package io.github.bloxxxx.etaclient.menu.widget;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class LabelMenuWidget extends MenuWidget {
    private final TextAlignment alignment;
    private final int color;
    private final float scale;
    public LabelMenuWidget(int x, int y, int width, int height, Text message, int color, TextAlignment alignment, float scale) {
        super(x, y, width, height, message);
        this.alignment = alignment;
        this.color = color;
        this.scale = scale;
    }
    public LabelMenuWidget(int x, int y, int width, int height, Text message, int color, TextAlignment alignment) {
        this(x, y, width, height, message, color, alignment, 1);
    }

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        drawText(context, getMessage(), color, alignment, getX(), getY(), getRight(), getBottom(), scale);
    }
}
