package me.addo6544.frost.module.modules.combat;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.modules.render.TargetHUDMod;
import me.addo6544.frost.module.setting.SettingGroup;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import me.addo6544.frost.module.setting.settings.DoubleSetting;
import me.addo6544.frost.module.setting.settings.IntegerSetting;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.utils.DelayHelper;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KillAura extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "", "Single", Arrays.asList(
            "Single", "Switch", "Multi"
    ));
    public ModeSetting sortMode = new ModeSetting("Sort Mode", "", "Random", Arrays.asList(
            "Random", "Distance"
    ));

    public SettingGroup targetsOption;
    public BooleanSetting targetPlayers = new BooleanSetting("Players", "Target Players", true);
    public BooleanSetting targetAnimals = new BooleanSetting("Animals", "Target Animals", true);
    public BooleanSetting targetMobs = new BooleanSetting("Mobs", "Target Mobs", true);
    public BooleanSetting targetInvisible = new BooleanSetting("Invisible", "Target Invisible", true);

    public SettingGroup attackOption;
    public BooleanSetting noSwing = new BooleanSetting("No Swing", "Don't swing item", false);
    public IntegerSetting switchDelay = new IntegerSetting("Switch Delay", "", 50, 1000, 300);

    public SettingGroup rangeOption;
    public DoubleSetting minAttackRange = new DoubleSetting("Min Attack Range", "Minimum attack range", 0D,6D,0D);
    public DoubleSetting maxAttackRange = new DoubleSetting("Max Attack Range", "Maxmium attack range", 0D,6D,3D);
    public DoubleSetting swingRange = new DoubleSetting("Swing Range", "Swing item if target in range", 0D,6D,4D);

    private TargetHUDMod tghud;

    public ArrayList<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();

    public KillAura(){
        super("Kill Aura", "Like AutoAttack but better", Category.Combat);

        tghud = (TargetHUDMod) Frost.INSTANCE.moduleManager.getModule(TargetHUDMod.class);

        swingRange.addParent(!noSwing.getConfigValue());
        sortMode.addParent(!mode.getConfigValue().equals("Multi"));
        switchDelay.addParent(mode.getConfigValue().equals("Switch"));

        targetsOption = new SettingGroup("Targets", Arrays.asList(
                targetPlayers,targetAnimals,targetMobs,targetInvisible
        ));
        attackOption = new SettingGroup("Attack Options", Arrays.asList(
                noSwing,switchDelay
        ));
        rangeOption = new SettingGroup("Range Options", Arrays.asList(
                minAttackRange,maxAttackRange,swingRange
        ));

        this.settings.addSetting(mode);
        this.settings.addSetting(sortMode);
        this.settings.addSetting(targetsOption);
        this.settings.addSetting(attackOption);
        this.settings.addSetting(rangeOption);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        processEntities(mc.theWorld.loadedEntityList);
        if (targets.isEmpty()) return;
        attack();
    }

    private void processEntities(List<Entity> entities){
        ArrayList<EntityLivingBase> tgTmp = new ArrayList<>();
        for (Entity e : entities){
            if (e.isDead) continue;

            float d = mc.thePlayer.getDistanceToEntity(e);

            if (d > maxAttackRange.getConfigValue() && d > swingRange.getConfigValue()) continue;
            if (d < minAttackRange.getConfigValue()) continue;

            if (e instanceof AbstractClientPlayer && targetPlayers.getConfigValue() && !e.equals(mc.thePlayer)) tgTmp.add((EntityLivingBase) e);
            if (e instanceof EntityAnimal && targetAnimals.getConfigValue()) tgTmp.add((EntityLivingBase) e);
            if (e instanceof EntityMob && targetMobs.getConfigValue()) tgTmp.add((EntityLivingBase) e);
        }

        tgTmp.removeIf(e -> e.isInvisible() && !targetInvisible.getConfigValue());

        /***
        for (EntityLivingBase e : targets){
            if (!tgTmp.contains(e)){
                targets.remove(e);
                if (e instanceof AbstractClientPlayer) tghud.getHud().setTarget(null);
            }
        }
         ***/
        targets.clear();
        tghud.getHud().setTarget(null);

        targets.addAll(tgTmp);
    }

    private void attack(){
        if (mode.getConfigValue().equalsIgnoreCase("Multi")) {
            for (EntityLivingBase tg : targets) {
                if (tg instanceof AbstractClientPlayer)
                    tghud.getHud().setTarget((AbstractClientPlayer) tg);
                if (mc.thePlayer.getDistanceToEntity(tg) <= swingRange.getConfigValue() && !noSwing.getConfigValue())
                    mc.thePlayer.swingItem();
                if (mc.thePlayer.getDistanceToEntity(tg) <= maxAttackRange.getConfigValue()) {
                    mc.playerController.attackEntity(mc.thePlayer, tg);
                    if (!noSwing.getConfigValue()) mc.thePlayer.swingItem();
                }
            }
        } else if (mode.getConfigValue().equalsIgnoreCase("Single")){
            EntityLivingBase tg = null;

            if (sortMode.getConfigValue().equalsIgnoreCase("Distance"))
                targets.sort((o1, o2) -> (int) ((mc.thePlayer.getDistanceToEntity(o1)) - (mc.thePlayer.getDistanceToEntity(o2))));

            tg=targets.get(0);


            if (tg instanceof AbstractClientPlayer)
                tghud.getHud().setTarget((AbstractClientPlayer) tg);
            if (mc.thePlayer.getDistanceToEntity(tg) <= swingRange.getConfigValue() && !noSwing.getConfigValue())
                mc.thePlayer.swingItem();
            if (mc.thePlayer.getDistanceToEntity(tg) <= maxAttackRange.getConfigValue()) {
                mc.playerController.attackEntity(mc.thePlayer, tg);
                if (!noSwing.getConfigValue()) mc.thePlayer.swingItem();
            }
        } else if (mode.getConfigValue().equalsIgnoreCase("Switch")) {
                EntityLivingBase tg = null;
                int attacked = 0;

                if (sortMode.getConfigValue().equalsIgnoreCase("Distance"))
                    targets.sort((o1, o2) -> (int) ((mc.thePlayer.getDistanceToEntity(o1)) - (mc.thePlayer.getDistanceToEntity(o2))));

                if (attacked != 0 && DelayHelper.delay(switchDelay.getConfigValue())){
                    tg = targets.get(attacked);
                    attacked = attacked + 1;
                    if (attacked > targets.size()) attacked = 0;
                }else {
                    tg = targets.get(attacked);
                }

                if (tg instanceof AbstractClientPlayer)
                    tghud.getHud().setTarget((AbstractClientPlayer) tg);
                if (mc.thePlayer.getDistanceToEntity(tg) <= swingRange.getConfigValue() && !noSwing.getConfigValue())
                    mc.thePlayer.swingItem();
                if (mc.thePlayer.getDistanceToEntity(tg) <= maxAttackRange.getConfigValue()) {
                    mc.playerController.attackEntity(mc.thePlayer, tg);
                    if (!noSwing.getConfigValue()) mc.thePlayer.swingItem();
                }
        }
    }


    @Override
    public void onEnable(){
        targets.clear();
        tghud.getHud().setTarget(null);
        tghud.getHud().setVisible(true);
    }

    @Override
    public void onDisable() {
        targets.clear();
        tghud.getHud().setVisible(false);
        tghud.getHud().setTarget(null);
    }
}
