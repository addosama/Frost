package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.utils.ChatHelper;

public class Cmd_Modules extends Command {
    public Cmd_Modules() {
        super("Modules", "List all Modules", "modules", "");
    }

    @Override
    public void run(String config) {
        ChatHelper.addMessage("All Modules:");
        for (Module m : Frost.INSTANCE.moduleManager.getModules()){
            ChatHelper.addMessage(m.getName() + " - " + m.getDescription());
        }
        ChatHelper.addMessage("==================");
    }
}
