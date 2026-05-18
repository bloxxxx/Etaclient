package io.github.bloxxxx.etaclient.feature.trait;

import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public interface SimpleWorldRenderFeature extends WorldRenderFeature {

    void renderWorldSimple(WorldRenderContext context, MatrixStack matrixStack, Vec3d cameraPos);

    @Override
    default void renderWorld(WorldRenderContext context) {
        MatrixStack matrixStack = context.matrices();
        Camera camera = context.gameRenderer().getCamera();
        Vec3d cameraPos = camera.getCameraPos();

        matrixStack.push();
        matrixStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        renderWorldSimple(context, matrixStack, cameraPos);

        matrixStack.pop();
    }
}
