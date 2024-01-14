package me.addo6544.frost.module.modules.player;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.SettingGroup;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import net.minecraft.potion.PotionEffect;

import java.util.Arrays;

public class AntiDebuff extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "", "Remove", Arrays.asList("Remove"));


    public BooleanSetting moveSlowdown = new BooleanSetting("Move Slowdown", false);
    public BooleanSetting digSlowdown = new BooleanSetting("Dig Slowdown", false);
    public BooleanSetting harm = new BooleanSetting("Harm", true);
    public BooleanSetting confusion = new BooleanSetting("Confusion", false);
    public BooleanSetting blindness = new BooleanSetting("Blindness", true);
    public BooleanSetting hunger = new BooleanSetting("Hunger", false);
    public BooleanSetting weakness = new BooleanSetting("Weakness", false);
    public BooleanSetting poison = new BooleanSetting("Poison", false);
    public BooleanSetting wither = new BooleanSetting("Wither", false);

    public SettingGroup effects = new SettingGroup("Effects", Arrays.asList(
            moveSlowdown,digSlowdown,harm,confusion,blindness,hunger,weakness,poison,wither
    ));
    public AntiDebuff(){
        super("Anti Debuff", "Blocks the debuff", Category.Player);
        this.settings.addSetting(mode);
        this.settings.addSetting(effects);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if (mode.getConfigValue().equalsIgnoreCase("remove")){
            for (PotionEffect p : mc.thePlayer.getActivePotionEffects()){
                int id = p.getPotionID();
                if (
                        (id == 2 && moveSlowdown.getConfigValue())
                        || (id == 4 && digSlowdown.getConfigValue())
                        || (id == 7 && harm.getConfigValue())
                        || (id == 9 && confusion.getConfigValue())
                        || (id == 15 && blindness.getConfigValue())
                        || (id == 17 && hunger.getConfigValue())
                        || (id == 18 && weakness.getConfigValue())
                        || (id == 19 && poison.getConfigValue())
                        || (id == 20 && wither.getConfigValue())
                ) mc.thePlayer.removePotionEffect(id);
            }
        }
    }

}
