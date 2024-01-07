package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import org.lwjgl.input.Keyboard;

public class FastLiquid extends Module {
    public FastLiquid(){
        super("Fast Liquid", "Move quickly in liquid", Keyboard.KEY_NONE, Category.Movement);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        mc.thePlayer.inWater = false;
    }
}
