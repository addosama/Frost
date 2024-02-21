package me.addo6544.frost.auth;

import java.awt.*;

public enum UserRanks {
    User("User", new Color(192, 192, 192)),
    PremiumUser("Premium User", new Color(75, 255, 152)),
    BetaUser("Beta User", new Color(101, 186, 255)),
    CommunityDeveloper("Community Developer", new Color(191, 148, 255)),
    FrostDeveloper("Frost Developer", new Color(255,100,100)),
    ;

    private final Color color;
    private final String name;

    UserRanks(String name, Color color){
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
