package me.addo6544.frost.ui.hud.target;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.module.modules.render.TargetHUDMod;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.GLUtil;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;

import java.awt.*;

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
        this.visible = b && Frost.INSTANCE.moduleManager.getModule(TargetHUDMod.class).isState();
        if (b) Frost.INSTANCE.eventManager.register(this);
        else Frost.INSTANCE.eventManager.unregister(this);
    }

    @EventTarget
    public void onRender(EventRender2D e){
        if (!visible) return;
        RoundedUtil.drawRound(
                x,y,
                5 + 40 + 10 + b24.getStringWidth(target.getName()) + 10,
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

        b24.drawString(target.getName(), x+55,y+10,-1);
        r12.drawString((int)target.getHealth() + " | Distance:" + mc.thePlayer.getDistanceToEntity(target), x+55,y+10+b24.FONT_HEIGHT+2, -1);
        float bW = b24.getStringWidth(target.getName()) * (target.getHealth()/target.getMaxHealth());
        RoundedUtil.drawGradientVertical(
                x+55,y+10+b24.FONT_HEIGHT+2+r12.FONT_HEIGHT+2,
                bW,5,
                2.5F,
                new Color(99,130,255),
                new Color(0,51,255)
        );
    }
}
