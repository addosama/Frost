package me.addo6544.frost.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatHelper {
    protected static Minecraft mc = Minecraft.getMinecraft();
    public static void addMessageWithoutPrefix(String msg){
        mc.thePlayer.addChatMessage(new ChatComponentText(msg));
    }

    public static void addMessage(String msg){
        addMessageWithoutPrefix(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "frost > " + EnumChatFormatting.RESET + msg);
    }
}
