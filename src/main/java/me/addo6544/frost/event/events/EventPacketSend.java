package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;
import net.minecraft.network.Packet;

public class EventPacketSend extends Event {
    private Packet packet;
    public EventPacketSend(Type type, Packet packet) {
        super(type);
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
