package me.addo6544.frost.module.modules.world;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;

public class AutoBan extends Module {
    public AutoBan(){
        super("Auto BAN", "GET BAN NOW!!!", Category.World);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.thePlayer.jump();
        mc.thePlayer.heal(1);
        mc.thePlayer.attackEntityAsMob(mc.theWorld.loadedEntityList.get(1));
    }
}
