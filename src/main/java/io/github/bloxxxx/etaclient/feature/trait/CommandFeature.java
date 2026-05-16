package io.github.bloxxxx.etaclient.feature.trait;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public interface CommandFeature extends Feature {
    void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess);

    default void initCommands() {
        ClientCommandRegistrationCallback.EVENT.register(this::registerCommands);
    }
}
