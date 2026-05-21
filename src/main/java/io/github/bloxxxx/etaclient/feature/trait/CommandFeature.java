package io.github.bloxxxx.etaclient.feature.trait;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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

    default void registerSimple(CommandDispatcher<FabricClientCommandSource> dispatcher, String name, AbstractSimpleSubCommand... subCommands) {
        LiteralArgumentBuilder<FabricClientCommandSource> builder = ClientCommandManager.literal(name);
        for (AbstractSimpleSubCommand subCommand : subCommands) {
            subCommand.addToBuilder(builder);
        }
        builder.executes(context -> {
            PlayerUtil.sendMinimsg("<#ff0000>No Subcommand specified!");
            return 0;
        });
        dispatcher.register(builder);
    }

    interface AbstractSimpleSubCommand {
        void addToBuilder(LiteralArgumentBuilder<FabricClientCommandSource> builder);
    }
    record SimpleSubCommand(String name, Command<FabricClientCommandSource> executes) implements AbstractSimpleSubCommand {
        @Override
        public void addToBuilder(LiteralArgumentBuilder<FabricClientCommandSource> builder) {
            builder.then(ClientCommandManager.literal(name).executes(executes));
        }
    }
    record SimpleNestedSubCommand(String name, AbstractSimpleSubCommand... subCommands) implements AbstractSimpleSubCommand {
        @Override
        public void addToBuilder(LiteralArgumentBuilder<FabricClientCommandSource> builder) {
            LiteralArgumentBuilder<FabricClientCommandSource> nestedBuilder = ClientCommandManager.literal(name);
            for (AbstractSimpleSubCommand subCommand : subCommands) {
                subCommand.addToBuilder(nestedBuilder);
            }
            builder.then(nestedBuilder);
        }
    }
}
