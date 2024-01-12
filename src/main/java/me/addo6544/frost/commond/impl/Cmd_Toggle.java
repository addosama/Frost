package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.utils.ChatHelper;

public class Cmd_Toggle extends Command {
    public Cmd_Toggle(){
        super("Toggle", "Toggle a module", "t", "<module>");
    }

    @Override
    public void run(String config) {
        Module m = Frost.INSTANCE.moduleManager.getModuleByName(config, true);
        if (m == null){
            ChatHelper.addMessage("Module " + config + " not found");
        }else {
            m.toggle();
            String text = m.isState() ? "enabled" : "disabled";
            ChatHelper.addMessage("Module " + m.getName() + "was " + text);
        }
    }
}
