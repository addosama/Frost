package me.addo6544.frost.ui.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.InputStream;

public class Fonts {
    public static FontRenderer mc = Minecraft.getMinecraft().fontRendererObj;
    public static FR HMBlack18 = new FR("HM_BLACK.ttf", Font.BOLD, 18, 7, false);
    public static FR HMRegular18 = new FR("HM_Regular.ttf", Font.PLAIN, 18, 7, false);

    public static Font getFont(String name, int size) {
        Font font;
        try {
            InputStream is = Fonts.class.getResourceAsStream("/assets/minecraft/radical/font/" + name);
            font = Font.createFont(0, is);
            font = font.deriveFont(Font.PLAIN, size);
        } catch (Exception ex) {
            System.out.println("Error loading font " + name);
            font = new Font("Arial", Font.PLAIN, size);
        }
        return font;
    }
}
