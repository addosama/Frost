package me.addo6544.frost.commond;

import me.addo6544.frost.commond.impl.Cmd_Help;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<Command> commands;
    public final String prefix = ">";

    public CommandManager(){
        this.commands = new ArrayList<>();
    }

    public void loadCommands(){
        addCommand(new Cmd_Help());
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void processCommand(String message){
        boolean processed = false;
        String[] m = message.split(" ");
        m[0] = m[0].replace(">", "");
        for (Command c : commands){
            if (c.command.equalsIgnoreCase(m[0])){
                StringBuilder cfgs = new StringBuilder();
                for (String s : m){
                    if (s.equalsIgnoreCase(m[0])) continue;
                    cfgs.append((cfgs.length() == 0) ? "" : " ").append(s);
                }
                c.run(cfgs.toString());
                processed = true;
            }
        }
        if (!processed) getCommand("help").run("");
    }

    public Command getCommand(String name){
        for (Command c : commands){
            if (c.name.equalsIgnoreCase(name)) return c;
        }
        return commands.get(0);
    }
}
