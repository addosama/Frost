package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.DoubleSetting;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.utils.MovementUtils;

import java.util.Arrays;

public class Speed extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "","BHop",
            Arrays.asList(
                    "BHop", "FallOnly"
            )
            );
    public DoubleSetting speed = new DoubleSetting("Speed", "", 1D,10D,1D);
    public Speed(){
        super("Speed", "Move Faster", Category.Movement);
        this.settings.addSetting(speed);
        this.settings.addSetting(mode);
    }

    @EventTarget
    public void onMotion(EventMotion e){
        if (mode.getConfigValue().equalsIgnoreCase("BHop")){
            bhopSpeed(e);
        }
        if (mode.getConfigValue().equalsIgnoreCase("FallOnly")){
            foSpeed(e);
        }
    }

    private void foSpeed(EventMotion e){
        if (MovementUtils.isMoving()){
            if (mc.thePlayer.onGround){
                MovementUtils.setSpeed(MovementUtils.getBaseMoveSpeed());
                mc.thePlayer.jump();
            } else {
                MovementUtils.setSpeed(speed.getConfigValue() / 4);
            }
        }
    }

    private void bhopSpeed(EventMotion e){
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
