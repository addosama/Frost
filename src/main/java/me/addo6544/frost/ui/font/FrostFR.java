package me.addo6544.frost.ui.font;

import net.minecraft.client.Minecraft;

public abstract class FrostFR {
    protected Minecraft mc = Minecraft.getMinecraft();
    public int height;
    public abstract void drawString(String text, float x, float y, int color);
    public abstract void drawStringWithShadow(String text, float x, float y, int color);
    public abstract void drawShadowForString(String text, float stringX, float stringY, int stringColor, int s, int b);
    public abstract int getStringWidth(String s);
}
