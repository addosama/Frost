package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventPacketReceive;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.settings.IntegerSetting;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class Velocity extends Module {
    public IntegerSetting h = new IntegerSetting("Horizontal", "Horizontal Velocity", 0, 100, 0);
    public IntegerSetting v = new IntegerSetting("Vertical", "Vertical Velocity", 0, 100, 0);
    public Velocity(){
        super("Velocity", "Remove KnockBack", Category.Combat);
        this.settings.addSetting(h);
        this.settings.addSetting(v);
    }

    @EventTarget
    public void onPacketReceive(EventPacketReceive e){
        if (e.getPacket() instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity p = (S12PacketEntityVelocity) e.getPacket();
            if (mc.thePlayer != null && p.getEntityID() == mc.thePlayer.getEntityId()){
                p.motionX *= h.getConfigValue() / 100;
                p.motionZ *= h.getConfigValue() / 100;
                p.motionY *= v.getConfigValue() / 100;
            }
        }
    }

    @Override
    public String getTag() {
        return "Vanilla";
    }
}
