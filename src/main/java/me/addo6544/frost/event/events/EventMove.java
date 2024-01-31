package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;
import me.addo6544.frost.utils.MovementUtils;

public class EventMove extends Event {
    private double x,y,z;
    public EventMove(double x, double y, double z){
        super(Type.PRE);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setSpeed(double speed) {
        MovementUtils.setSpeed(this, speed);
    }
}
