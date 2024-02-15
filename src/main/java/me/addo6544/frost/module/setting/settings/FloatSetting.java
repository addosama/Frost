package me.addo6544.frost.module.setting.settings;

public class FloatSetting extends NumberSetting{
    public FloatSetting(String name, String description, double min, double max, double current, double increasement){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = increasement;
    }

    public FloatSetting(String name, String description, double min, double max, double current){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = 0.1;
    }

    @Override
    public Float getConfigValue() {
        return value.floatValue();
    }
}

