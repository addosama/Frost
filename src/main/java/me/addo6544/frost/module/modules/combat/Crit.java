package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Crit extends Module {
    public Crit() {
        super("Criticals", "", Category.Combat);
    }

    @EventTarget
    public void onMotion(EventMotion e){
        if (mc.objectMouseOver.entityHit != null && mc.thePlayer.onGround) {
            if (mc.objectMouseOver.entityHit.hurtResistantTime > 0) {
                for (double offset : new double[]{0.006253453, 0.002253453, 0.001253453}) {
                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + offset, mc.thePlayer.posZ, false));
                }
            }
        }
    }
}
