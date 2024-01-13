package me.addo6544.frost.ui.guihelper.widget;

public abstract class Widget {
    public float x;
    public float y;
    public float width;
    public float height;

    public abstract void drawWidget();
    public Widget(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
