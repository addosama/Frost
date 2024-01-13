package me.addo6544.frost.ui.guihelper.widget.impl.base;

import me.addo6544.frost.ui.guihelper.widget.Widget;
import me.addo6544.frost.utils.RenderUtils;

import java.awt.*;

public class CRect extends Widget {

    public Color color;

    public CRect(float x, float y, float width, float height, Color color){
        super(x,y,width,height);
        this.color = color;
    }
    @Override
    public void drawWidget() {
        RenderUtils.drawRect(x,y,width,height,color.getRGB());
    }
}
