package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.utils.ChatHelper;
import org.lwjgl.input.Keyboard;

public class Cmd_Bind extends Command {
    public Cmd_Bind(){
        super("Bind", "Bind module to a key", "bind", "<module> <key>");
    }

    @Override
    public void run(String config) {
        String[] s = config.split(" ");
        Module m = Frost.INSTANCE.moduleManager.getModuleByName(s[0], true);
        int bind = Keyboard.getKeyIndex(s[1].toUpperCase());

        if (m == null){
            ChatHelper.addMessage("Module " + s[0] + " not found");
            return;
        }

        m.setKeyCode(bind);
        if (bind == 0){
            ChatHelper.addMessage("Module " + m.getName() + " was unbound");
        }else {
            ChatHelper.addMessage("Module " + m.getName() + " was bound to " + Keyboard.getKeyName(bind));
        }

    }
}
