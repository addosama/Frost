package me.addo6544.frost.utils;

public class DelayHelper {
    private static long startTick;

    public static boolean delay(int tick){
        if (startTick == 0){
            startTick = System.currentTimeMillis();
        }else if (startTick + tick == System.currentTimeMillis()){
            startTick = 0;
            return true;
        }
        return false;
    }
}
