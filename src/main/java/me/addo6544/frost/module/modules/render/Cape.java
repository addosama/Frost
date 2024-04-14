package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventGetPlayerCape;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.settings.ModeSetting;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public class Cape extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "Cape Styles",
            "Frost",
            Arrays.asList(
                    "Frost", "Frost Dark"
            ));
    public Cape(){
        super("Cape", "", Category.Render);
        this.settings.addSetting(mode);
    }

    @EventTarget
    public void onEvent(EventGetPlayerCape e){
        if (mode.getConfigValue().equalsIgnoreCase("Frost")){
            e.setR(new ResourceLocation("frost/cape/frostCape.png"));
        }else if (mode.getConfigValue().equalsIgnoreCase("Frost Dark")){
            e.setR(new ResourceLocation("frost/cape/frostCapeDark.png"));
        } else if (mode.getConfigValue().equalsIgnoreCase("Addo")){
            e.setR(new ResourceLocation("frost/cape/addoCape.png"));
        }else if (mode.getConfigValue().equalsIgnoreCase("Addo Dark")){
            e.setR(new ResourceLocation("frost/cape/addoCapeDark.png"));
        }
    }


}
