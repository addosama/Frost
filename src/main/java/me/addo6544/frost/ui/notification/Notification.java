package me.addo6544.frost.ui.notification;

import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class Notification {

    public enum NColors{
        GREEN(new Color(120,255,147)),
        ORANGE(new Color(255,190,120)),
        RED(new Color(255,120,120)),
        BLUE(new Color(120,199,255));

        private final Color c;

        NColors(Color i) {c=i;}

        public Color getC() {
            return c;
        }
    }

    private Minecraft mc = Minecraft.getMinecraft();

    private final String title;
    private final String message;

    private final NColors color;

    private final long time;//ms
    private final long sTime;


    private ScaledResolution sr = new ScaledResolution(mc);

    private float xPos = sr.getScaledWidth();

    public Notification(String title, String message, NColors color, long time){
        this.title = title;
        this.message = message;
        this.color = color;
        this.time = time;
        sTime = System.currentTimeMillis();
    }

    public Notification(String title, String message, NColors color){
        this.title = title;
        this.message = message;
        this.color = color;
        this.time = 3000;
        sTime = System.currentTimeMillis();
    }

    public void drawNotification(float yPos){

        float tW = Fonts.HMBold18.getStringWidth(title);
        float mW = Fonts.HMLight18.getStringWidth(message);

        float width = 5 + tW + 10 + 10 + mW + 10;
        float height = 30;

        float xPosFinal = sr.getScaledWidth() - 10 - width;

        if (xPos > xPosFinal && System.currentTimeMillis()-sTime <= 500){
            if (xPos - xPosFinal > 20){
                xPos = xPos -20;
            } else xPos = xPosFinal;
        }
        if (System.currentTimeMillis()-sTime > 500 && sTime+time-500 > System.currentTimeMillis()){
            xPos = xPosFinal;
        }

        if (sTime+time-500 < System.currentTimeMillis()){
            xPos = xPos + 20;
        }


        RoundedUtil.drawRound(xPos, yPos, width, height, 8, new Color(0,0,0, 179));
        RoundedUtil.drawGradientHorizontal(xPos, yPos, 5+tW+10, height, 8, color.getC(), new Color(0,0,0,0));

        Fonts.HMBold18.drawString(title, (xPos) + 5, yPos+(height-Fonts.HMBold18.FONT_HEIGHT)/2, -1);
        Fonts.HMLight18.drawString(message, (xPos+width)-(10+mW), yPos+(height-Fonts.HMLight18.FONT_HEIGHT)/2, -1);

    }

    public boolean isState(){
        return System.currentTimeMillis() <= sTime + time;
    }
}


