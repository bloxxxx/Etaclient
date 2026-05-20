package io.github.bloxxxx.etaclient.menu.widget.container;

import io.github.bloxxxx.etaclient.menu.widget.MenuWidget;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class HorizontalMenuContainerWidget extends AbstractMenuContainerWidget {

    private int gap = 0;

    public HorizontalMenuContainerWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public HorizontalMenuContainerWidget setGap(int gap) {
        this.gap = gap;
        return this;
    }

    @Override
    void updateChildPositions(List<MenuWidget> children) {
        int offset = 0;
        for (MenuWidget child : children) {
            child.setPosition(offset, 0);
            offset += child.getWidth() + gap;
        }
    }

    @Override
    void preRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }

    @Override
    void postRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }
}
