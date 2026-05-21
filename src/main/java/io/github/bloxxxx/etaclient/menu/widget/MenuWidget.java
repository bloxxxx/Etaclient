package io.github.bloxxxx.etaclient.menu.widget;

import io.github.bloxxxx.etaclient.util.McUtil;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;

public abstract class MenuWidget extends ClickableWidget {
    public MenuWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }
    public MenuWidget(int x, int y, int width, int height) {
        this(x, y, width, height, Text.empty());
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE, getNarrationMessage());
    }

    protected TextRenderer textRenderer() {
        return McUtil.textRenderer();
    }
    protected int fontHeight() {
        return textRenderer().fontHeight;
    }
    protected int fontWidth(String text) {
        return textRenderer().getWidth(text);
    }
    protected int fontWidth(Text text) {
        return textRenderer().getWidth(text.asOrderedText());
    }
    protected int halfFontHeight() {
        return fontHeight() / 2;
    }
    protected void drawText(DrawContext context, Text text, int color, TextAlignment alignment, int x1, int y1, int x2, int y2, float scale, float xShift) {
        context.getMatrices().pushMatrix();

        int fontWidth = fontWidth(text);

        int xDist = x2 - x1;
        int yDist = y2 - y1;
        float xOffset = switch (alignment) {
            case LEFT -> xShift;
            case RIGHT -> xDist - fontWidth - xShift;
            case CENTER -> (float) (xDist / 2 - (fontWidth / 2));
        };

        float yOffset = (float) yDist / 2 - halfFontHeight();
        context.getMatrices().translate(x1 + xOffset, y1 + yOffset);

        float pivotX = switch (alignment) {
            case LEFT -> 0;
            case CENTER -> fontWidth / 2f;
            case RIGHT -> fontWidth;
        };

        float pivotY = halfFontHeight();

        context.getMatrices().scaleAround(scale, pivotX, pivotY);
        context.drawText(textRenderer(), text, 0, 0, color, true);
        context.getMatrices().popMatrix();
    }
    protected void drawText(DrawContext context, Text text, int color, TextAlignment alignment, int x1, int y1, int x2, int y2, float scale) {
        drawText(context, text, color, alignment, x1, y1, x2, y2, scale, 0);
    }
    protected void drawText(DrawContext context, Text text, int color, TextAlignment alignment, int x1, int y1, int x2, int y2) {
        drawText(context, text, color, alignment, x1, y1, x2, y2, 1);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
    }

    protected boolean mouseDown = false;
    protected boolean mouseWasDown = false;
    // Only becomes true when the click started inside the widget, although it can end anywhere.
    protected boolean mouseDownInside = false;
    protected boolean mouseWasDownInside = false;

    @Override
    public void onClick(Click click, boolean doubled) {
        mouseDown = true;
        mouseWasDown = true;
        if (isMouseOver(click.x(), click.y())) {
            mouseDownInside = true;
            mouseWasDownInside = true;
        }
        onMouseClick(click, true, doubled);
    }

    @Override
    public void onRelease(Click click) {
        mouseDown = false;
        mouseDownInside = false;
        onMouseClick(click, false, false);
        mouseWasDown = false;
        mouseWasDownInside = false;
    }

    public abstract void onMouseClick(Click click, boolean down, boolean doubled);
}
