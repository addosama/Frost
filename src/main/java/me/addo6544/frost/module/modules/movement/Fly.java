package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public Fly(){
        super("Fly", "Fly like a bird", Keyboard.KEY_5, Category.Movement);
    }

    private double startY = 0;

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        if (mc.thePlayer.posY <= startY) mc.thePlayer.jump();
    }

    @Override
    public void onEnable(){
        startY = mc.thePlayer.posY;
    }
}
