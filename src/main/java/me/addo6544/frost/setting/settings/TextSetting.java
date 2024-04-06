package me.addo6544.frost.setting.settings;

import me.addo6544.frost.setting.Setting;

public class TextSetting extends Setting {
    private String text;


    public TextSetting(String name, String description, String defaultText){
        this.name = name;
        this.description = description;
        this.text = defaultText;
    }

    @Override
    public String getConfigValue() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
