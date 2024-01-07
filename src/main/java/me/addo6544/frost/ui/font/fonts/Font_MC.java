package me.addo6544.frost.ui.font.fonts;

import me.addo6544.frost.ui.font.FrostFR;

public class Font_MC extends FrostFR {

    public int height = mc.fontRendererObj.FONT_HEIGHT;

    @Override
    public void drawString(String text, float x, float y, int color) {
        mc.fontRendererObj.drawString(text,x,y,color,false);
    }

    @Override
    public void drawStringWithShadow(String text, float x, float y, int color) {
        mc.fontRendererObj.drawStringWithShadow(text,x,y,color);
    }

    @Override
    public void drawShadowForString(String text, float stringX, float stringY, int stringColor, int s, int b) {
        mc.fontRendererObj.drawString(text, stringX+1, stringY+1, stringColor,false);
    }

    @Override
    public int getStringWidth(String s) {
        return mc.fontRendererObj.getStringWidth(s);
    }
}
