package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventSettingModeChange;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.settings.ModeSetting;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Arrays;

public class FullBright extends Module {
    public ModeSetting mode = new ModeSetting(
            "Mode", "",
            "Gamma",
            Arrays.asList("Effect", "Gamma")
    );
    public FullBright(){
        super("Full Bright", "Night vision", Category.Render);
        this.settings.addSetting(mode);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if (mode.getConfigValue().equalsIgnoreCase("Effect")){
            mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 1337));
        }else if (mode.getConfigValue().equalsIgnoreCase("Gamma")){
            mc.gameSettings.gammaSetting = 10000F;
        }

    }

    @EventTarget
    public void onModeChange(EventSettingModeChange e){
        if (e.getType().equals(Event.Type.PRE)){
            if (e.getOldMode().equalsIgnoreCase("Effect") && e.getNewMode().equalsIgnoreCase("Gamma")) {
                removeEffect();
            } else if (
                    e.getOldMode().equalsIgnoreCase("Gamma") && e.getNewMode().equalsIgnoreCase("Effect")
            ) {
                setGammaToDark();
            }
        }
    }

    @Override
    public void onDisable() {
        if (mode.getConfigValue().equalsIgnoreCase("Effect")){
            removeEffect();
        }else if (mode.getConfigValue().equalsIgnoreCase("Gamma")){
            setGammaToDark();
        }
    }

    private void removeEffect(){
        for (PotionEffect p : mc.thePlayer.getActivePotionEffects()){
            if (p.getAmplifier() == 1337) mc.thePlayer.removePotionEffect(p.getPotionID());
        }
    }

    private void setGammaToDark(){
        mc.gameSettings.gammaSetting = 0F;
    }

    @Override
    public String getTag() {
        return mode.getConfigValue();
    }
}
