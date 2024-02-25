package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.extern.ExternSystem;
import me.addo6544.frost.utils.ChatHelper;

public class Cmd_Reload extends Command {
    public Cmd_Reload() {
        super("Reload", "Reload a part", "reload", "<widget>");
    }

    @Override
    public void run(String config) {
        if (config.equalsIgnoreCase("modules")){
            Frost.INSTANCE.init(0);
        }else if (config.equalsIgnoreCase("configs")){
            Frost.INSTANCE.init(1);
        }else if (config.equalsIgnoreCase("commands")){
            Frost.INSTANCE.init(2);
        }else if (config.equalsIgnoreCase("fonts")){
            Frost.INSTANCE.init(3);
        }else if (config.equalsIgnoreCase("ui")){
            Frost.INSTANCE.init(4);
        }else if (config.equalsIgnoreCase("extern")){
            ExternSystem.reloadAddons();
        }
        else {
            ChatHelper.addMessage("Invalid Option");
            ChatHelper.addMessage("You can reload these part");
            ChatHelper.addMessage("modules,configs,commands,fonts,ui,extern");
        }
    }
}
