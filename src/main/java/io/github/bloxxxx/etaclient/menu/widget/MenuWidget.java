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
    protected void drawText(DrawContext context, Text text, int color, TextAlignment alignment, int x1, int y1, int x2, int y2) {
        context.getMatrices().pushMatrix();

        int xDist = x2 - x1;
        int yDist = y2 - y1;
        float xOffset = switch (alignment) {
            case LEFT -> 5;
            case RIGHT -> xDist - fontWidth(text) - 5;
            case CENTER -> (float) (xDist / 2 - (fontWidth(text) / 2));
        };

        context.getMatrices().translate(xOffset, (float) yDist / 2 - halfFontHeight());
        context.drawText(textRenderer(), text, x1, y1, color, true);
        context.getMatrices().popMatrix();
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
    }

    protected boolean mouseDown = false;
    // Only becomes true when the click started inside the widget, although it can end anywhere.
    protected boolean mouseDownInside = false;

    @Override
    public void onClick(Click click, boolean doubled) {
        mouseDown = true;
        if (isMouseOver(click.x(), click.y())) mouseDownInside = true;
        onMouseClick(click, true, doubled);
    }

    @Override
    public void onRelease(Click click) {
        mouseDown = false;
        mouseDownInside = false;
        onMouseClick(click, false, false);
    }

    public abstract void onMouseClick(Click click, boolean down, boolean doubled);
}
