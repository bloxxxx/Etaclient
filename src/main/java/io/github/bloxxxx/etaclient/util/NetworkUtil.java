package io.github.bloxxxx.etaclient.util;

import io.github.bloxxxx.etaclient.Etaclient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;

import java.util.Optional;

public final class NetworkUtil {
    private NetworkUtil() {}

    public static ClientPlayNetworkHandler get() {
        return McUtil.MC.getNetworkHandler();
    }

    public static Optional<ClientPlayNetworkHandler> getOp() {
        return Optional.ofNullable(get());
    }

    public static void send(Packet<?> packet) {
        getOp().ifPresent((handler) -> handler.sendPacket(packet));
    }
}
