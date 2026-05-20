package io.github.bloxxxx.etaclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;

public class McUtil {
    public static final MinecraftClient MC = MinecraftClient.getInstance();

    public static TextRenderer textRenderer() {
        return MC.textRenderer;
    }

    public static ClientWorld world() {
        return MC.world;
    }

    public static void execute(Runnable runnable) {
        MC.execute(runnable);
    }

    public static void setScreen(Screen screen) {
        MC.setScreen(screen);
    }
}
