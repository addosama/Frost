package me.addo6544.frost.ui.screen;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventInit;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;

import java.awt.*;

public class ClientSplashScreen extends GuiScreen {
    private final boolean connect;
    private Minecraft mcIn;
    private String servername;
    private int serverPort;
    private FR bb42;
    private FR r14;
    public ClientSplashScreen(){
        connect = false;

        Frost.INSTANCE.eventManager.register(this);
    }

    public ClientSplashScreen(Minecraft mcIn, String serverName, int serverPort){
        connect = true;
        this.mcIn = mcIn;
        this.servername = serverName;
        this.serverPort = serverPort;


        Frost.INSTANCE.eventManager.register(this);
    }

    @Override
    public void initGui() {
        this.bb42 = new FR("HM_BLACK.ttf", Font.BOLD, 42, 7, false);
        this.r14 = new FR("HM_REGULAR.ttf", Font.PLAIN, 14, 7, false);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (Frost.INSTANCE.loaded){
            Fonts.initFonts();
            if (connect) {
                mc.displayGuiScreen(new GuiConnecting(new ClientMainMenu(), mcIn, servername, serverPort));
            } else {
                mc.displayGuiScreen(new ClientMainMenu());
            }
            Frost.INSTANCE.eventManager.unregister(this);
            return;
        }


        RenderUtil.drawRect(0,0,0,0,new Color(0,0,0).getRGB());
        bb42.drawCenteredString("Starting " + Frost.CLIENT_NAME, width/2, (height/2)-bb42.FONT_HEIGHT-1, -1, false);
        r14.drawCenteredString(Frost.INSTANCE.getLoadState(), width/2, (height/2)+1+r14.FONT_HEIGHT, -1, false);
    }


}
