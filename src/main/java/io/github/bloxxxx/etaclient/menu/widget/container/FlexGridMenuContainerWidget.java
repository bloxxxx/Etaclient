package io.github.bloxxxx.etaclient.menu.widget.container;

import io.github.bloxxxx.etaclient.menu.widget.MenuWidget;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class FlexGridMenuContainerWidget extends AbstractMenuContainerWidget {

    private int gap = 0;
    private int margin = 0;

    public FlexGridMenuContainerWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public FlexGridMenuContainerWidget setGap(int gap) {
        this.gap = gap;
        return this;
    }

    public FlexGridMenuContainerWidget setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    @Override
    void updateChildPositions(List<MenuWidget> children) {
        int maxHeight = 0;

        int xOffset = margin;
        int yOffset = margin;
        for (MenuWidget child : children) {

            if (xOffset + child.getWidth() + margin > getWidth()) {
                yOffset += maxHeight + gap;
                maxHeight = 0;
                xOffset = margin;
            }

            maxHeight = Math.max(maxHeight, child.getHeight());

            child.setPosition(xOffset, yOffset);

            xOffset += child.getWidth() + gap;
        }
    }

    @Override
    void preRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    }

    @Override
    void postRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }
}
