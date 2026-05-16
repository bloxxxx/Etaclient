package io.github.bloxxxx.etaclient.feature.impl;

import com.mojang.brigadier.CommandDispatcher;
import io.github.bloxxxx.etaclient.feature.builtin.PlayerTracker;
import io.github.bloxxxx.etaclient.feature.trait.CommandFeature;
import io.github.bloxxxx.etaclient.feature.trait.ForceFeature;
import io.github.bloxxxx.etaclient.feature.trait.InitFeature;
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
        dispatcher.register(ClientCommandManager.literal("dftest").executes((context -> {
            PlayerUtil.sendMinimsg("<#a0a0ff>Current Location: <white><bold>" + PlayerTracker.MODE.name() + "<reset> <gray><italic>(" + PlayerTracker.MODE.location.name() + ")");
            return 0;
        })));
    }
}
