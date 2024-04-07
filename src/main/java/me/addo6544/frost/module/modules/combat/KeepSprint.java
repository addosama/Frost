package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventKeepSprint;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;

public class KeepSprint extends Module {
    public KeepSprint(){
        super("Keep Sprint", "Always Sprint", Category.Combat);
    }

    @EventTarget
    public void onEvent(EventKeepSprint e){
        e.setCancelled(true);
    }
}
