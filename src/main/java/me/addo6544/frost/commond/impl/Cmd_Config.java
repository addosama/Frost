package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.config.configs.CurrentConfig;
import me.addo6544.frost.config.configs.ModuleConfig;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.utils.ChatHelper;

import java.io.File;

public class Cmd_Config extends Command {
    public Cmd_Config() {
        super("Config", "Control ConfigManager", "config", "[create/load/save] <name>");
    }

    @Override
    public void run(String config) {
        String[] s = config.split(" ", 2);
        if (s[0].equalsIgnoreCase("load")){
            Frost.INSTANCE.configManager.getCurrent().setCurrentModule(s[1]);
            if (!Frost.INSTANCE.configManager.getConfig(ModuleConfig.class).getPath().toFile().exists())
            {
                ChatHelper.addMessage("Theres no config named " + s[1]);
                return;
            }
            Frost.INSTANCE.configManager.getConfig(ModuleConfig.class).load();
            ChatHelper.addMessage("Loaded Config: " + s[1]);
        } else if (s[0].equalsIgnoreCase("save")){
            Frost.INSTANCE.configManager.getConfig(ModuleConfig.class).save();
            ChatHelper.addMessage("Config " + Frost.INSTANCE.configManager.getCurrent().currentModule() + " Saved");
        } else if (s[0].equalsIgnoreCase("create")){
            Frost.INSTANCE.configManager.getCurrent().setCurrentModule(s[1]);
            Frost.INSTANCE.configManager.getConfig(ModuleConfig.class).save();
            ChatHelper.addMessage("Config " + s[1] + " Created");
        }

        Frost.INSTANCE.configManager.getCurrent().save();
    }
}
