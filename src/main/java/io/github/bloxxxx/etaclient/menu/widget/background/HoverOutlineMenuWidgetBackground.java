package io.github.bloxxxx.etaclient.menu.widget.background;

import net.minecraft.client.gui.DrawContext;

public class HoverOutlineMenuWidgetBackground implements MenuWidgetBackground {

    private final int color;
    private final int hoverColor;
    private final int outlineColor;
    private final int hoverOutlineColor;
    private final int outlineThickness;
    private final int hoverOutlineThickness;
    public HoverOutlineMenuWidgetBackground(int color, int hoverColor, int outlineColor, int hoverOutlineColor, int outlineThickness, int hoverOutlineThickness) {
        this.color = color;
        this.hoverColor = hoverColor;
        this.outlineColor = outlineColor;
        this.hoverOutlineColor = hoverOutlineColor;
        this.outlineThickness = outlineThickness;
        this.hoverOutlineThickness = hoverOutlineThickness;
    }
    public HoverOutlineMenuWidgetBackground(int color, int hoverColor, int outlineColor, int hoverOutlineColor, int outlineThickness) {
        this(color, hoverColor, outlineColor, hoverOutlineColor, outlineThickness, outlineThickness);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, int x1, int y1, int x2, int y2) {
        boolean hovered = mouseOver(mouseX, mouseY, x1, y1, x2, y2);

        int col = hovered ? hoverColor : color;
        int out = hovered ? hoverOutlineColor : outlineColor;
        int thick = hovered ? hoverOutlineThickness : outlineThickness;

        context.fill(x1, y1, x2, y2, out);
        context.fill(x1 + thick, y1 + thick, x2 - thick, y2 - thick, col);
    }
}
