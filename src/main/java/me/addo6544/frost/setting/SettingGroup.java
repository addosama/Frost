package me.addo6544.frost.setting;

import java.util.ArrayList;
import java.util.List;

public class SettingGroup extends SettingBase{
    protected final ArrayList<Setting> settings;

    public SettingGroup(String name, List<Setting> settings){
        super(true);
        this.name = name;
        this.settings = new ArrayList<>(settings);
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }
}
