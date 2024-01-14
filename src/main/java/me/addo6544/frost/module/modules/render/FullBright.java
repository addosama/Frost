package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FullBright extends Module {
    public FullBright(){
        super("Full Bright", "Night vision", Category.Render);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 1337));
    }

    @Override
    public void onDisable() {
        for (PotionEffect p : mc.thePlayer.getActivePotionEffects()){
            if (p.getAmplifier() == 1337) mc.thePlayer.removePotionEffect(p.getPotionID());
        }
    }
}
