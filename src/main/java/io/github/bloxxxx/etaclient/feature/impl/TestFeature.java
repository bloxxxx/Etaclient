package io.github.bloxxxx.etaclient.feature.impl;

import com.mojang.brigadier.CommandDispatcher;
import io.github.bloxxxx.etaclient.feature.builtin.PlayerTracker;
import io.github.bloxxxx.etaclient.feature.trait.CommandFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubeNode;
import io.github.bloxxxx.etaclient.hypercube.server.HypercubePlayer;
import io.github.bloxxxx.etaclient.util.LogUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public class TestFeature implements InitFeature, CommandFeature, ForceFeature {

    @Override
    public void init() {
        LogUtil.log("TestFeature Initialized");
    }

    @Override
    public void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(ClientCommandManager.literal("getlocation").executes((context -> {
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
        })));

        dispatcher.register(ClientCommandManager.literal("getnodes").executes((context -> {
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
        })));
    }
}
