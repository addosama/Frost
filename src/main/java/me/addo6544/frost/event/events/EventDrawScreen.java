package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;

public class EventDrawScreen extends Event {
    private final int x,y;
    private final float pTicks;
    public EventDrawScreen(Type type, int x, int y, float pTicks) {
        super(type);
        this.x = x;
        this.y = y;
        this.pTicks = pTicks;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getpTicks() {
        return pTicks;
    }
}
