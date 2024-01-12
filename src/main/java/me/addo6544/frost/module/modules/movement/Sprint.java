package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;

public class Sprint extends Module {
    public Sprint(){
        super("Sprint", "Auto sprint for you", Category.Movement);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        mc.thePlayer.setSprinting(true);
    }
}
