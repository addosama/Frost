package me.addo6544.frost.commond;

public class Command {
    public String name,description,command,format;
    public Command(String name, String description, String command, String format){
        this.name = name;
        this.description = description;
        this.command = command;
        this.format = format;
    }

    public void run(String config){

    }
}
