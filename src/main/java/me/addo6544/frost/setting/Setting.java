package me.addo6544.frost.setting;


import java.util.ArrayList;

public abstract class Setting extends SettingBase{

    public String description;
    public ArrayList<Boolean> parents = new ArrayList<>();

    public Setting() {
        super(false);
    }

    public String getName(){
        return name;
    }

    public abstract <T> T getConfigValue();

    public void addParent(Boolean setting){
        parents.add(setting);
    }

    public boolean canBeShown(){
        for (boolean b : parents){
            if (!b) return false;
        }
        return true;
    }
}
