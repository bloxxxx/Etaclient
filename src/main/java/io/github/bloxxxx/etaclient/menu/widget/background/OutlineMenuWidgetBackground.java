package io.github.bloxxxx.etaclient.menu.widget.background;

import net.minecraft.client.gui.DrawContext;

public class OutlineMenuWidgetBackground implements MenuWidgetBackground {

    private final int color;
    private final int outlineColor;
    private final int outlineThickness;
    public OutlineMenuWidgetBackground(int color, int outlineColor, int outlineThickness) {
        this.color = color;
        this.outlineColor = outlineColor;
        this.outlineThickness = outlineThickness;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, int x1, int y1, int x2, int y2) {
        context.fill(x1, y1, x2, y2, outlineColor);
        context.fill(x1 + outlineThickness, y1 + outlineThickness, x2 - outlineThickness, y2 - outlineThickness, color);
    }
}
