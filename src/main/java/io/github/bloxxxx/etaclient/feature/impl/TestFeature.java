package io.github.bloxxxx.etaclient.feature.impl;

import com.mojang.brigadier.CommandDispatcher;
import io.github.bloxxxx.etaclient.feature.builtin.PlayerTracker;
import io.github.bloxxxx.etaclient.feature.builtin.TeleportHandler;
import io.github.bloxxxx.etaclient.feature.trait.command.CommandFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItem;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeNode;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubePlayer;
import io.github.bloxxxx.etaclient.util.LogUtil;
import io.github.bloxxxx.etaclient.util.PBVUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class TestFeature implements InitFeature, CommandFeature, ForceFeature {

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
                })

        );
    }

    private Vec3d teleportTest = Vec3d.ZERO;
}
