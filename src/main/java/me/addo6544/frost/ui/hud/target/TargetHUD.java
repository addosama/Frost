package me.addo6544.frost.ui.hud.target;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.modules.render.TargetHUDMod;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.GLUtil;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.text.DecimalFormat;

public class TargetHUD {
    public float x;
    public float y;
    public float width;
    public final float height = 50;


    private final FR b24 = Fonts.HMBold24;
    private final FR r12 = Fonts.HMRegular12;

    public AbstractClientPlayer target;

    public boolean visible;

    protected Minecraft mc;

    public TargetHUD(float x, float y){
        this.x = x;
        this.y = y;
        mc = Minecraft.getMinecraft();
    }

    public void setTarget(AbstractClientPlayer e){
        this.target = e;
    }

    public void setVisible(boolean b){
        if (!Frost.INSTANCE.moduleManager.getModule(TargetHUDMod.class).isState()) return;
        this.visible = b;
    }

    public void render(){
        if (!visible) return;
        String s1 = target.getName();
        String s2 = (int)target.getHealth() + "HP | Distance:" + new DecimalFormat("0.00").format(mc.thePlayer.getDistanceToEntity(target));
        String text = b24.getStringWidth(s1) >= r12.getStringWidth(s2) ? s1 : s2;

        RoundedUtil.drawRound(
                x,y,
                5 + 40 + 10 + b24.getStringWidth(text) + 10,
                height,
                8,
                new Color(0,0,0, 191)
        );
        /*
        RoundedUtil.drawRound(
                x+5,y+5,
                40,40,8,
                new Color(0,0,0,128)
        );
         */
        GLUtil.startBlend();
        mc.getTextureManager().bindTexture(target.getLocationSkin());
        Gui.drawScaledCustomSizeModalRect(
                (int) (x+5), (int) (y+5),
                8,8,8,8,40,40,64F,64F
        );
        GLUtil.endBlend();

        b24.drawString(s1, x+55,y+10,-1);
        r12.drawString(s2, x+55,y+10+b24.FONT_HEIGHT+2, -1);
        float bW = b24.getStringWidth(s1) * (target.getHealth()/target.getMaxHealth());
        RoundedUtil.drawGradientVertical(
                x+55,y+10+b24.FONT_HEIGHT+2+r12.FONT_HEIGHT+2,
                bW,5,
                2.5F,
                new Color(99,130,255),
                new Color(0,51,255)
        );
    }
}
