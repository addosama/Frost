package me.addo6544.frost.module;

//
// Created by AddoSama
// 2023/6/6 10:26
//

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.setting.Setting;
import me.addo6544.frost.setting.SettingBase;
import me.addo6544.frost.setting.SettingGroup;
import me.addo6544.frost.ui.notification.Notification;
import me.addo6544.frost.utils.SimpleConsoleFormatter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.logging.Logger;

public class Module implements Comparable<Module>{
    protected Logger logger;
    private boolean state;
    private String name;
    private String description;
    private int keyCode;
    private Category category;

    private int about;


    private boolean extern;

    protected ModuleSettings settings = new ModuleSettings();

    public final Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, String description, int keyCode, Category category) {
        this.state = false;
        this.name = name;
        this.description = description;
        this.keyCode = keyCode;
        this.category = category;
        //this.settings = new ModuleSettings();
        this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger(name));
    }

    public Module(String name, String description, Category category) {
        this.state = false;
        this.name = name;
        this.description = description;
        this.keyCode = Keyboard.KEY_NONE;
        this.category = category;
        this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger(name));
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        if (this.state == state) return;
        this.state = state;
        if(state){
            Enable();
            Frost.INSTANCE.notificationManager.newNotification(
                    new Notification("Module Toggled", name + " enabled", Notification.NColors.GREEN)
            );
        }else {
            Disable();
            Frost.INSTANCE.notificationManager.newNotification(
                    new Notification("Module Toggled", name + " disabled", Notification.NColors.RED)
            );
        }
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    public boolean isExtern() {
        return extern;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public void setKeyCode(int keyCode) {
            this.keyCode = keyCode;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void toggle()
        {
            setState(!state);
        }

        public void Enable() {
            Frost.INSTANCE.eventManager.register(this);
            if (!Frost.INSTANCE.loaded) return;
            onEnable();
        }
        public void Disable() {
            if (!Frost.INSTANCE.loaded) return;
            onDisable();
            Frost.INSTANCE.eventManager.unregister(this);
        }

        public void onEnable(){

        }

        public void onDisable(){

        }

        public String getTag(){
            return "";
        }

    public ModuleSettings getSettings() {
        return settings;
    }

    public String getDescription() {
        return description;
    }

    public Setting getSetting(String name, String groupName){
            if (groupName.isEmpty()){
                for (SettingBase base : settings.getSettings()){
                    if (base.isGroup) continue;
                    if (name.equalsIgnoreCase(base.name)) return (Setting) base;
                }
            }else {
                for (SettingBase base : settings.getSettings()){
                    if (!base.isGroup) continue;
                    if (name.equalsIgnoreCase(groupName)){
                        SettingGroup sg = (SettingGroup) base;
                        for (Setting s : sg.getSettings()){
                            if (s.name.equalsIgnoreCase(name)) return s;
                        }
                    }
                }
            }

            return null;
    }

    public void setAbout(
            boolean showInArrayList,
            boolean saveSettings,
            boolean loadFromSettings,
            boolean toggleNotification
    ) {
        int i = 0;
        if (showInArrayList) i = i+1;
        if (saveSettings) i = i+10;
        if (loadFromSettings) i = i+100;
        if (toggleNotification) i = i+1000;
    }

    public void setAboutINT(int about) {
        this.about = about;
    }

    public int getAboutINT() {
        return about;
    }

    public boolean isShowInArraylist(){
        String[] s = String.valueOf(about).split("");
        return Integer.getInteger(s[s.length-1]) == 1;
    }

    public boolean isSaveSettings(){
        String[] s = String.valueOf(about).split("");
        return Integer.getInteger(s[s.length-2]) == 1;
    }

    public boolean isLoadFromSettings(){
        String[] s = String.valueOf(about).split("");
        return Integer.getInteger(s[s.length-3]) == 1;
    }

    public boolean isToggleNotification(){
        String[] s = String.valueOf(about).split("");
        return Integer.getInteger(s[s.length-4]) == 1;
    }

    public Setting getSetting(String name){
            return getSetting(name, "");
    }

    @Override
    public int compareTo(Module o){
            return name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
