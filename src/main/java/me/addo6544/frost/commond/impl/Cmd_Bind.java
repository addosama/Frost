package me.addo6544.frost.commond.impl;

import me.addo6544.frost.commond.Command;

public class Cmd_Bind extends Command {
    public Cmd_Bind(){
        super("Bind", "Bind module to a key", "bind", "<module> <key>");
    }

    @Override
    public void run(String config) {
    }
}
