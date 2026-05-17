package io.github.bloxxxx.etaclient.feature.impl;

import com.mojang.brigadier.CommandDispatcher;
import io.github.bloxxxx.etaclient.feature.builtin.PlayerTracker;
import io.github.bloxxxx.etaclient.feature.builtin.TeleportHandler;
import io.github.bloxxxx.etaclient.feature.trait.CommandFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.HudRenderFeature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeNode;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubePlayer;
import io.github.bloxxxx.etaclient.util.*;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TestFeature implements InitFeature, CommandFeature, HudRenderFeature, ForceFeature {

    @Override
    public void init() {
        LogUtil.log("TestFeature Initialized");
    }

    @Override
    public void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {

        registerSimple(dispatcher, "etatest",

                new SimpleSubCommand("getlocation", context -> {
                    String node = "NULL";
                    if (PlayerTracker.NODE != null) node = PlayerTracker.NODE.getId();
                    PlayerUtil.sendMinimsg(
                            "<#a0a0ff>Current Location: <white><bold>" +
                                    PlayerTracker.MODE.name() +
                                    "<reset> <gray><italic>(" +
                                    PlayerTracker.MODE.location.name() +
                                    ") [" +
                                    node
                                    +
                                    "]"
                    );
                    return 0;
                }),

                new SimpleSubCommand("getnodes", context -> {
                    PlayerUtil.sendMinimsg("<#a0a0ff>Tracked nodes:");
                    for (HypercubeNode node : PlayerTracker.NODES) {
                        StringBuilder playerList = new StringBuilder();
                        boolean first = true;
                        for (HypercubePlayer player : node.getPlayers()) {
                            if (!first) {
                                playerList.append(", ");
                            }
                            first = false;
                            playerList.append(player.name());
                        }
                        PlayerUtil.sendMinimsg("<white><bold>" + node.getId() + "</bold>: <gray>" + playerList);
                    }

                    return 0;
                }),

                new SimpleSubCommand("setteleport", context -> {
                    teleportTest = PlayerUtil.getPosition();
                    PlayerUtil.sendMinimsg("<green>Teleport location set: <gray><italic><" + teleportTest.getX() + ", " + teleportTest.getY() + ", " + teleportTest.getZ() + ">");
                    return 0;
                }),

                new SimpleSubCommand("teleport", context -> {
                    if (TeleportHandler.tp(teleportTest)) {
                        PlayerUtil.sendMinimsg("<green>Teleported successfully");
                    } else {
                        PlayerUtil.sendMinimsg("<red>Teleport failed");
                    }
                    return 0;
                }),

                new SimpleSubCommand("pbv", context -> {
                    PlayerUtil.sendMinimsg("<green>" + Objects.requireNonNullElse(
                            PBVUtil.get(PlayerUtil.getHandStack()),
                            "<red>No PBV Found"
                    ));
                    return 0;
                }),

                new SimpleSubCommand("varitem", context -> {
                    PlayerUtil.sendMinimsg("<green>" + Objects.requireNonNullElse(
                            VarItem.parse(PlayerUtil.getHandStack()),
                            "<red>Not a Varitem"
                    ));
                    return 0;
                }),

                new SimpleSubCommand("templateparse", context -> {
                    PlayerUtil.sendMinimsg("<green>" + Objects.requireNonNullElse(
                            PBVUtil.getCodeTemplate(PlayerUtil.getHandStack()),
                            "<red>Not a Template"
                    ).toString().replaceAll("§.", ""));
                    return 0;
                }),

                new SimpleSubCommand("playerdata", context -> {
                    PlayerUtil.sendMinimsg("<green>" + PlayerTracker.PLAYER_DATA.toString());
                    return 0;
                }),


                new SimpleNestedSubCommand("toggleHud",
                        new SimpleSubCommand("enable", context -> {
                            hud = true;
                            return 0;
                        }),

                        new SimpleSubCommand("disable", context -> {
                            hud = false;
                            return 0;
                        })
                )

        );
    }

    private boolean hud = false;
    private Vec3d teleportTest = Vec3d.ZERO;

    @Override
    public void renderHud(DrawContext context, RenderTickCounter renderTickCounter) {
        if (!hud) return;

        String[] messages = getRenderString();

        int y = 20;
        for (String message: messages) {
            context.drawText(
                    McUtil.textRenderer(),
                    MinimsgUtil.deserializeText(message),
                    20,
                    y,
                    -1,
                    true
            );
            y += 15;
        }
    }

    private static String @NotNull [] getRenderString() {
        String node = "NONE";
        if (PlayerTracker.NODE != null) node = PlayerTracker.NODE.getId();
        return new String[]{
                "<#a0a0ff>Current Location:",
                "<dark_gray>- <gray>MODE: <white><bold>" + PlayerTracker.MODE.name(),
                "<dark_gray>- <gray>LOC: <white><bold>" + PlayerTracker.MODE.location.name(),
                "<dark_gray>- <gray>NODE: <white><bold>" + node,
                "<dark_gray>- <gray>NODES: <white><bold>" + PlayerTracker.NODES.size()
        };
    }

    @Override
    public String getHudElementId() {
        return "testfeature";
    }
}
