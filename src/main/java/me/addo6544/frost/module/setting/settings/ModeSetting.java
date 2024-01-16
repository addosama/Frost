package me.addo6544.frost.module.setting.settings;

import me.addo6544.frost.module.setting.Setting;
import me.addo6544.frost.module.setting.exceptions.InvalidModeException;

import java.util.ArrayList;
import java.util.List;

public class ModeSetting extends Setting{

    private String currentMode;
    private ArrayList<String> modes;

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

    public void setMode(String mode) throws InvalidModeException {
        boolean setted = false;
        for (String s : modes){
            if (s.equalsIgnoreCase(mode)) {
                currentMode = s;
                setted = true;
            }
        }
        if (!setted){
            throw new InvalidModeException(mode);
        }
    }

    public ArrayList<String> getModes() {
        return modes;
    }
}
