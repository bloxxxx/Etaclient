package io.github.bloxxxx.etaclient.menu.impl;

import io.github.bloxxxx.etaclient.menu.Menu;
import io.github.bloxxxx.etaclient.menu.widget.IntSliderWidget;
import io.github.bloxxxx.etaclient.menu.widget.MenuButtonWidget;
import io.github.bloxxxx.etaclient.menu.widget.ToggleButtonWidget;
import io.github.bloxxxx.etaclient.menu.widget.container.ScrollMenuContainerWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class TestMenu extends Menu {
    public TestMenu(@Nullable Screen parent) {
        super(Text.literal("TestMenu"), parent);
    }

    @Override
    protected void doInit() {
        ScrollMenuContainerWidget containerWidget = new ScrollMenuContainerWidget(
                20, 20,
                300, 300
        ).setGap(5);
        containerWidget.addChild(new ToggleButtonWidget(
                0, 0,
                50, 20,
                true
        ));
        containerWidget.addChild(new IntSliderWidget(
                0, 0,
                100, 20,
                -10, 10, 0
        ));
        containerWidget.addChild(new MenuButtonWidget(
                0, 0,
                50, 20,
                Text.literal("HELLO")
        ));

        for (int i = 0; i < 5; i++) {
            containerWidget.addChild(new MenuButtonWidget(
                    0, 0,
                    50, 20,
                    Text.literal("HELLO")
            ));
        }

        addDrawableChild(containerWidget);
    }

    @Override
    protected void doRender(DrawContext context, int mouseX, int mouseY, float deltaTicks) {

    }
}
