package me.addo6544.frost.ui.clickgui.fullscreen;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FSClickGui extends GuiScreen {
    private final uiTitleBar titleBar;
    private final uiCategories categories;
    private final uiModules modules;
    //private ui moduleSettings;

    private Category currentCategory = null;
    private Module currentModule = null;

    public FSClickGui(){
        this.titleBar = new uiTitleBar();
        this.categories = new uiCategories();
        this.modules = new uiModules();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        modules.draw(mouseX, mouseY);
        categories.draw(mouseX, mouseY);
        titleBar.draw(mouseX,mouseY);
    }

    @Override
    public void updateScreen() {
        modules.update();
        categories.update();
        titleBar.update();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseX > modules.x && mouseX < modules.x+modules.width){
            if (mouseY > modules.y && mouseY < modules.y+modules.height) this.modules.mouseClicked(mouseX,mouseY,mouseButton);
        }
        if (mouseX > categories.x && mouseX < categories.x+categories.width){
            if (mouseY > categories.y && mouseY < categories.y+categories.height) this.categories.mouseClicked(mouseX,mouseY,mouseButton);
        }
    }

    public void switchToCategory(Category c){
        if (currentCategory == c) {
            currentCategory = null;
            modules.setNull();
        }
        else {
            currentCategory = c;
            modules.updateUI(c);
        }
    }

    public void switchToModule(Module m){
        if (currentModule == m) currentModule = null;
        else currentModule = m;
    }

    public Module getCurrentModule() {
        return currentModule;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }
}

abstract class widget {
    public float x,y,width,height;
    public ui parent;
    public widget(float x, float y, float width, float height){
        this.x=x;this.y=y;this.width=width;this.height=height;
    }
    public abstract void draw(float mouseX, float mouseY);
    public abstract void onClick(int i);

    public boolean isHovering(float mouseX, float mouseY){
        if (mouseX >= x && mouseX <= x+width){
            if (mouseY >= y && mouseY <= y+height){
                return true;
            }
        }
        return false;
    }
}
abstract class ui {
    public float x,y,width,height;
    public ui(float x, float y, float width, float height){
        this.x=x;this.y=y;this.width=width;this.height=height;
    }
    public abstract void draw(float mouseX, float mouseY);
    public abstract void update();

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
}

class uiModules extends ui{

    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    ArrayList<moduleButton> moduleButtons;
    Category c = null;

    public uiModules() {
        super(100, 20, 150, 0);
        this.moduleButtons = new ArrayList<>();
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        if (c == null) return;
        RenderUtil.drawWHRect(x,y,width,height, new Color(0,0,0,128).getRGB());
        for (moduleButton moduleButton : moduleButtons){
            moduleButton.draw(mouseX, mouseY);
        }
    }

    @Override
    public void update() {
        sr = new ScaledResolution(Minecraft.getMinecraft());
        this.height = sr.getScaledHeight()-20;
    }

    public void setNull(){
        c = null;
        moduleButtons.clear();
    }

    public void updateUI(Category category){
        c = category;
        moduleButtons.clear();
        float mOffset = 0;
        for (Module m : Frost.INSTANCE.moduleManager.getModuleByCategory(c)){
            moduleButtons.add(new moduleButton(x+20, y+10+mOffset,
                    Fonts.mc.getStringWidth(c.toString())+4,
                    Fonts.mc.FONT_HEIGHT+4,
                    m
            ));
            mOffset = mOffset+Fonts.mc.FONT_HEIGHT+4+12;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (moduleButton b : moduleButtons){
            if (mouseX > b.x && mouseX < b.x+b.width){
                if (mouseY > b.y && mouseY < b.y+b.height) b.onClick(mouseButton);
            }
        }
    }
}

class uiCategories extends ui{

    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    ArrayList<categoryButton> categoryButtons;

    public uiCategories() {
        super(0, 20, 100, 0);
        categoryButtons = new ArrayList<>();
        float cbOffset = 0;
        for (Category c : Category.values()){
            categoryButtons.add(new categoryButton(x+20, y+10+cbOffset,
                    Fonts.mc.getStringWidth(c.toString())+4,
                    Fonts.mc.FONT_HEIGHT+4,
                    c
            ));
            cbOffset = cbOffset+Fonts.mc.FONT_HEIGHT+4+12;
        }
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        RenderUtil.drawWHRect(x,y,width,height, new Color(0,0,0,128).getRGB());
        for (categoryButton b : categoryButtons){
            b.draw(mouseX, mouseY);
        }
    }

    @Override
    public void update() {
        sr = new ScaledResolution(Minecraft.getMinecraft());
        this.height = sr.getScaledHeight()-20;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (categoryButton b : categoryButtons){
            if (mouseX > b.x && mouseX < b.x+b.width){
                if (mouseY > b.y && mouseY < b.y+b.height) b.onClick(mouseButton);
            }
        }
    }
}

class uiTitleBar extends ui {

    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    public uiTitleBar() {
        super(0,0,0,20);
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        RenderUtil.drawWHRect(0,0,sr.getScaledWidth(),20,new Color(0,0,0,255).getRGB());
        Fonts.mc.drawString("frost client", 4,4,new Color(0, 246, 255).getRGB());
    }

    @Override
    public void update() {
        sr = new ScaledResolution(Minecraft.getMinecraft());
        this.width = sr.getScaledWidth();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }
}

class moduleButton extends widget{

    Module module;

    public moduleButton(float x, float y, float width, float height, Module module) {
        super(x, y, width, height);
        this.module = module;
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        Color color = new Color(-1);
        if (module.isState()) color = new Color(0,255,255);
        if (Frost.INSTANCE.CGUI_FS.getCurrentModule() == module) color = new Color(0,150,255);
        if (this.isHovering(mouseX,mouseY)) color = new Color(180,255,255);
        Fonts.mc.drawStringWithShadow(
                module.getName(),
                x,
                y+(height/2)-(Fonts.mc.FONT_HEIGHT/2),
                color.getRGB()
        );
    }

    @Override
    public void onClick(int i) {
        if (i == 0) module.toggle();
        if (i == 1) Frost.INSTANCE.CGUI_FS.switchToModule(module);
    }
}

class categoryButton extends widget{

    Category category;

    public categoryButton(float x, float y, float width, float height, Category category) {
        super(x, y, width, height);
        this.category = category;
    }

    @Override
    public void draw(float mouseX, float mouseY) {
        Color color = new Color(-1);
        if (Frost.INSTANCE.CGUI_FS.getCurrentCategory() == category) color = new Color(0,150,255);
        if (this.isHovering(mouseX,mouseY)) color = new Color(0,255,255);
        Fonts.mc.drawStringWithShadow(
                category.toString().toLowerCase(),
                x,
                y+(height/2)-(Fonts.mc.FONT_HEIGHT/2),
                color.getRGB()
        );
    }

    @Override
    public void onClick(int i) {
        if (i != 0) return;
        Frost.INSTANCE.CGUI_FS.switchToCategory(category);
    }
}