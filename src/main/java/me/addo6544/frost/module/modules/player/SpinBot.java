package me.addo6544.frost.module.modules.player;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.settings.DoubleSetting;
import me.addo6544.frost.utils.RotationUtil;

public class SpinBot extends Module {
    public DoubleSetting p = new DoubleSetting("Pitch", "", 0,360,180);
    public DoubleSetting s = new DoubleSetting("Speed", "", 0,10,1);
    public SpinBot(){
        super("Spin Bot", "背背背起了行囊", Category.Player);
        this.settings.addSetting(p);
        this.settings.addSetting(s);
    }

    @EventTarget
    public void onMotion(EventMotion e){
        //mc.thePlayer.rotationPitch = p.getConfigValue().floatValue();
        float yaw = mc.thePlayer.rotationYawHead + s.getConfigValue().floatValue();
        if (yaw >= 360){
            float y1 = yaw - 360;
            yaw = 0 + y1;
        }
        //mc.thePlayer.rotationYaw = yaw;

        e.setRotations(yaw,p.getConfigValue().floatValue());
        RotationUtil.setVisualRotations(yaw,p.getConfigValue().floatValue());
    }
}
