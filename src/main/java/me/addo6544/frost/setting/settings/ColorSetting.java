package me.addo6544.frost.setting.settings;

import me.addo6544.frost.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting {

    private Color color;

    public ColorSetting(String name, String description, Color currentColor){
        this.name = name;
        this.description = description;
        this.color = currentColor;
    }

    @Override
    public Integer getConfigValue() {
        return color.getRGB();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
