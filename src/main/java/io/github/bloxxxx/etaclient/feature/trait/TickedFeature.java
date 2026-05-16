package io.github.bloxxxx.etaclient.feature.trait;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public interface TickedFeature extends Feature {
    void tick();

    default void initTicked() {
        ClientTickEvents.END_CLIENT_TICK.register((ignored) -> tick());
    }
}
