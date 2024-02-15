package me.addo6544.frost.utils;

import net.minecraft.entity.EntityLivingBase;

public class EntityUtil {

    public static float getYawToEntity(EntityLivingBase player, EntityLivingBase target){

        double xmax = Math.abs(Math.max(player.posX, target.posX));
        double xmin = Math.abs(Math.min(player.posX, target.posX));

        double zmax = Math.abs(Math.max(player.posZ, target.posZ));
        double zmin = Math.abs(Math.min(player.posZ, target.posZ));

        double a,b,c;

        double yaw;



        if (xmin == player.posX){
            if (zmin == player.posZ){
                a = xmax - xmin;
                b = zmax - zmin;
                c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

                yaw = Math.asin(a/c);

                return (float) -yaw;
            }

            if (zmax == player.posZ){
                a = zmax - zmin;
                b = xmax - xmin;
                c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

                yaw = Math.asin(a/c);
                return (float) -(90+yaw);
            }
        } else if (xmax == player.posX) {
            if (zmax == player.posZ){
                a = xmax - xmin;
                b = zmax - zmin;
                c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

                yaw = Math.asin(a/c);
                return (float) (180-yaw);
            }

            if (zmin == player.posZ){
                a = zmax - zmin;
                b = xmax - xmin;
                c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

                yaw = Math.asin(a/c);
                return (float) (90-yaw);
            }
        }

        return 0;
    }
}
