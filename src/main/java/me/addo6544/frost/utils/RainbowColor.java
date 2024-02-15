package me.addo6544.frost.utils;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class RainbowColor {

    private Color rainbow;
    private int rainbowTickc;

    public RainbowColor(){
        rainbow = new Color(255,0,0);
        rainbowTickc = 0;
        Frost.INSTANCE.eventManager.register(this);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        if (++rainbowTickc > 100) {
            rainbowTickc = 0;
        }

        rainbow = new Color(
                Color.HSBtoRGB(
                        (float) ((double) Minecraft.getMinecraft().thePlayer.ticksExisted / 50.0
                        - Math.sin((double) rainbowTickc / 40.0 * 1.4)) % 1.0f, 1.0f, 1.0f));
    }
}
