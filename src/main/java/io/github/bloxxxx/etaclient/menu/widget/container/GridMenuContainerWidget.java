package io.github.bloxxxx.etaclient.menu.widget.container;

import io.github.bloxxxx.etaclient.menu.widget.MenuWidget;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class GridMenuContainerWidget extends AbstractMenuContainerWidget {

    private int gap = 0;
    private int margin = 0;
    private final int columns;
    private final int rows;

    public GridMenuContainerWidget(int x, int y, int width, int height, int columns, int rows) {
        super(x, y, width, height);
        this.columns = columns;
        this.rows = rows;
    }

    public GridMenuContainerWidget setGap(int gap) {
        this.gap = gap;
        return this;
    }

    public GridMenuContainerWidget setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public int getMaxElements() {
        return rows * columns;
    }

    @Override
    void updateChildPositions(List<MenuWidget> children) {
        int currentColumn = 0;
        int currentRow = 0;
        int maxHeight = 0;

        int xOffset = margin;
        int yOffset = margin;
        for (MenuWidget child : children) {
            maxHeight = Math.max(maxHeight, child.getHeight());

            child.setPosition(xOffset, yOffset);

            xOffset += child.getWidth() + gap;
            currentColumn++;
            if (currentColumn == columns) {
                currentRow++;
                yOffset += maxHeight + gap;
                maxHeight = 0;
                xOffset = margin;
                currentColumn = 0;

                if (currentRow > rows) {
                    break;
                }
            }
        }
    }

    @Override
    void preRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }

    @Override
    void postRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }
}
