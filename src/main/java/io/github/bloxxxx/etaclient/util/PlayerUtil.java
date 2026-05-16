package io.github.bloxxxx.etaclient.util;

import io.github.bloxxxx.etaclient.Etaclient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.util.Optional;

public final class PlayerUtil {
    private PlayerUtil() {}

    public static ClientPlayerEntity get() {
        return Etaclient.MC.player;
    }

    public static Optional<ClientPlayerEntity> getOp() {
        return Optional.ofNullable(get());
    }

    public static void sendMessage(Text message, boolean overlay) {
        getOp().ifPresent((player) -> {
            player.sendMessage(message, overlay);
        });
    }
    public static void sendMessage(Text message) {
        sendMessage(message, false);
    }
    public static void sendMessage(String message) {
        sendMessage(Text.literal(message));
    }
}
