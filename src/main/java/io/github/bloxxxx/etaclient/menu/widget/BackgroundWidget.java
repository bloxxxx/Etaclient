package io.github.bloxxxx.etaclient.menu.widget;

import io.github.bloxxxx.etaclient.menu.widget.background.MenuWidgetBackground;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;

public class BackgroundWidget extends MenuWidget {

    private final MenuWidgetBackground background;

    public BackgroundWidget(int x, int y, int width, int height, @NotNull MenuWidgetBackground background) {
        super(x, y, width, height);
        this.background = background;
    }

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {

    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        background.render(context, mouseX, mouseY, deltaTicks, getX(), getY(), getRight(), getBottom());
    }
}
