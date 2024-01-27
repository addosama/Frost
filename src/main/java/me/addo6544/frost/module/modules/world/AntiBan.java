package me.addo6544.frost.module.modules.world;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.modules.movement.Sprint;

public class AntiBan extends Module {
    public AntiBan(){
        super("Anti Ban", "funny", Category.World);
    }

    @Override
    public void onEnable(){
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            if (!m.isState()) continue;
            if (m.getCategory().equals(Category.Render) || m instanceof AntiBan || m instanceof Sprint) continue;
            m.setState(false);
        }
    }

    @Override
    public String getTag() {
        return "?";
    }
}
