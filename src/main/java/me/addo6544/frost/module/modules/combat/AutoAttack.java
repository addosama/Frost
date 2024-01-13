package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import java.util.ArrayList;

public class AutoAttack extends Module {

    public BooleanSetting player = new BooleanSetting("Attack Players", false);
    public AutoAttack(){
        super("Auto Attack", "Auto attack entities", Category.Combat);
        this.settings.addSetting(player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        ArrayList<Entity> es = new ArrayList<>(mc.theWorld.getLoadedEntityList());
        for (Entity en : es){
            if (en instanceof EntityLivingBase){
                EntityLivingBase t = (EntityLivingBase) en;
                if (t.isDead || t.isInvisible() || t.equals(mc.thePlayer) || t.getDistanceToEntity(mc.thePlayer) > 3) continue;
                if (t.getHealth() > 0){
                    mc.playerController.attackEntity(mc.thePlayer, t);
                    mc.thePlayer.swingItem();
                }
            }
        }
    }
}
