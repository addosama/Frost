package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;
import net.minecraft.network.Packet;

public class EventPacketReceive extends Event {
    private Packet packet;
    public EventPacketReceive(Type type, Packet packet) {
        super(type);
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
