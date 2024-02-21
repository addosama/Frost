package me.addo6544.frost.auth;

import frost.loader.FrostLoader;
import me.addo6544.frost.core.Frost;

import javax.swing.*;

public class Validator {
    public static void tryStart(String[] args){
        AuthResult result = auth();
        long r = result.getResult();
        if (r == AuthUtils.AUTH_SUCCEED){
            Frost.INSTANCE.setUser(new FrostUser(JOptionPane.showInputDialog("Input your Username"), result.getToken(), getRank(result.getToken())));
            FrostLoader.run(args);
        } else if (r == AuthUtils.AUTH_FAILED) {
            JOptionPane.showMessageDialog(null, "AUTH FAILED!");
        } else if (r == AuthUtils.BANNED_USER) {
            JOptionPane.showMessageDialog(null, "YOU HAVE BEEN BANNED");
        }
    }

    public static AuthResult auth(){
        return new AuthResult(AuthUtils.AUTH_SUCCEED, "abc");
    }

    public static UserRanks getRank(String userToken){
        return UserRanks.FrostDeveloper;
    }
}
