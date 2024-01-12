package me.addo6544.frost.module.setting;

import java.util.ArrayList;

public class ModuleSettings {
    public ArrayList<SettingBase> settings;

    public ModuleSettings(){
        settings = new ArrayList<>();
    }

    public void addSetting(SettingBase settingBase){
        settings.add(settingBase);
    }
}
