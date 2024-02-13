package me.addo6544.frost.extern;

import me.addo6544.frost.core.Frost;

import java.io.File;
import java.util.ArrayList;

public class ExternSystem {
    public static final String EXTERN_DIR = Frost.CLIENT_PATH+"\\extern";
    public static ArrayList<File> addonPaths;
    public static ArrayList<AddonContainer> addons;

    public static void init(){
        if (!new File(EXTERN_DIR).exists()){
            new File(EXTERN_DIR).mkdirs();
        }

        addons = new ArrayList<>();
        addonPaths = new ArrayList<>(JarLoader.getAllJarFile());
    }

    public static int loadAddons(){
        int i = 0;
        for (File f : addonPaths){
            AddonContainer a = new AddonContainer("file:"+f.getAbsolutePath());

            if (a.load()){
                addons.add(a);
                i = i+1;
            }
        }

        return i;
    }

    public static int unloadAddons(){
        int i = 0;
        for (AddonContainer a : addons){
            if (a.unload()){
                addons.remove(a);
                i = i+1;
            }
        }

        return i;
    }

    public static int reloadAddons(){
        int a1 = addons.size();
        if (unloadAddons() != a1){
            System.out.println("Failed to unload Addon");
        }

        return loadAddons();
    }
}
