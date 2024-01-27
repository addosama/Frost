package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.ModeSetting;

import java.util.Arrays;

public class NoSlow extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Bypass method",
            "Remove",
            Arrays.asList("Remove")
            );
    public NoSlow(){
        super("No Slow", "Allows you to move quickly while using items", Category.Movement);
    }

    @Override
    public String getTag() {
        return mode.getConfigValue();
    }
}
