package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import me.addo6544.frost.utils.TimeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Interface extends Module {

    public ModeSetting style = new ModeSetting("Style", "Interface Style", "Modern",
            Arrays.asList(
                    "Classic",
                    "Modern"
            )
            );

    public Interface(){
        super("Interface", "Client HUD", Keyboard.KEY_H, Category.Render);
        this.settings.addSetting(style);
    }

    @EventTarget
    public void onRender2D(EventRender2D e){
        if (style.getConfigValue().equalsIgnoreCase("Classic")){
            ClassicHUD.drawWatermark();
            ClassicHUD.drawArraylist();
        }else if (style.getConfigValue().equalsIgnoreCase("Modern")){
            ModernHUD.drawWatermark();
            ModernHUD.drawArraylist();
        }
    }
}

class ClassicHUD{
    static FontRenderer fr = Fonts.mc;


    public static void drawWatermark(){
        String info = "[" + TimeUtil.getTime() + "] [" + Minecraft.getDebugFPS() + " FPS]";
        fr.drawStringWithShadow(Frost.CLIENT_NAME + " ",2,2, -1);
        fr.drawStringWithShadow(info, 2+fr.getStringWidth(Frost.CLIENT_NAME + " "), 2, -1);
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

class ModernHUD{
    public static FR bb18 = Fonts.HMBlack18;
    public static void drawWatermark(){
        float x = 5;
        float y = 5;

        float tW = bb18.getStringWidth("FROST");//Name Text Width
        float pV = (20 - bb18.FONT_HEIGHT) / 2;//Text Padding Value

        //draw
        //BG
        RenderUtil.drawRoundedRect(
                x,y,
                x + 5 + pV + tW + pV + 5,
                y + 30,
                new Color(178,178,178).getRGB(),
                new Color(0,0,0,50).getRGB()
        );

        //Text BG and text
        RenderUtil.drawRoundedRect(
                x + 5,
                y + 5,
                x + 5 + pV + tW + pV,
                y + 5 + 20,
                new Color(0,153,235).getRGB()
        );
        bb18.drawString("FROST", x+5+pV, y+5+pV, -1);
    }

    public static void drawArraylist(){

    }
}
