package io.github.bloxxxx.etaclient.menu.widget.background;

import net.minecraft.client.gui.DrawContext;

public interface MenuWidgetBackground {
    void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, int x1, int y1, int x2, int y2);

    default boolean mouseOver(int mouseX, int mouseY, int x1, int y1, int x2, int y2) {
        return (
                mouseX >= x1 &&
                        mouseX <= x2 &&
                        mouseY >= y1 &&
                        mouseY <= y2
        );
    }
}
