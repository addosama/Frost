package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;
import net.minecraft.util.ResourceLocation;

public class EventGetPlayerCape extends Event {

    private ResourceLocation r = null;
    public EventGetPlayerCape() {
        super(Type.PRE);
    }

    public ResourceLocation getR() {
        return r;
    }

    public void setR(ResourceLocation r) {
        this.r = r;
    }
}
