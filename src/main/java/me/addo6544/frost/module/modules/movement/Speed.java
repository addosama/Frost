package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.DoubleSetting;
import me.addo6544.frost.utils.MovementUtils;

public class Speed extends Module {
    public DoubleSetting speed = new DoubleSetting("Speed", "", 1D,10D,1D);
    public Speed(){
        super("Speed", "Move Faster", Category.Movement);
        this.settings.addSetting(speed);
    }

    @EventTarget
    public void onMotion(EventMotion e){
        if (MovementUtils.isMoving()) {
            MovementUtils.setSpeed(speed.getConfigValue() / 4);
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
        }
    }

    @Override
    public String getTag() {
        return "Vanilla BHop";
    }
}
