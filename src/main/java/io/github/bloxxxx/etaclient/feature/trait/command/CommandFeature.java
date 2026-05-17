package io.github.bloxxxx.etaclient.feature.trait.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.bloxxxx.etaclient.feature.trait.Feature;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public interface CommandFeature extends Feature {
    void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess);

    default void initCommands() {
        ClientCommandRegistrationCallback.EVENT.register(this::registerCommands);
    }

    default void registerSimple(CommandDispatcher<FabricClientCommandSource> dispatcher, String name, Command<FabricClientCommandSource> executes) {
        dispatcher.register(ClientCommandManager.literal(name).executes((executes)));
    }

    default void registerSimple(CommandDispatcher<FabricClientCommandSource> dispatcher, String name, SimpleSubCommand... subCommands) {
        LiteralArgumentBuilder<FabricClientCommandSource> builder = ClientCommandManager.literal(name);
        for (SimpleSubCommand subCommand : subCommands) {
            builder.then(ClientCommandManager.literal(subCommand.name).executes(subCommand.executes));
        }
        builder.executes(context -> {
            StringBuilder sb = new StringBuilder();
            for (SimpleSubCommand subCommand : subCommands) {
                if (!sb.isEmpty()) sb.append("<reset><dark_gray>, ");
                sb.append("<gray><italic>").append(subCommand.name);
            }
            PlayerUtil.sendMinimsg("<#ff0000>No Subcommand specified! <dark_gray>( " + sb + "<reset> <dark_gray>)");
            return 0;
        });
        dispatcher.register(builder);
    }

    record SimpleSubCommand(String name, Command<FabricClientCommandSource> executes) {
    }
}
