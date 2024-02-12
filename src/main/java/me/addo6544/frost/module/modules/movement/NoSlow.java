package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.SettingGroup;
import me.addo6544.frost.module.setting.settings.DoubleSetting;
import me.addo6544.frost.module.setting.settings.ModeSetting;

import java.util.Arrays;

public class NoSlow extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Bypass method",
            "Edit",
            Arrays.asList("Edit")
            );

    public DoubleSetting swordStrafe = new DoubleSetting("Move Strafe", "", 0, 1, 0);
    public DoubleSetting swordForward = new DoubleSetting("Move Forward", "", 0, 1, 0);

    public DoubleSetting foodStrafe = new DoubleSetting("Move Strafe", "", 0, 1, 0);
    public DoubleSetting foodForward = new DoubleSetting("Move Forward", "", 0, 1, 0);

    public DoubleSetting bowStrafe = new DoubleSetting("Move Strafe", "", 0, 1, 0);
    public DoubleSetting bowForward = new DoubleSetting("Move Forward", "", 0, 1, 0);
    public NoSlow(){
        super("No Slow", "Allows you to move quickly while using items", Category.Movement);

        this.settings.addSetting(new SettingGroup(
                "Sword",Arrays.asList(
                        swordStrafe,swordForward
        )
        ));

        this.settings.addSetting(new SettingGroup(
                "Food",Arrays.asList(
                foodStrafe,foodForward
        )
        ));

        this.settings.addSetting(new SettingGroup(
                "Bow",Arrays.asList(
                bowStrafe,bowForward
        )
        ));
    }

    @Override
    public String getTag() {
        return mode.getConfigValue();
    }
}
