package io.github.bloxxxx.etaclient.menu;

import io.github.bloxxxx.etaclient.util.McUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public abstract class Menu extends Screen {

    protected Screen parent;
    public Menu(Text title, @Nullable Screen parent) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        doInit();
    }
    protected abstract void doInit();

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        doRender(context, mouseX, mouseY, deltaTicks);
    }
    protected abstract void doRender(DrawContext context, int mouseX, int mouseY, float deltaTicks);

    @Override
    public void close() {
        McUtil.setScreen(parent);
    }
}
