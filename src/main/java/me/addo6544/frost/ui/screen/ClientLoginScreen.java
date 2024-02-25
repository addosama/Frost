package me.addo6544.frost.ui.screen;

import me.addo6544.frost.auth.FrostUser;
import me.addo6544.frost.auth.UserRanks;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ChatAllowedCharacters;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientLoginScreen extends GuiScreen {

    private final boolean connect;
    private Minecraft mcIn;
    private String servername;
    private int serverPort;

    public inputField username;
    public inputField password;
    public button login;

    private String message = "";

    private ArrayList<widget> widgets;

    public ClientLoginScreen(Minecraft mcIn, String serverName, int serverPort){
        connect = true;
        this.mcIn = mcIn;
        this.servername = serverName;
        this.serverPort = serverPort;
    }

    public ClientLoginScreen(){
        connect = false;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.username = new inputField(
                width*0.025F,height*0.24f,width*0.275f,25,"","USERNAME",false,this
        );
        this.password = new inputField(
                width*0.025F,height*0.24f+25+10,width*0.275f,25,"","PASSWORD",true,this
        );
        this.login = new button(
                width*0.025F,height*0.56f, 62.5F, 25, false,this
        );

        this.widgets = new ArrayList<>(Arrays.asList(
                username,password,login
        ));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (widget w : widgets){
            w.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (widget w : widgets){
            if (w.isHovering(mouseX,mouseY)) {
                w.setActive(true);
                w.onClick(mouseButton);
            } else w.setActive(false);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        float width = sr.getScaledWidth();
        float height = sr.getScaledHeight();

        RenderUtil.drawWHRect(0,0,width*0.375F,height, new Color(250,250,250).getRGB());

        if (!message.isEmpty()){
            Fonts.HMRegular18.drawCenteredString(message, width*0.1875F,5, new Color(255, 51, 51).getRGB(),true);
        }

        Fonts.HMBlack24.drawString("FROST CLIENT", width*0.025F, height*0.08F, new Color(0,0,0).getRGB()  );
        Fonts.HMRegular12.drawString("You need login to continue", width*0.025F, height*0.08F+Fonts.HMBlack24.FONT_HEIGHT, new Color(0,0,0).getRGB());

        username.set(width*0.025F,height*0.24f,width*0.275f,25,"USERNAME",false);
        password.set(width*0.025F,height*0.24f+25+10,width*0.275f,25,"PASSWORD",true);
        login.set(width*0.025F,height*0.56f, 62.5F, 25, false);

        for (widget w : widgets){
            w.drawWidget(mouseX,mouseY);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void gotoMainMenu(){
        if (connect){
            mc.displayGuiScreen(new GuiConnecting(new ClientMainMenu(), mcIn, servername, serverPort));
        } else {
            mc.displayGuiScreen(new ClientMainMenu());
        }
    }
}

class button extends widget{
    public boolean textButton;

    public button(float x, float y, float width, float height, boolean isTextButton, ClientLoginScreen screen){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textButton = isTextButton;
        this.screen = screen;
    }
    public void set(float x, float y, float width, float height, boolean isTextButton){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textButton = isTextButton;
    }

    @Override
    public void drawWidget(int mouseX, int mouseY){
        if (textButton){
            if (isHovering(mouseX, mouseY)){
                Fonts.HMRegular18.drawCenteredString("LOGIN", x+(width/2), y+(height/2), new Color(89, 187, 248).getRGB(), true);
            } else {
                Fonts.HMRegular18.drawCenteredString("LOGIN", x+(width/2), y+(height/2), new Color(0, 157, 255).getRGB(), true);
            }
        } else {
            if (isHovering(mouseX, mouseY)){
                RoundedUtil.drawRound(x,y,width,height,2,new Color(91, 206, 255));
                Fonts.HMRegular18.drawCenteredString("LOGIN", x+(width/2), y+(height/2), -1, true);
            } else {
                RoundedUtil.drawRound(x,y,width,height,2,new Color(0,174,255));
                Fonts.HMRegular18.drawCenteredString("LOGIN", x+(width/2), y+(height/2), -1, true);
            }
        }
    }

    @Override
    void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    void onClick(int mouseButton) {
        String username = screen.username.getText();
        String password = screen.password.getText();
        if (username.isEmpty()){
            screen.setMessage("Please input your username");
            return;
        }
        if (password.isEmpty()){
            screen.setMessage("Please input your password");
            return;
        }

        Frost.INSTANCE.setUser(new FrostUser(username, "uwu", UserRanks.User));

        screen.gotoMainMenu();
    }
}

class inputField extends widget{

    public boolean passwordInput;
    public String tip;
    public String text;

    public inputField(float x, float y, float width, float height, String text, String tip, boolean isPasswordInput, ClientLoginScreen screen){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tip = tip;
        this.text = text;
        this.screen = screen;
        this.passwordInput = isPasswordInput;
    }

    public void drawWidget(int mouseX, int mouseY){
        Fonts.HMRegular12.drawString(tip, x, y, new Color(0,0,0).getRGB());
        RoundedUtil.drawRoundOutline(
                x,y+height*0.08f+Fonts.HMRegular12.FONT_HEIGHT, width, (height*0.92f)-Fonts.HMRegular12.FONT_HEIGHT,2,0.5f,
                new Color(232,232,232),
                active ? new Color(0, 140, 255) : new Color(232,232,232)
        );

        String displayText = this.text;
        if (passwordInput) displayText = StringUtils.repeat("*", this.text.length());

        Fonts.HMRegular18.drawString(displayText, x+2, (y+height*0.08f+Fonts.HMRegular12.FONT_HEIGHT)+(((height*0.92f)-Fonts.HMRegular12.FONT_HEIGHT)-Fonts.HMRegular18.FONT_HEIGHT), new Color(0,0,0).getRGB());

    }

    @Override
    void keyTyped(char typedChar, int keyCode) {
        if (active){
            if (keyCode == Keyboard.KEY_BACK){
                if (text.isEmpty()) return;
                this.text = this.text.substring(0,text.length()-1);
                return;
            }
            if (!ChatAllowedCharacters.isAllowedCharacter(typedChar)) return;
            this.text = this.text+typedChar;
        }
    }

    @Override
    void onClick(int mouseButton) {
        this.active = true;
    }

    public void set(float x, float y, float width, float height, String tip, boolean isPasswordInput){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tip = tip;
        this.passwordInput = isPasswordInput;
    }

    public String getText() {
        return text;
    }
}

abstract class widget{
    public float x;
    public float y;
    public float width;
    public float height;
    public ClientLoginScreen screen;
    public boolean active = false;

    abstract void drawWidget(int mouseX, int mouseY);
    abstract void keyTyped(char typedChar, int keyCode);
    abstract void onClick(int mouseButton);
    public boolean isHovering(int mouseX, int mouseY){
        if (mouseX >= x && mouseX <= x+width){
            if (mouseY >= y && mouseY <= y+height){
                return true;
            }
        }
        return false;
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

    public void setActive(boolean active) {
        this.active = active;
    }
}