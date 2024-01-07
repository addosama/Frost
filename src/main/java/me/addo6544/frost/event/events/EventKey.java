package me.addo6544.frost.event.events;


import me.addo6544.frost.event.Event;

public class EventKey extends Event {
    private int k;

    public EventKey(int k){
        super(Type.PRE);
        this.k = k;
    }

    public int getKey(){
        return k;
    }
}
