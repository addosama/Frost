package me.addo6544.frost.setting.settings;

public class DoubleSetting extends NumberSetting{

    public DoubleSetting(String name, String description, double min, double max, double current, double increasement){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = increasement;
    }

    public DoubleSetting(String name, String description, double min, double max, double current){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = 0.1;
    }

    @Override
    public Double getConfigValue() {
        return value.doubleValue();
    }
}
