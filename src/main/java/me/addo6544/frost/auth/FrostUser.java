package me.addo6544.frost.auth;

public class FrostUser {
    public final String username;
    public final UserRanks userRank;
    public final String userToken;

    public FrostUser(String username, String userToken, UserRanks userRank){
        this.username = username;
        this.userToken = userToken;
        this.userRank = userRank;
    }
}
