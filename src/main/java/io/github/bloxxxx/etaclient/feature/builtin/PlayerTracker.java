package io.github.bloxxxx.etaclient.feature.builtin;

import io.github.bloxxxx.etaclient.feature.trait.DisconnectListenerFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.packetHandler.HandlePacket;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeMode;
import io.github.bloxxxx.etaclient.util.LogUtil;
import io.github.bloxxxx.etaclient.util.MinimsgUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTracker implements DisconnectListenerFeature, ForceFeature {
    public static HypercubeMode MODE = HypercubeMode.NONE;

    private static final Pattern SPAWN_PATTERN = Pattern.compile(
            ".*<!italic><!underlined><!strikethrough><!bold><!obfuscated><#FFD42A>⧈ ([0-9]+) Tokens  </#FFD42A></!obfuscated></!bold></!strikethrough></!underlined></!italic><!italic><!underlined><!strikethrough><!bold><!obfuscated><#FFD4AA>ᛥ ([0-9]+) Tickets  </#FFD4AA></!obfuscated></!bold></!strikethrough></!underlined></!italic><!italic><!underlined><!strikethrough><!bold><!obfuscated><#AAD4FF>⚡ ([0-9]+) Sparks"
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

    @Override
    public void onDisconnect(DisconnectionInfo info) {
        MODE = HypercubeMode.NONE;
    }

    @HandlePacket
    public static boolean handleActionBar(OverlayMessageS2CPacket packet) {
        Matcher matcher = SPAWN_PATTERN.matcher(MinimsgUtil.serializeText(packet.text()));
        if (matcher.find()) MODE = HypercubeMode.SPAWN;
        return false;
    }

    @HandlePacket
    public static boolean handleGameMessage(GameMessageS2CPacket packet) {
        String message = MinimsgUtil.serializeText(packet.content());
        LogUtil.log(message);
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
}
