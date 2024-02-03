package me.addo6544.frost.auth;

import frost.loader.FrostLoader;
import me.addo6544.frost.core.Frost;

import javax.swing.*;

public class Validator {
    public static void tryStart(String token, String[] args){
        long r = auth(token);
        if (r == AuthUtils.AUTH_SUCCEED){
            Frost.INSTANCE.setUser(new FrostUser(JOptionPane.showInputDialog("Input your Username")));
            FrostLoader.run(args);
        } else if (r == AuthUtils.AUTH_FAILED) {
            JOptionPane.showMessageDialog(null, "AUTH FAILED!");
        } else if (r == AuthUtils.BANNED_USER) {
            JOptionPane.showMessageDialog(null, "YOU HAVE BEEN BANNED");
        }
    }

    public static long auth(String token){
        return AuthUtils.AUTH_SUCCEED;
    }
}
