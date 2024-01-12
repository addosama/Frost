package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.utils.ChatHelper;

public class Cmd_Help extends Command {
    public Cmd_Help(){
        super("Help", "Show help", "help", "");
    }

    @Override
    public void run(String config) {
        ChatHelper.addMessage("Command Helps");
        for (Command c : Frost.INSTANCE.commandManager.getCommands()){
            ChatHelper.addMessage(c.name + ": >" + c.command + " " + c.format + " - " + c.description);
        }
    }
}
