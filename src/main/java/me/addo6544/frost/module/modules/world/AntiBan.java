package me.addo6544.frost.module.modules.world;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.modules.movement.Sprint;

public class AntiBan extends Module {
    public AntiBan(){
        super("Anti Ban", "99% Work", Category.World);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate){
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            if (!m.isState()) continue;
            if (m.getCategory().equals(Category.Render) || m instanceof AntiBan || m instanceof Sprint) continue;
            m.setState(false);
        }
    }

    @Override
    public String getTag() {
        return "啊?";
    }
}
