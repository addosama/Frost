package me.addo6544.frost.ui.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.InputStream;

public class Fonts {
    public static FontRenderer mc = Minecraft.getMinecraft().fontRendererObj;
    public static FR HMBlack18;
    public static FR HMRegular18;
    public static FR HMLight42;
    public static FR HMLight18;
    public static FR HMBold24;
    public static FR HMBold18;
    public static FR HMRegular12;
    public static FR HMBlack36;


    public static void initFonts(){
        HMBlack18 = new FR("HM_BLACK.ttf", Font.BOLD, 18, 7, true);
        HMRegular18 = new FR("HM_REGULAR.ttf", Font.PLAIN, 18, 7, true);
        HMLight42 = new FR("HM_LIGHT.ttf", Font.PLAIN, 42,7,true);
        HMLight18 = new FR("HM_LIGHT.ttf", Font.PLAIN, 18,7,true);
        HMBold24 = new FR("HM_BOLD.ttf", Font.PLAIN, 24, 7, true);
        HMBold18 = new FR("HM_BOLD.ttf", Font.PLAIN, 18, 7, true);
        HMRegular12 = new FR("HM_REGULAR.ttf", Font.PLAIN, 12, 7, true);
        HMBlack36 = new FR("HM_BLACK.ttf", Font.PLAIN, 36, 7, true);
    }

    public static Font getFont(String name, int size) {
        Font font;
        try {
            InputStream is = Fonts.class.getResourceAsStream("/assets/minecraft/frost/fonts/" + name);
            font = Font.createFont(0, is);
            font = font.deriveFont(Font.PLAIN, size);
        } catch (Exception ex) {
            System.out.println("Error loading font " + name);
            font = new Font("Arial", Font.PLAIN, size);
        }
        return font;
    }
}
