package me.addo6544.frost.module.modules.other;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;

public class Panic extends Module {
    public Panic(){
        super("Panic", "Disable all modules", Category.Other);
    }

    @Override
    public void onEnable() {
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            if (m.isState()) m.setState(false);
        }
    }
}
