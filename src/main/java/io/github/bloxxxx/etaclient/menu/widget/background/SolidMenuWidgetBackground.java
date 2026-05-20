package io.github.bloxxxx.etaclient.menu.widget.background;

import net.minecraft.client.gui.DrawContext;

public class SolidMenuWidgetBackground implements MenuWidgetBackground {

    private final int color;
    public SolidMenuWidgetBackground(int color) {
        this.color = color;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, int x1, int y1, int x2, int y2) {
        context.fill(x1, y1, x2, y2, color);
    }
}
