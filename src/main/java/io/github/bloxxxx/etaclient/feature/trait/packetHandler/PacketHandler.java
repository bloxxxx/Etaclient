package io.github.bloxxxx.etaclient.feature.trait.packetHandler;

import io.github.bloxxxx.etaclient.feature.trait.Feature;
import net.minecraft.network.packet.Packet;

public class PacketHandler {
    private final Feature feature;
    private final PacketHandlerRunnable runnable;
    private final Class<?> paramClass;
    private final boolean alwaysOn;

    public PacketHandler(Feature feature, PacketHandlerRunnable runnable, Class<?> paramClass, boolean alwaysOn) {
        this.feature = feature;
        this.runnable = runnable;
        this.paramClass = paramClass;
        this.alwaysOn = alwaysOn;
    }

    public boolean recieve(Packet<?> packet) {
        return runnable.recieve(packet);
    }

    public boolean enabled() {
        if (alwaysOn) return true;
        return feature.enabled();
    }

    public Class<?> getParamClass() {
        return paramClass;
    }
}
