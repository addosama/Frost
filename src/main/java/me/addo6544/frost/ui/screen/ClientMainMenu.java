package me.addo6544.frost.ui.screen;

import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import me.addo6544.frost.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientMainMenu extends GuiScreen {
    private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};

    private final ArrayList<TB> buttons = new ArrayList<>();

    private final ResourceLocation single = new ResourceLocation("frost/mainmenu/single.png");
    private final ResourceLocation multi = new ResourceLocation("frost/mainmenu/multi.png");
    private final ResourceLocation alts = new ResourceLocation("frost/mainmenu/alts.png");
    private ResourceLocation backgroundTexture;
    private DynamicTexture viewportTexture;
    private int panoramaTimer;

    @Override
    public void initGui() {
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
    }

    public ClientMainMenu(){
        buttons.add(
                new TB(
                        1,
                        ((float) (width - 185) /2)+35+12.5F,
                        ((float) (height - 240) /2)+21+42.5F,
                        90,30,single
                )
        );
        buttons.add(
                new TB(
                        2,
                        ((float) (width - 185) /2)+35+12.5F,
                        ((float) (height - 240) /2)+21+42.5F+30+10.5F,
                        90,30,multi
                )
        );
        buttons.add(
                new TB(
                        3,
                        ((float) (width - 185) /2)+35+12.5F,
                        ((float) (height - 240) /2)+21+42.5F+30+10.5F+30+10.5F,
                        90,30,alts
                )
        );
        buttons.add(new TB(
                4,((float) (width - 185) /2)+35+12.5F,
                ((float) (height - 240) /2)+21+42.5F+30+10.5F+30+10.5F+30+10.5F,
                23,23,
                new ResourceLocation("frost/mainmenu/settings.png")
        ));
        buttons.add(new TB(
                5,((float) (width - 185) /2)+35+12.5F+33,
                ((float) (height - 240) /2)+21+42.5F+30+10.5F+30+10.5F+30+10.5F,
                23,23,
                new ResourceLocation("frost/mainmenu/lang.png")
        ));
        buttons.add(new TB(
                6,((float) (width - 185) /2)+35+12.5F+33+33,
                ((float) (height - 240) /2)+21+42.5F+30+10.5F+30+10.5F+30+10.5F,
                23,23,
                new ResourceLocation("frost/mainmenu/exit.png")
        ));
    }

    public void buttonAction(int id){
        switch (id){
            case 1:
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                break;
            case 4:
                mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 5:
                mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
            case 6:
                Minecraft.getMinecraft().shutdown();
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        renderSkybox(mouseX,mouseY,partialTicks);
        drawMenu();
        for (TB b : buttons){
            if (b.id <= 3){
                b.setY(
                        ((float) (height - 240) /2)+21+42.5F+((b.id-1)*(30+10.5F))
                );
                b.setX(((float) (width - 185) /2)+35+12.5F);
            }if (b.id >= 4){
                b.setX(
                        ((float) (width - 185) /2)+35+12.5F+((b.id-4)*33)
                );
                b.setY(((float) (height - 240) /2)+21+42.5F+30+10.5F+30+10.5F+30+10.5F);
            }
            b.drawButton(mouseX,mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0){
            for (TB b : buttons){
                if (b.isHovered(mouseX,mouseY)) buttonAction(b.id);
            }
        }
    }

    private void drawMenu(){
        float w = 185;
        float h = 240;
        float x = (float) (width - w) /2;
        float y = (float) (height - h) /2;

        RoundedUtil.drawRoundOutline(x,y, w,h, 8, 1, new Color(255,255,255,76), new Color(128,128,128,191));

        x = x + 35;
        y = y + 21;
        w = 115;
        h = 198;
        RoundedUtil.drawGradientVertical(
                x,y,w,h,4,
                new Color(240,240,240),
                new Color(189, 189, 189)
        );

        Fonts.HMBlack36.drawString("FROST", x+27.5F, y+11, new Color(102,102,102).getRGB());

        x = x + 12.5F;
        y = y + 42.5F;
        w = 90;
        h = 30;

    }
    private void rotateAndBlurSkybox(float p_73968_1_)
    {
        this.mc.getTextureManager().bindTexture(this.backgroundTexture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.colorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        GlStateManager.disableAlpha();
        int i = 3;

        for (int j = 0; j < i; ++j)
        {
            float f = 1.0F / (float)(j + 1);
            int k = this.width;
            int l = this.height;
            float f1 = (float)(j - i / 2) / 256.0F;
            worldrenderer.pos((double)k, (double)l, (double)this.zLevel).tex((double)(0.0F + f1), 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
            worldrenderer.pos((double)k, 0.0D, (double)this.zLevel).tex((double)(1.0F + f1), 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
            worldrenderer.pos(0.0D, 0.0D, (double)this.zLevel).tex((double)(1.0F + f1), 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
            worldrenderer.pos(0.0D, (double)l, (double)this.zLevel).tex((double)(0.0F + f1), 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
        }

        tessellator.draw();
        GlStateManager.enableAlpha();
        GlStateManager.colorMask(true, true, true, true);
    }

    private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        int i = 8;

        for (int j = 0; j < i * i; ++j)
        {
            GlStateManager.pushMatrix();
            float f = ((float)(j % i) / (float)i - 0.5F) / 64.0F;
            float f1 = ((float)(j / i) / (float)i - 0.5F) / 64.0F;
            float f2 = 0.0F;
            GlStateManager.translate(f, f1, f2);
            GlStateManager.rotate(MathHelper.sin(((float)this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-((float)this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int k = 0; k < 6; ++k)
            {
                GlStateManager.pushMatrix();

                if (k == 1)
                {
                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 2)
                {
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 3)
                {
                    GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (k == 4)
                {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (k == 5)
                {
                    GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.getTextureManager().bindTexture(titlePanoramaPaths[k]);
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                int l = 255 / (j + 1);
                float f3 = 0.0F;
                worldrenderer.pos(-1.0D, -1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, l).endVertex();
                worldrenderer.pos(1.0D, -1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, l).endVertex();
                worldrenderer.pos(1.0D, 1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, l).endVertex();
                worldrenderer.pos(-1.0D, 1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, l).endVertex();
                tessellator.draw();
                GlStateManager.popMatrix();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
    }

    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
        this.mc.getFramebuffer().unbindFramebuffer();
        GlStateManager.viewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        float f = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float f1 = (float)this.height * f / 256.0F;
        float f2 = (float)this.width * f / 256.0F;
        int i = this.width;
        int j = this.height;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(0.0D, (double)j, (double)this.zLevel).tex((double)(0.5F - f1), (double)(0.5F + f2)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        worldrenderer.pos((double)i, (double)j, (double)this.zLevel).tex((double)(0.5F - f1), (double)(0.5F - f2)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        worldrenderer.pos((double)i, 0.0D, (double)this.zLevel).tex((double)(0.5F + f1), (double)(0.5F - f2)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        worldrenderer.pos(0.0D, 0.0D, (double)this.zLevel).tex((double)(0.5F + f1), (double)(0.5F + f2)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        tessellator.draw();
    }

    @Override
    public void updateScreen() {
        ++this.panoramaTimer;
    }
}

class TB{
    final int id;
    float x,y,width,height;
    ResourceLocation t;
    public TB(int id, float x, float y, float width, float height, ResourceLocation texture){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.t = texture;
    }

    public void drawButton(int mouseX, int mouseY){
        Minecraft.getMinecraft().getTextureManager().bindTexture(t);
        if (this.isHovered(mouseX,mouseY)){
            RoundedUtil.drawRoundTextured(
                    x-0.5F,y-0.5F,width+1,height+1,2,255
            );
        } else {
            RoundedUtil.drawRoundTextured(
                    x,y,width,height,2,255
            );
        }

    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public boolean isHovered(int mouseX, int mouseY){
        if (mouseX >= x && mouseX <= x+width){
            if (mouseY >= y && mouseY <= y+height){
                return true;
            }
        }
        return false;
    }
}
