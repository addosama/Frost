package me.addo6544.frost.module.setting;


public abstract class Setting {

    public String name;
    public String getName(){
        return name;
    }

    public abstract <T> T getConfigValue();


}
