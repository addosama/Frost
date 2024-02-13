package me.addo6544.frost.extern;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarLoader {
    public static List<File> getAllJarFile(){
        List<File> jars = new ArrayList<>();
        File dir = new File(ExternSystem.EXTERN_DIR);
        File[] files = dir.listFiles();

        if (files != null){
            for (File file : files){
                if (file.isFile() && file.getName().endsWith(".jar")){
                    jars.add(file);
                }
            }
        }

        return jars;
    }
}
