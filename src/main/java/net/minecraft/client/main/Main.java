package net.minecraft.client.main;

import frost.loader.FrostLoader;

public class Main
{
    public static void main(String[] p_main_0_)
    {
        /***
        String token = "";

        for (String s : p_main_0_){
            if (s.contains("frostToken")){
                //System.out.println(s);
                String[] s1;
                s1 = s.split("=", 2);
                //System.out.println(s1[0]);
                //System.out.println(s1[1]);
                token = s1[1];
            }
        }

        if (token.isEmpty()) return;
         ***/

        FrostLoader.run(p_main_0_);
    }
}
