package me.addo6544.frost.core;

public enum ReleaseType {
    Stable("Stable Build"),
    Experimental("Experimental Build"),
    Development("Development Build"),
    ;


    final String type;
    ReleaseType(String s){
        type = s;
    }

    public String getType() {
        return type;
    }
}
