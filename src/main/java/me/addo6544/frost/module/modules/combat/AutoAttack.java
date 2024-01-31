package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.modules.render.TargetHUDMod;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import me.addo6544.frost.module.setting.settings.DoubleSetting;
import me.addo6544.frost.ui.hud.target.TargetHUD;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

import java.util.ArrayList;

public class AutoAttack extends Module {

    public BooleanSetting player = new BooleanSetting("Attack Players", false);
    public DoubleSetting range = new DoubleSetting("Range", "Attack Entities in range", 0.5D, 6.0D, 6.0D);
    public EntityLivingBase target = null;
    public AutoAttack(){
        super("Auto Attack", "Auto attack entities", Category.Combat);
        this.settings.addSetting(player);
        this.settings.addSetting(range);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        target = null;
        ArrayList<Entity> es = new ArrayList<>(mc.theWorld.getLoadedEntityList());
        for (Entity en : es){
            if (en instanceof EntityLivingBase){
                EntityLivingBase t = (EntityLivingBase) en;
                if (t.isDead || t.isInvisible() || t.equals(mc.thePlayer) || t.getDistanceToEntity(mc.thePlayer) >= range.getConfigValue()) continue;
                if (t.getHealth() > 0){
                    attack(t);
                }
            }
        }
    }

    public void attack(EntityLivingBase en){
        target = en;
        TargetHUD hud = ((TargetHUDMod) Frost.INSTANCE.moduleManager.getModule(TargetHUDMod.class)).getHud();
        if (target instanceof AbstractClientPlayer){
            hud.setTarget((AbstractClientPlayer) target);
            hud.setVisible(true);
        }else hud.setVisible(false);;
        mc.playerController.attackEntity(mc.thePlayer, en);
        mc.thePlayer.swingItem();
    }

    @Override
    public String getTag() {
        if (target != null){
            return "Name:"+target.getName();
        } else return "None";
    }
}
