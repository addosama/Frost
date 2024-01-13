package me.addo6544.frost.ui.guihelper.widget.impl;

import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.guihelper.widget.Widget;

import java.io.IOException;

public class CButton extends Widget {
    private boolean hovering = false;
    public CButton(float x, float y, float width, float height, FrostUI ui){
        super(x, y, width, height, ui);
    }

    @Override
    public void update(int mouseX, int mouseY, float partialTicks) {
        if (mouseX >= x && mouseX <= x+width){
            if (mouseY >= y && mouseY <= y+height){
                hovering = true;
            } else hovering = false;
        }else hovering = false;
    }

    @Override
    public void drawWidget() {

    }


    public void onClick(int mouseX, int mouseY, int mouseButton){

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (hovering) onClick(mouseX, mouseY, mouseButton);
    }
}
