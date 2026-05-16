package io.github.bloxxxx.etaclient.feature.trait.packetHandler;

import net.minecraft.network.packet.Packet;

public interface PacketHandlerRunnable {
    boolean recieve(Packet<?> packet);
}
