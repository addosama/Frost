package me.addo6544.frost.ui.frostinterface.widgets;

import me.addo6544.frost.ui.frostinterface.InterfaceSettings;
import net.minecraft.client.Minecraft;

public abstract class InterfaceWidget {

    protected Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private String description;
    private boolean state;
    private float x,y,width,height;
    private final boolean inGameWidget;
    public InterfaceSettings settings;

    public InterfaceWidget(String name, String description, float x, float y, float width, float height, boolean inGameWidget){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.inGameWidget = inGameWidget;
        this.settings = new InterfaceSettings(false);
        this.state = false;
    }

    public boolean isHovering(float mouseX, float mouseY){
        return
                mouseX >= x && mouseX <= x+width &&
                        mouseY >= y && mouseY <= y+height;
    }

    public abstract void drawWidget(float mouseX, float mouseY, float pTicks);


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isInGameWidget() {
        return inGameWidget;
    }
}
