package me.addo6544.frost.ui.guihelper.widget.impl;

import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.guihelper.widget.Widget;
import me.addo6544.frost.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.IOException;

public class CDebug extends Widget {
    private final FontRenderer fr;
    private int mouseX, mouseY;
    private float partialTicks;
    private Color c;

    //Widgets

    public CDebug(FrostUI ui) {
        super(0, 0, ui.width, ui.height, ui);
        fr = Minecraft.getMinecraft().fontRendererObj;
    }

    @Override
    public void drawWidget() {
        RenderUtil.drawRect(mouseX,0,1,ui.height, c.getRGB());
        RenderUtil.drawRect(0,mouseY, ui.width, 1, c.getRGB());
        int h = fr.FONT_HEIGHT;
        fr.drawStringWithShadow("FROST UI DEBUG INFOMATION", 1, 50, -1);
        fr.drawStringWithShadow("mouseX:" + mouseX, 2, 50+h, -1);
        fr.drawStringWithShadow("mouseY:" + mouseY, 2, 50+h*2, -1);
        fr.drawStringWithShadow("partialTicks:" + partialTicks, 2, 50+h*3, -1);
    }

    @Override
    public void update(int mouseX, int mouseY, float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
        c = Color.GREEN;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }
}
