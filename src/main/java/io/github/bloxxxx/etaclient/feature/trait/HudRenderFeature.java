package io.github.bloxxxx.etaclient.feature.trait;

import io.github.bloxxxx.etaclient.Etaclient;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public interface HudRenderFeature extends Feature {
    void renderHud(DrawContext context, RenderTickCounter renderTickCounter);
    String getHudElementId();

    default void initRenderHud() {
        HudElementRegistry.addLast(Etaclient.getIdentifier(getHudElementId()), this::renderHud);
    }
}
