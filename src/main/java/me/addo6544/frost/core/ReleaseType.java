package me.addo6544.frost.core;

public enum ReleaseType {
    Release("Release"),
    Beta("Beta"),
    Development("Development"),
    ;


    final String type;
    ReleaseType(String s){
        type = s;
    }

    public String getType() {
        return type;
    }
}
