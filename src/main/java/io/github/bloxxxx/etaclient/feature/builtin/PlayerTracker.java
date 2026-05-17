package io.github.bloxxxx.etaclient.feature.builtin;

import com.mojang.authlib.GameProfile;
import io.github.bloxxxx.etaclient.feature.trait.DisconnectListenerFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacket;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeClientPlayerData;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeMode;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeNode;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubePlayer;
import io.github.bloxxxx.etaclient.util.McUtil;
import io.github.bloxxxx.etaclient.util.MinimsgUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.ScoreboardScoreUpdateS2CPacket;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTracker implements DisconnectListenerFeature, ForceFeature {
    public static HypercubeMode MODE = HypercubeMode.NONE;
    public static HypercubeNode NODE = null;
    public static Set<HypercubeNode> NODES = new HashSet<>();
    public static HypercubeClientPlayerData PLAYER_DATA = new HypercubeClientPlayerData();

    private static final Pattern SPAWN_PATTERN = Pattern.compile(
            ".*<!italic><!underlined><!strikethrough><!bold><!obfuscated><#FFD42A>⧈ ([0-9]+) Tokens {2}</#FFD42A></!obfuscated></!bold></!strikethrough></!underlined></!italic><!italic><!underlined><!strikethrough><!bold><!obfuscated><#FFD4AA>ᛥ ([0-9]+) Tickets {2}</#FFD4AA></!obfuscated></!bold></!strikethrough></!underlined></!italic><!italic><!underlined><!strikethrough><!bold><!obfuscated><#AAD4FF>⚡ ([0-9]+) Sparks"
    );
    private static final Pattern PLAY_PATTERN = Pattern.compile(
            "<bold><green>» </green></bold><gray>Joined game:(.+)by </gray><white>(.+)</white><gray>."
    );
    private static final Pattern DEV_PATTERN = Pattern.compile(
            "<bold><green>» </green></bold><gray>You are now in dev mode."
    );
    private static final Pattern BUILD_PATTERN = Pattern.compile(
            "<bold><green>» </green></bold><gray>You are now in build mode."
    );
    private static final Pattern TRANSFER_PATTERN = Pattern.compile(
            "</green></bold><gray>Sending you to (.+)</gray><gray>..."
    );
    private static final Pattern SCOREBOARD_PATTERN = Pattern.compile(
            "§a(.+)§8"
    );

    @Override
    public void onDisconnect(DisconnectionInfo info) {
        MODE = HypercubeMode.NONE;
        NODE = null;
        NODES = new HashSet<>();
    }

    @HandlePacket
    public static boolean handleActionBar(OverlayMessageS2CPacket packet) {
        Matcher matcher = SPAWN_PATTERN.matcher(MinimsgUtil.serializeText(packet.text()));
        if (matcher.find()) {
            MODE = HypercubeMode.SPAWN;
            PLAYER_DATA.tokens = Integer.parseInt(matcher.group(1));
            PLAYER_DATA.tickets = Integer.parseInt(matcher.group(2));
            PLAYER_DATA.sparks = Integer.parseInt(matcher.group(3));
        }
        return false;
    }

    @HandlePacket
    public static boolean handleGameMessage(GameMessageS2CPacket packet) {
        String message = MinimsgUtil.serializeText(packet.content());
        if (PLAY_PATTERN.matcher(message).find()) {
            MODE = HypercubeMode.PLAY;
            return false;
        }
        if (BUILD_PATTERN.matcher(message).find()) {
            MODE = HypercubeMode.BUILD;
            return false;
        }
        if (DEV_PATTERN.matcher(message).find()) {
            MODE = HypercubeMode.DEV;
            return false;
        }
        if (TRANSFER_PATTERN.matcher(message).find()) {
            MODE = HypercubeMode.TRANSFER;
            return false;
        }
        return false;
    }

    @HandlePacket
    public static boolean handleScoreboard(ScoreboardScoreUpdateS2CPacket packet) {
        Matcher matcher = SCOREBOARD_PATTERN.matcher(packet.scoreHolderName());
        if (matcher.find()) {
            changeNode(matcher.group(1));
        }
        return false;
    }

    private static void changeNode(String name) {
        if (NODE == null || !name.equals(NODE.getName())) {
            NODE = new HypercubeNode(name);
            NODES.removeIf((node) -> node.getId().equals(NODE.getId()));
            NODES.add(NODE);

            updateNodePlayers();
        }
    }

    private static void updateNodePlayers() {
        McUtil.execute(() -> {
            ClientPlayerEntity player = PlayerUtil.get();
            if (player == null) return;
            NODE.clearPlayers();
            for (PlayerListEntry entry : player.networkHandler.getPlayerList()) {
                GameProfile profile = entry.getProfile();
                if (profile.id().equals(player.getUuid())) continue;
                if (profile.name().contains("§")) continue; // No clue why this happens tbh

                // Remove from other nodes
                for (HypercubeNode node : NODES) {
                    node.removePlayer(profile.id());
                }

                NODE.addPlayer(new HypercubePlayer(profile));
            }
        });
    }
}
