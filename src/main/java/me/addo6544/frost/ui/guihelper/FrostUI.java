package me.addo6544.frost.ui.guihelper;

import me.addo6544.frost.ui.guihelper.widget.Widget;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public abstract class FrostUI extends GuiScreen{
    public ArrayList<Widget> widgets;
    public final boolean pauseGame;
    public FrostUI(boolean pauseGame){
        widgets = new ArrayList<>();
        this.pauseGame = pauseGame;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return pauseGame;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Widget widget : widgets){
            widget.drawWidget(mouseX, mouseY, partialTicks);
        }
    }
}
