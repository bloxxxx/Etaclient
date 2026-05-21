package io.github.bloxxxx.etaclient.menu.widget;

import io.github.bloxxxx.etaclient.util.LogUtil;
import io.github.bloxxxx.etaclient.util.PlayerUtil;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class IntSliderWidget extends MenuWidget {
    public IntSliderWidget(int x, int y, int width, int height, int min, int max, int defaultValue) {
        super(x, y, width, height);
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        value = defaultValue;
        animation = new WidgetAnimationTimer(WidgetAnimationTimer.EasingType.CUBIC_OUT, getFraction());
    }

    private final int min;
    private final int max;
    public final int defaultValue;
    private int value;

    private WidgetAnimationTimer animation;

    public void setValue(int value) {
        this.value = value;
        animation.setValue(getFraction());
    }
    public int getValue() {
        return value;
    }

    private final int margin = 2;

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {}

    private void updateMouseMove(float mouseX, float mouseY) {
        if (!mouseDownInside) return;
        float clickFraction = ((mouseX - (getX() + margin)) / ((getRight() - margin) - (getX() + margin)));
        clickFraction = Math.clamp(clickFraction, 0, 1);
        int val = Math.round(min + (max - min) * clickFraction);
        if (val != value) {
            PlayerUtil.get().playSound(SoundEvents.BLOCK_METAL_HIT, 1, 1.5F);
            value = val;
            animation.setTarget(getFraction(), 1);
        }
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        animation.update(deltaTicks);

        updateMouseMove(mouseX, mouseY);

        context.fill(getX(), getY(), getRight(), getBottom(), 0xff404050);
        context.fill(getX() + margin, getY() + margin, getRight() - margin, getBottom() - margin, 0xff101020);

        drawText(context, Text.literal(String.valueOf(value)), (value == defaultValue) ? 0xffa0a0a0 : 0xff505050, TextAlignment.CENTER, getX(), getY(), getRight(), getBottom());

        int thickness = 3;
        context.getMatrices().pushMatrix();
        context.getMatrices().translate((width - thickness - margin * 2) * animation.getValue(), 0);
        context.fill(getX() + margin, getY() + margin, getX() + margin + thickness, getBottom() - margin, 0xa0ffffff);
        if (value == defaultValue) context.fill(getX() + margin, getBottom() - margin - 2, getX() + margin + thickness, getBottom() - margin, 0xa0ffffff);
        context.getMatrices().popMatrix();
    }

    public float getFraction() {
        return (float) (value - min) / (max - min);
    }
}
