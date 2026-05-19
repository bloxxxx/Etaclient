package io.github.bloxxxx.etaclient.feature.trait;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public interface GameRenderOverlayFeature extends Feature {
    void renderGameOverlay(RenderTickCounter tickCounter, boolean tick, DrawContext context);
}
