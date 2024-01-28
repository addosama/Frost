package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.BooleanSetting;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import me.addo6544.frost.utils.RoundedUtil;
import me.addo6544.frost.utils.TimeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Interface extends Module {

    public ModeSetting style = new ModeSetting("Style", "Interface Style", "Modern",
            Arrays.asList(
                    "Classic",
                    "Modern",
                    "Power"
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
            ModernHUD.drawWatermark(false);
            ModernHUD.drawArraylist();
        } else if (style.getConfigValue().equalsIgnoreCase("Power")) {
            PowerHUD.drawWatermark();
            PowerHUD.drawArraylist();
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
    public static FR r18 = Fonts.HMRegular18;
    public static void drawWatermark(boolean s){
        float x = 5;
        float y = 5;

        float tW = bb18.getStringWidth("FROST");//Name Text Width
        float pV = ((float) 20 /2) - (bb18.FONT_HEIGHT / 2);//Text Padding Value
        float padding = 7.5F;
        float uSw = r18.getStringWidth(Frost.INSTANCE.user.username);
        float fpsSw = r18.getStringWidth(Minecraft.getDebugFPS() + " FPS");

        //draw
        //r18.drawStringWithShadow("Shader " + (s ? "Enabled" : "Disabled"), 2, 50, -1);
        //BG
        RoundedUtil.drawRound(
                x,y,
                (float) (5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding + 12 + 2.5 + fpsSw + 5),
                30,
                8,
                s,
                new Color(0,0,0, 128)
        );

        //Text BG and text
        RoundedUtil.drawRound(
                x + 5,
                y + 5,
                pV + tW + pV,
                20,
                4,
                new Color(0,153,235)
        );
        bb18.drawString("FROST", x+5+pV, y+5+pV+0.5F, -1);

        //user
        RenderUtil.drawImage(new ResourceLocation("frost/icon/user.png"),
                x+5+pV+tW+pV+padding,
                y+((float) (30/2)-((float) 12 /2)),
                12,12
        );
        r18.drawString(
                Frost.INSTANCE.user.username,
                (float) (x + 5 + pV + tW + pV + padding + 12 + 2.5),
                y+((30/2)-(r18.FONT_HEIGHT /2))+0.5F,
                -1
        );

        //fps
        RenderUtil.drawImage(new ResourceLocation("frost/icon/cpu.png"),
                (float) (x+5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding),
                y+((float) (30/2) - ((float) 12 /2)),
                12,12
        );
        r18.drawString(
                Minecraft.getDebugFPS() + " FPS",
                (float) (x + 5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding + 12 + 2.5),
                y+(((float) 30 /2)-(r18.FONT_HEIGHT/2))+0.5F,
                -1
        );

    }

    public static void drawArraylist(){
        FR fr = Fonts.HMRegular18;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        ArrayList<Module> enabledModules = new ArrayList<>();
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            if (m.isState()) enabledModules.add(m);
        }
        enabledModules.sort((o1, o2) -> (int) (fr.getStringWidth(o2.getName() + " " + o2.getTag()) - fr.getStringWidth(o1.getName() + " " + o1.getTag())));
        int arrayIndex = 0;
        for (Module module : enabledModules){
            fr.drawStringWithShadow(module.getName(), sr.getScaledWidth()-2-fr.getStringWidth(module.getTag().isEmpty() ? module.getName() : module.getName() + " " + module.getTag()), 2 + arrayIndex, -1 );
            if (!module.getTag().isEmpty())
                fr.drawStringWithShadow(module.getTag(), sr.getScaledWidth()-2-fr.getStringWidth(module.getTag()), 2 + arrayIndex, new Color(153, 153, 153).getRGB() );

            arrayIndex += (2+fr.FONT_HEIGHT);
        }
    }
}
class PowerHUD{
    public static void drawWatermark(){
        FR l42 = Fonts.HMLight42;
        float padding = (25-l42.FONT_HEIGHT)/2;
        float w = 2 + padding + l42.getStringWidth(Frost.CLIENT_NAME) + padding;
        float h = 25;
        RenderUtil.drawWHRect(2,2,w,h,new Color(0,0,0,128).getRGB());
        RenderUtil.drawWHRect(2,2,2,h,-1);
        l42.drawString(Frost.CLIENT_NAME, 2+2 + padding, 2+padding, -1);
    }

    public static void drawArraylist(){
        ModernHUD.drawArraylist();
    }
}
