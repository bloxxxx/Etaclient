package io.github.bloxxxx.etaclient.menu.widget;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundEvents;

public class ToggleButtonWidget extends MenuWidget {
    public ToggleButtonWidget(int x, int y, int width, int height, boolean defaultValue) {
        super(x, y, width, height);
        this.defaultValue = defaultValue;
        value = defaultValue;
        animation = new WidgetAnimationTimer(WidgetAnimationTimer.EasingType.CUBIC_OUT);
    }

    private boolean defaultValue;
    public boolean value;

    private WidgetAnimationTimer animation;

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {
        if (!down) return;
        value = !value;
        animation.setTarget(value, 0.5F);
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        animation.update(deltaTicks);

        int margin = 2;

        int fill = value ? 0xffa0ffa0 : 0xffffa0a0;
        int outline = hovered ? 0xff303040 : 0xff101020;
        int leftFill = 0xff203020;
        int rightFill = 0xff302020;

        context.fill(getX(), getY(), getRight(), getBottom(), outline);
        context.fill(getX() + margin, getY() + margin, getRight() - margin, getBottom() - margin, rightFill);
        context.fill(getX() + margin, getY() + margin, (int) (getX() + ((width - margin * 2) * animation.value) + margin), getBottom() - margin, leftFill);

        context.getMatrices().pushMatrix();
        int size = height;
        context.getMatrices().translate((width - size) * animation.value, 0);
        context.fill(getX() + margin, getY() + margin, getX() + size - margin, getY() + size - margin, fill);
        if (value == defaultValue) context.fill(getX() + margin, getY() + size + margin - 5, getX() + size - margin, getY() + size - margin, 0xa0ffffff);
        context.getMatrices().popMatrix();
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        if (value) {
            soundManager.play(PositionedSoundInstance.ui(SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, 1.5F, 0.5F));
        } else {
            soundManager.play(PositionedSoundInstance.ui(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, 1.5F, 0.5F));
        }
    }
}
