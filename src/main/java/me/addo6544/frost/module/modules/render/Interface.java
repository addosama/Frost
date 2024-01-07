package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.font.FrostFR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

public class Interface extends Module {
    public Interface(){
        super("Interface", "Client HUD", Keyboard.KEY_H, Category.Render);
    }

    @EventTarget
    public void onRender2D(EventRender2D e){
        if (true){
            if (true){
                ClassicHUD.drawWatermark();
            }
            if (true){
                ClassicHUD.drawArraylist();
            }
        }
    }
}

class ClassicHUD{
    static FrostFR fr = Fonts.mc;


    public static void drawWatermark(){
        fr.drawStringWithShadow("Frost",2,2, -1);
    }

    public static void drawArraylist(){
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        ArrayList<Module> enabledModules = new ArrayList<>();
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            if (m.isState()) enabledModules.add(m);
        }
        enabledModules.sort((o1, o2) -> (int) (fr.getStringWidth(o2.getName()) - fr.getStringWidth(o1.getName())));
        int arrayIndex = 0;
        for (Module module : enabledModules){
            fr.drawStringWithShadow(module.getName(), sr.getScaledWidth()-2-fr.getStringWidth(module.getName()), 2 + arrayIndex, -1 );

            arrayIndex += 11;
        }
    }
}
