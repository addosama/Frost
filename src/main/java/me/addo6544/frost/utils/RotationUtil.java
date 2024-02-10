package me.addo6544.frost.utils;

import net.minecraft.client.Minecraft;

public class RotationUtil {
    protected static Minecraft mc = Minecraft.getMinecraft();
    public static void setVisualRotations(float yaw, float pitch) {
        mc.thePlayer.rotationYawHead = mc.thePlayer.renderYawOffset = yaw;
        mc.thePlayer.rotationPitchHead = pitch;
    }
}
