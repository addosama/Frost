package me.addo6544.frost.ui.guihelper.widget;

import me.addo6544.frost.ui.guihelper.FrostUI;

import java.io.IOException;

public abstract class Widget {
    public float x;
    public float y;
    public float width;
    public float height;
    public final FrostUI ui;

    public abstract void drawWidget();
    public Widget(float x, float y, float width, float height, FrostUI ui){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ui = ui;
    }

    public abstract void update(int mouseX, int mouseY, float partialTicks);

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException;

}
