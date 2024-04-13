package me.addo6544.frost.ui.clickgui.modern;

import me.addo6544.frost.module.Module;
import me.addo6544.frost.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class WindowClickGui extends GuiScreen {

    private float x,y,width,height;
    private ScaledResolution sr;
    private pageCategory page;
    private ArrayList<Module> activeModules;

    public WindowClickGui(){
        this.width = 400;
        this.height = 250;
        this.x = 10;
        this.y = 10;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        sr = new ScaledResolution(Minecraft.getMinecraft());
        this.x = (sr.getScaledWidth()-width)/2;
        this.y = (sr.getScaledHeight()-height)/2;

        //Background
        RenderUtil.drawWHRect(x,y,width,height,new Color(0, 0, 0, 229).getRGB());
        //TitleBar
        RenderUtil.drawWHRect(x,y,width,20,new Color(26,26,26).getRGB());
        RenderUtil.drawWHRect(x,y+19.5F, width, 0.5F, new Color(0,56,87).getRGB());


    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}

class pageCategory{

}
