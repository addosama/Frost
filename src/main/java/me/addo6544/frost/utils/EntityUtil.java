package me.addo6544.frost.utils;

import net.minecraft.entity.EntityLivingBase;

public class EntityUtil {

    public static float getYawToEntity(EntityLivingBase player, EntityLivingBase target){

        double xmax = Math.abs(Math.max(player.posX, target.posX));
        double xmin = Math.abs(Math.min(player.posX, target.posX));

        double zmax = Math.abs(Math.max(player.posZ, target.posZ));
        double zmin = Math.abs(Math.min(player.posZ, target.posZ));

        double a = xmax - xmin;
        double b = zmax - zmin;
        double c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

        double yaw;

        yaw = Math.asin(a/c);

        return (float) yaw;
    }
}
