package me.addo6544.frost.module.setting.settings;

import me.addo6544.frost.module.setting.Setting;

public class BooleanSetting extends Setting {

    private boolean state;

    public BooleanSetting(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public boolean isEnabled() {
        return state;
    }

    public void toggle() {
        setState(!isEnabled());
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public Boolean getConfigValue() {
        return isEnabled();
    }

}
