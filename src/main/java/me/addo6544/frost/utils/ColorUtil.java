package me.addo6544.frost.utils;


import java.awt.Color;

public enum ColorUtil {
    BLACK(-16711423),
    BLUE(-12028161),
    DARKBLUE(-12621684),
    GREEN(-9830551),
    DARKGREEN(-9320847),
    WHITE(-65794),
    AQUA(-7820064),
    DARKAQUA(-12621684),
    GREY(-9868951),
    DARKGREY(-14342875),
    RED(-65536),
    DARKRED(-8388608),
    ORANGE(-29696),
    DARKORANGE(-2263808),
    YELLOW(-256),
    DARKYELLOW(-2702025),
    MAGENTA(-18751),
    DARKMAGENTA(-2252579);

    public int c;

    private ColorUtil(int co) {
        this.c = co;
    }

    public static int getColor(Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static int getColor(int brightness) {
        return getColor(brightness, brightness, brightness, 255);
    }

    public static int getColor(int brightness, int alpha) {
        return getColor(brightness, brightness, brightness, alpha);
    }

    public static int getColor(int red, int green, int blue) {
        return getColor(red, green, blue, 255);
    }

    public static int getColor(int red, int green, int blue, int alpha) {
        byte color = 0;
        int color1 = color | alpha << 24;
        color1 |= red << 16;
        color1 |= green << 8;
        color1 |= blue;
        return color1;
    }

    //thanks thomaz <3
    public static int blendColours(final int[] colours, final double progress) {
        final int size = colours.length;
        if (progress == 1.f) return colours[0];
        else if (progress == 0.f) return colours[size - 1];
        final double mulProgress = Math.max(0, (1 - progress) * (size - 1));
        final int index = (int) mulProgress;
        return fadeBetween(colours[index], colours[index + 1], mulProgress - index);
    }
    public static int fadeBetween(int startColour, int endColour, double progress) {
        if (progress > 1) progress = 1 - progress % 1;
        return fadeTo(startColour, endColour, progress);
    }

    public static int fadeBetween(int startColour, int endColour, long offset) {
        return fadeBetween(startColour, endColour, ((System.currentTimeMillis() + offset) % 2000L) / 1000.0);
    }

    public static int fadeBetween(int startColour, int endColour) {
        return fadeBetween(startColour, endColour, 0L);
    }

    public static int fadeTo(int startColour, int endColour, double progress) {
        double invert = 1.0 - progress;
        int r = (int) ((startColour >> 16 & 0xFF) * invert +
                (endColour >> 16 & 0xFF) * progress);
        int g = (int) ((startColour >> 8 & 0xFF) * invert +
                (endColour >> 8 & 0xFF) * progress);
        int b = (int) ((startColour & 0xFF) * invert +
                (endColour & 0xFF) * progress);
        int a = (int) ((startColour >> 24 & 0xFF) * invert +
                (endColour >> 24 & 0xFF) * progress);
        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                (b & 0xFF);
    }

    //rainbow
    /*
    public static int rainbow(int delay, long timeOffset) {
        OldHUD Hud = (OldHUD) Radical.instance.moduleManager.getModule(OldHUD.class);
        double rainbowState = Math.ceil((System.currentTimeMillis() + timeOffset) / 8 + delay / 20.0D);
        rainbowState %= 360.0;
        Color alColor1 = new Color(Hud.alColor1R.getCurrentValueInt(), Hud.alColor1G.getCurrentValueInt(), Hud.alColor1B.getCurrentValueInt());
        Color alColor2 = new Color(Hud.alColor2R.getCurrentValueInt(), Hud.alColor2G.getCurrentValueInt(), Hud.alColor2B.getCurrentValueInt());
        return Color.getHSBColor((float) rainbowState / 360f, getColorHSB(alColor1)[1], getColorHSB(alColor1)[2]).getRGB();
    }

     */

    public static Color getColorLighterRGB(Color color, int v){
        int r,g,b;

        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();

        r = r+v;
        g = g+v;
        b = b+v;

        if(r > 255) r = 255;
        if(g > 255) g = 255;
        if(b > 255) b = 255;

        return new Color(r,g,b);
    }

    public static Color getColorLighterHSB(Color color, int percent){
        float p = percent/100f;
        if (p > 1 || p < 0) return new Color(0,0,0,0);
        float[] hsb = getColorHSB(color);
        return new Color(
                hsb[0],
                hsb[1],
                hsb[2] + percent > 1 ? 1 : hsb[2] + percent
        );
    }

    public static Color getClientDarkerHSB(Color color, int percent){
        float p = percent/100f;
        if (p > 1 || p < 0) return new Color(0,0,0,0);
        float[] hsb = getColorHSB(color);
        return new Color(
                hsb[0],
                hsb[1],
                hsb[2] - percent < 0 ? 0 : hsb[2] - percent
        );
    }

    public static Color getColorDarkerRGB(Color color, int v){
        int r,g,b;

        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();

        r = r-v;
        g = g-v;
        b = b-v;

        if(r < 0) r = 0;
        if(g < 0) g = 0;
        if(b < 0) b = 0;

        return new Color(r,g,b);
    }

    public static float[] getColorHSB(Color color) {
        Color currentColor = color;
        return Color.RGBtoHSB(currentColor.getRed(),
                currentColor.getGreen(),
                currentColor.getBlue(),null);
    }

    public static int HSBToINT(float[] hsb){
        return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
    }

    public static int HSBToINT(float h, float s, float b){
        return Color.HSBtoRGB(h, s, b);
    }

    public static Color tripleColor(int rgbValue) {
        return tripleColor(rgbValue, 1);
    }

    public static Color tripleColor(int rgbValue, float alpha) {
        alpha = Math.min(1, Math.max(0, alpha));
        return new Color(rgbValue, rgbValue, rgbValue, (int) (255 * alpha));
    }
}
