package io.github.bloxxxx.etaclient.feature.trait;

import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;

public interface WorldRenderFeature extends Feature {
    void renderWorld(WorldRenderContext context);

    default void initWorldRender() {
        WorldRenderEvents.END_MAIN.register(context -> {if (enabled()) {renderWorld(context);}});
    }
}
