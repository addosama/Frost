package me.addo6544.frost.auth;

import java.util.Random;

public class AuthUtils extends Thread{
    public static long AUTH_SUCCEED;
    public static long AUTH_FAILED;
    public static long BANNED_USER;

    {
        this.start();
    }

    public void run(){
        while (true){
            AUTH_SUCCEED = new Random().nextLong();
            AUTH_FAILED = new Random().nextLong();
            BANNED_USER = new Random().nextLong();
            try {
                sleep(1000);
            } catch (InterruptedException ignored) {
                System.exit(0);
            }
        }
    }
}
