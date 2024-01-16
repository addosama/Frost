package me.addo6544.frost.module.setting.exceptions;

public class InvalidModeException extends Exception{
    public final String mode;

    public InvalidModeException(String mode){
        super("Invalid Mode: " + mode);
        this.mode = mode;
    }
}
