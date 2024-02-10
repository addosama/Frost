package me.addo6544.frost.module.modules.player;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.DoubleSetting;

public class RotationSetter extends Module {
    public DoubleSetting y = new DoubleSetting("Y", "", 0,360,0);
    public DoubleSetting p = new DoubleSetting("P", "", 0,360,0);
    public RotationSetter(){
        super("Rotation Setter", "", Category.Player);
        this.settings.addSetting(y);
        this.settings.addSetting(p);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.thePlayer.rotationYaw = y.getConfigValue().floatValue();
        mc.thePlayer.rotationPitch = p.getConfigValue().floatValue();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
