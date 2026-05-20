package io.github.bloxxxx.etaclient.menu.widget;

import io.github.bloxxxx.etaclient.menu.widget.background.HoverOutlineMenuWidgetBackground;
import io.github.bloxxxx.etaclient.menu.widget.background.MenuWidgetBackground;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class MenuButtonWidget extends MenuWidget {

    private int textColor = -1;
    private @Nullable MenuWidgetBackground background = new HoverOutlineMenuWidgetBackground(
            0xff101020,
            0xff202030,
            0xff303040,
            0xffd0d0f0,
            1
    );

    private TextAlignment textAlignment = TextAlignment.LEFT;

    private ClickAction clickAction = null;

    public MenuButtonWidget(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    public MenuButtonWidget setTextColor(int color) {
        this.textColor = color;
        return this;
    }

    public MenuButtonWidget setTextAlignment(TextAlignment alignment) {
        textAlignment = alignment;
        return this;
    }

    public MenuButtonWidget setBackground(@Nullable MenuWidgetBackground background) {
        this.background = background;
        return this;
    }

    public MenuButtonWidget onClick(ClickAction action) {
        this.clickAction = action;
        return this;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (background != null) background.render(context, mouseX, mouseY, deltaTicks, getX(), getY(), getRight(), getBottom());
        drawText(context, message, textColor, textAlignment, getX(), getY(), getRight(), getBottom());
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.ui(SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON, 1.4F, 1.5F));
    }

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {
        if (!down && clickAction != null) clickAction.onClick(this, click, down, doubled);
    }

    public interface ClickAction {
        void onClick(MenuButtonWidget widget, Click click, boolean down, boolean doubled);
    }
}
