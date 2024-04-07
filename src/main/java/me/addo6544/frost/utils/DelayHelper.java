package me.addo6544.frost.utils;

public class DelayHelper {
    private long startTick = 0;
    private long delayTick = 0;
    private boolean delaying;

    public DelayHelper(){
    }

    public boolean delay(){
        if (delaying) {
            if (startTick+delayTick >= System.currentTimeMillis()){
                delaying = false;
                return true;
            }
            return false;
        } else {
            delaying = true;
            startTick = System.currentTimeMillis();
            if (startTick+delayTick >= System.currentTimeMillis()){
                delaying = false;
                return true;
            }
            return false;
        }
    }



    public void reset(long millisecond){
        startTick = 0;
        delayTick = millisecond;
        delaying = false;
    }

    public boolean isDelaying() {
        return delaying;
    }

    public long getDelay() {
        return delayTick;
    }
}
