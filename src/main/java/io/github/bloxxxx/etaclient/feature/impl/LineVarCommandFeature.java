package io.github.bloxxxx.etaclient.feature.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.github.bloxxxx.etaclient.feature.trait.CommandFeature;
import io.github.bloxxxx.etaclient.feature.trait.Feature;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public class LineVarCommandFeature implements CommandFeature, Feature {
    @Override
    public void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(ClientCommandManager.literal("vv")
                .then(ClientCommandManager.argument("name", StringArgumentType.greedyString())
                        .executes((context) -> {
                            String name = StringArgumentType.getString(context, "name");
                            PlayerUtil.sendCommand("var [" + name + "] -i 64");
                            return 0;
        })));
    }

    @Override
    public boolean enabled() {
        return true;
    }
}
