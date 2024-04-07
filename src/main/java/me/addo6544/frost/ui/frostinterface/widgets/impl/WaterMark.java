package me.addo6544.frost.ui.frostinterface.widgets.impl;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.setting.settings.BooleanSetting;
import me.addo6544.frost.setting.settings.FloatSetting;
import me.addo6544.frost.setting.settings.ModeSetting;
import me.addo6544.frost.ui.font.FR;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.frostinterface.widgets.InterfaceWidget;
import me.addo6544.frost.utils.RainbowColor;
import me.addo6544.frost.utils.RenderUtil;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Arrays;

public class WaterMark extends InterfaceWidget {

    public ModeSetting mode = new ModeSetting(
            "Style", "",
            "Logo", Arrays.asList(
                    "Logo", "Frost"
    )
    );
    public BooleanSetting rainbow = new BooleanSetting("Rainbow", false);
    public FloatSetting radiusFloat = new FloatSetting("Radius", "", 0, 15, 8);

    private final RainbowColor color = new RainbowColor();
    public WaterMark() {
        super("Watermark", "Client watermark", 5, 5, 0, 30, true);
        radiusFloat.addParent(mode.getConfigValue().equalsIgnoreCase("Frost"));

        this.settings.addSetting(mode);
        this.settings.addSetting(radiusFloat);
        this.settings.addSetting(rainbow);
    }

    @Override
    public void drawWidget(float mouseX, float mouseY, float pTicks) {
        color.setState(true);
        String s = mode.getConfigValue();
        if (s.equalsIgnoreCase("Frost")){
            drawDefaultWatermark();
        }else if (s.equalsIgnoreCase("Logo")){
            drawLogoWatermark();
        }
    }

    private void drawLogoWatermark(){
        RenderUtil.color(rainbow.getConfigValue()? color.getRainbow(0).getRGB() : -1);
        RenderUtil.drawImageNoColor(new ResourceLocation("frost/icon/logo_render.png")
        ,5,5,80,80,255);
    }

    private void drawDefaultWatermark(){
        FR bb18 = Fonts.HMBlack18;
        FR r18 = Fonts.HMRegular18;
        float radius = radiusFloat.getConfigValue();

        float tW = bb18.getStringWidth("FROST");//Name Text Width
        float pV = ((float) 20 /2) - (bb18.FONT_HEIGHT / 2);//Text Padding Value
        float padding = 7.5F;
        float uSw = r18.getStringWidth(Frost.INSTANCE.user.username);
        float fpsSw = r18.getStringWidth(Minecraft.getDebugFPS() + " FPS");

        //draw
        //r18.drawStringWithShadow("Shader " + (s ? "Enabled" : "Disabled"), 2, 50, -1);
        //BG
        RoundedUtil.drawRoundOutline(
                getX(),getY(),
                (float) (5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding + 12 + 2.5 + fpsSw + 5),
                30,
                radius,
                0.0005F,
                new Color(0,0,0, 128),
                new Color(178,178,178)
        );

        //Text BG and text
        if (!rainbow.getConfigValue()){
            RoundedUtil.drawGradientVertical(
                    getX() + 5,
                    getY() + 5,
                    pV + tW + pV,
                    20,
                    radius/2,
                    new Color(117, 194,235),
                    new Color(0,153,235)
            );
        }else {
            Color rb1 = color.getRainbow(0);
            float[] hsb = new float[3];
            Color rb2 = color.getRainbow(2);
            hsb = rb2.RGBtoHSB(rb2.getRed(),rb2.getGreen(),rb2.getBlue(),hsb);
            rb2 = new Color(Color.HSBtoRGB(hsb[0],hsb[1], hsb[2]-0.45F));

            RoundedUtil.drawGradientVertical(
                    getX() + 5,
                    getY() + 5,
                    pV + tW + pV,
                    20,
                    radius/2,
                    rb1,
                    rb2
            );
        }
        bb18.drawString("FROST", getX()+5+pV, getY()+5+pV+0.5F, -1);

        //user
        RenderUtil.drawImage(new ResourceLocation("frost/icon/user.png"),
                getX()+5+pV+tW+pV+padding,
                getY()+((float) (30/2)-((float) 12 /2)),
                12,12
        );
        r18.drawString(
                Frost.INSTANCE.user.username,
                (float) (getX() + 5 + pV + tW + pV + padding + 12 + 2.5),
                getY()+((30/2)-(r18.FONT_HEIGHT /2))+0.5F,
                -1
        );

        //fps
        RenderUtil.drawImage(new ResourceLocation("frost/icon/cpu.png"),
                (float) (getX()+5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding),
                getY()+((float) (30/2) - ((float) 12 /2)),
                12,12
        );
        r18.drawString(
                Minecraft.getDebugFPS() + " FPS",
                (float) (getX() + 5 + pV + tW + pV + padding + 12 + 2.5 + uSw + padding + 12 + 2.5),
                getY()+(((float) 30 /2)-(r18.FONT_HEIGHT/2))+0.5F,
                -1
        );
    }
}