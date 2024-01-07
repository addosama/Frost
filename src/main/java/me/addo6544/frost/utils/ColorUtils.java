package me.addo6544.frost.utils;

import java.awt.*;

public class ColorUtils {
    public static int HSBToINT(float[] hsb){
        return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
    }

    public static int HSBToINT(float h, float s, float b){
        return Color.HSBtoRGB(h, s, b);
    }
}
