package io.github.bloxxxx.etaclient.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.bloxxxx.etaclient.feature.Features;
import io.github.bloxxxx.etaclient.feature.trait.GameRenderOverlayFeature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MGameRenderer {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/GuiRenderer;render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V"))
    public void render(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
        Features.callFeatures(GameRenderOverlayFeature.class, f -> f.renderGameOverlay(tickCounter, tick, drawContext));
    }
}
