package me.addo6544.frost.config;

import me.addo6544.frost.config.configs.CurrentConfig;
import me.addo6544.frost.config.configs.ModuleConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {


    private CurrentConfig current = new CurrentConfig();

    private List<Config> configs = new ArrayList<Config>();

    public ConfigManager(){
        this.addConfig(current);
        this.addConfig(new ModuleConfig());
    }

    public void load(){
        for (Config config : configs){
            if(config.getPath().toFile().exists()) {
                config.load();
            }
        }
    }

    public void save(){
        for (Config config : configs){
            if (!config.getPath().toFile().exists()){
                try{
                    Files.createDirectories(config.getPath().getParent());
                    config.getPath().toFile().createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            config.save();
        }
    }

    public void addConfig(Config c){
        configs.add(c);
    }

    public void removeConfig(Config c){
        configs.remove(c);
    }

    public Config getConfig(Class<? extends Config> c){
        for (Config config : configs){
            if (c.equals(config.getClass())) return config;
        }

        return current;
    }

    public CurrentConfig getCurrent() {
        return current;
    }
}