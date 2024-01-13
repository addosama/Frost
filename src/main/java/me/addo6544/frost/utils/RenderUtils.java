package me.addo6544.frost.utils;

import net.minecraft.client.gui.Gui;

public class RenderUtils {
    public static void drawRect(float x, float y, float width, float height, int color){
        Gui.drawRect((int)x, (int)y, (int)(x+width), (int)(y+height), color);
    }
}
