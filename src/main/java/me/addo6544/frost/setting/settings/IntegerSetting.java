package me.addo6544.frost.setting.settings;


public class IntegerSetting extends NumberSetting {
    public IntegerSetting(String name, String description, int min, int max, int current, int increasement){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = increasement;
    }

    public IntegerSetting(String name, String description, int min, int max, int current){
        this.name = name;
        this.description = description;
        this.min = min;
        this.max = max;
        this.value = current;
        this.increasement = 1;
    }

    @Override
    public Integer getConfigValue() {
        return value.intValue();
    }


}
