package io.github.bloxxxx.etaclient.menu.widget.background;

import net.minecraft.client.gui.DrawContext;

public class HoverSolidMenuWidgetBackground implements MenuWidgetBackground {

    private final int color;
    private final int hoverColor;
    public HoverSolidMenuWidgetBackground(int color, int hoverColor) {
        this.color = color;
        this.hoverColor = hoverColor;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, int x1, int y1, int x2, int y2) {
        int col = color;
        if (mouseOver(mouseX, mouseY, x1, y1, x2, y2)) col = hoverColor;
        context.fill(x1, y1, x2, y2, col);
    }
}
