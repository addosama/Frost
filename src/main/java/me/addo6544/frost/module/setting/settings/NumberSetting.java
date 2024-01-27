package me.addo6544.frost.module.setting.settings;

import me.addo6544.frost.module.setting.Setting;

public abstract class NumberSetting extends Setting {
    protected Number min,max,value,increasement;

    public Number getMin() {
        return min;
    }

    public void setMin(Number min) {
        this.min = min;
    }

    public Number getMax() {
        return max;
    }

    public void setMax(Number max) {
        this.max = max;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Number getIncreasement() {
        return increasement;
    }

    public Number getValue(){
        return value;
    }
}
