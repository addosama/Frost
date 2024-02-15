package me.addo6544.frost.config;

import java.nio.file.Path;

public abstract class Config {
    public abstract Path getPath();
    public abstract void load();
    public abstract void save();
}