package io.github.bloxxxx.etaclient.menu.widget.container;

import io.github.bloxxxx.etaclient.menu.widget.MenuWidget;
import io.github.bloxxxx.etaclient.util.LogUtil;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class ScrollMenuContainerWidget extends AbstractMenuContainerWidget {

    private int gap = 0;
    private int scroll = 0;
    private int maxScroll = 0;

    public ScrollMenuContainerWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public ScrollMenuContainerWidget setGap(int gap) {
        this.gap = gap;
        return this;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scroll -= (int) (verticalAmount * 10);
        if (scroll < 0) scroll = 0;
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    void updateChildPositions(List<MenuWidget> children) {
        int offset = 0;
        for (MenuWidget child : children) {
            offset += child.getHeight() + gap;
        }
        maxScroll = Math.max(0, offset - getHeight());
        if (scroll > maxScroll) scroll = maxScroll;

        offset = 0;
        for (MenuWidget child : children) {
            child.setPosition(0, offset - scroll);
            offset += child.getHeight() + gap;
        }

    }

    @Override
    void preRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    }

    @Override
    void postRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (maxScroll == 0) return;

        int size = 10;

        float fraction = 0;
        fraction = (float) scroll / maxScroll;
        float y1 = getY() + fraction * height - size;
        float y2 = getY() + fraction * height + size;

        float y1Smaller = Math.max(0, getY() - y1);
        float y2Bigger = Math.max(0, y2 - (getY() + height)) * 2;

        y1 += y1Smaller - y2Bigger;
        y2 += y1Smaller - y2Bigger;

        context.fill(getRight() - 2, (int) y1, getRight(), (int) y2, 0xa0ffffff);
    }
}
