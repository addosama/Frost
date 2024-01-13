package me.addo6544.frost.ui.guihelper.widget;

import me.addo6544.frost.ui.guihelper.FrostUI;

public abstract class Widget {
    public float x;
    public float y;
    public float width;
    public float height;
    public final FrostUI ui;

    public abstract void drawWidget(int mouseX, int mouseY, float partialTicks);
    public Widget(float x, float y, float width, float height, FrostUI ui){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ui = ui;
    }

}
