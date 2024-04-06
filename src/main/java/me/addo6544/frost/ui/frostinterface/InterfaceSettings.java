package me.addo6544.frost.ui.frostinterface;

import me.addo6544.frost.setting.SettingBase;
import me.addo6544.frost.setting.settings.BooleanSetting;

import java.util.ArrayList;

public class InterfaceSettings {
    public InterfaceSettings(boolean isGlobal){
        addDefaultSettings(isGlobal);
    }

    private void addDefaultSettings(boolean global){
        if (!global){
            this.addSetting(new BooleanSetting("Use Global Setting", false));
        }
    }

    protected ArrayList<SettingBase> list = new ArrayList<>();

    public void addSetting(SettingBase settingBase){
        list.add(settingBase);
    }

    public ArrayList<SettingBase> getList() {
        return list;
    }
}
