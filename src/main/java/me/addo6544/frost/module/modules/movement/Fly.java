package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.utils.ChatHelper;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

public class Fly extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Bypass method",
            "Creative",
            Arrays.asList(
                    "Creative", "Spartan"
            ));

    public Fly(){
        super("Fly", "Fly like a bird", Keyboard.KEY_5, Category.Movement);
        this.settings.addSetting(mode);
    }

    private double startY = 0;

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        if (mode.getConfigValue().equalsIgnoreCase("creative"))
            mc.thePlayer.capabilities.isFlying = true;
        if (mode.getConfigValue().equalsIgnoreCase("spartan"))
            if (mc.thePlayer.posY <= startY) mc.thePlayer.jump();
    }

    @Override
    public void onEnable(){
        startY = mc.thePlayer.posY;
    }

    @Override
    public void onDisable(){
        if (mode.getConfigValue().equalsIgnoreCase("creative")) mc.thePlayer.capabilities.isFlying = false;
    }

    @Override
    public String getTag() {
        return mode.getConfigValue();
    }
}
