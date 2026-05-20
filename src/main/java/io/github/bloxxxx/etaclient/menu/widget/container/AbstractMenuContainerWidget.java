package io.github.bloxxxx.etaclient.menu.widget.container;

import io.github.bloxxxx.etaclient.menu.widget.MenuWidget;
import io.github.bloxxxx.etaclient.menu.widget.background.MenuWidgetBackground;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenuContainerWidget extends MenuWidget {

    protected final List<MenuWidget> children;
    private @Nullable MenuWidgetBackground background = null;
    public AbstractMenuContainerWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
        children = new ArrayList<>();
    }

    public void addChild(MenuWidget child) {
        children.add(child);
    }

    public void clearChildren() {
        children.clear();
    }

    public void setBackground(MenuWidgetBackground background) {
        this.background = background;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        updateChildPositions(children);
        if (background != null) background.render(context, mouseX, mouseY, deltaTicks, getX(), getY(), getRight(), getBottom());
        preRender(context, mouseX, mouseY, deltaTicks);
        context.getMatrices().pushMatrix();
        context.enableScissor(getX(), getY(), getRight(), getBottom());
        context.getMatrices().translate(getX(), getY());
        for (MenuWidget child : children) {
            child.render(context, mouseX - getX(), mouseY - getY(), deltaTicks);
        }
        context.disableScissor();
        context.getMatrices().popMatrix();
        postRender(context, mouseX, mouseY, deltaTicks);
    }
    abstract void updateChildPositions(List<MenuWidget> children);
    abstract void preRender(DrawContext context, int mouseX, int mouseY, float deltaTicks);
    abstract void postRender(DrawContext context, int mouseX, int mouseY, float deltaTicks);

    @Override
    public void onMouseClick(Click click, boolean down, boolean doubled) {
        Click newClick = transformClick(click);
        for (MenuWidget child : children) {
            if (down) {
                child.mouseClicked(newClick, doubled);
            } else {
                child.mouseReleased(newClick);
            }
        }
    }

    @Override
    protected void onDrag(Click click, double offsetX, double offsetY) {
        super.onDrag(click, offsetX, offsetY);
        for (MenuWidget child : children) {
            child.mouseDragged(transformClick(click), offsetX, offsetY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        for (MenuWidget child : children) {
            child.mouseScrolled(mouseX - getX(), mouseY - getY(), horizontalAmount, verticalAmount);
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        super.mouseMoved(mouseX, mouseY);
        for (MenuWidget child : children) {
            child.mouseMoved(mouseX - getX(), mouseY - getY());
        }
    }

    private Click transformClick(Click click) {
        return new Click(click.x() - getX(), click.y() - getY(), click.buttonInfo());
    }
}
