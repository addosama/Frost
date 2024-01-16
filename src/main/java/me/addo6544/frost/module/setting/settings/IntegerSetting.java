package me.addo6544.frost.module.setting.settings;

import me.addo6544.frost.module.setting.Setting;

public class IntegerSetting extends Setting {
    private int min,max,value,increasement;
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
        return value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIncreasement() {
        return increasement;
    }
}
