package me.addo6544.frost.utils;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class RainbowColor {
    private int rainbowTick;

    private float s;
    private float b;

    private boolean state = false;

    public RainbowColor(float s, float b){
        this.s = s;
        this.b = b;
        rainbowTick = 0;
    }

    public RainbowColor(){
        this.s = 1;
        this.b = 1;
        rainbowTick = 0;
    }

    public void register(){
        Frost.INSTANCE.eventManager.register(this);
        state = true;
    }

    public void unregister(){
        state = false;
        Frost.INSTANCE.eventManager.unregister(this);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        updateRainbowTick();
    }

    public void updateRainbowTick(){
        if (!state) return;
        ++rainbowTick;
        if (rainbowTick > 100) rainbowTick = 0;
    }

    public Color getRainbow(int index){
        int rbT = rainbowTick;
        rbT = rbT + index;
        if (rbT > 100) rbT = rbT - 100;

        return new Color(
                Color.HSBtoRGB(
                        (float) ((double) Minecraft.getMinecraft().thePlayer.ticksExisted / 50.0
                                - Math.sin((double) rbT / 40.0 * 1.4)) % 1.0f, s, b));
    }

    public float getS() {
        return s;
    }

    public float getB() {
        return b;
    }

    public void setS(float s) {
        this.s = s;
    }

    public void setB(float b) {
        this.b = b;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
