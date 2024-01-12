package me.addo6544.frost.module.setting.settings;

import me.addo6544.frost.module.setting.Setting;

import java.util.ArrayList;
import java.util.List;

public class ModeSetting extends Setting{

    public String currentMode;
    public ArrayList<String> modes;

    public ModeSetting(String name, String description, String currentMode, List<String> modes){
        super();
        this.name = name;
        this.description = description;
        this.currentMode = currentMode;
        this.modes = new ArrayList<>(modes);
    }

    @Override
    public String getConfigValue() {
        return currentMode;
    }

    public void setMode(String mode){
        for (String s : modes){
            if (s.equalsIgnoreCase(mode)) currentMode = s;
        }
    }
}
