package me.addo6544.frost.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class PacketSender {
    public static void sendPacket(Packet packet){
        sendPacket(packet,true);
    }

    public static void sendPacketNoEvent(Packet packet){
        sendPacket(packet,false);
    }

    public static void sendPacket(Packet packet, boolean event){
        Minecraft.getMinecraft().thePlayer.sendQueue.getNetworkManager().sendPacket(packet, event);
    }

}
