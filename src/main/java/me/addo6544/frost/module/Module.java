package me.addo6544.frost.module;

//
// Created by AddoSama
// 2023/6/6 10:26
//

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.utils.SimpleConsoleFormatter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.Objects;
import java.util.logging.Logger;

public class Module implements Comparable<Module>{
        protected Logger logger;
        private boolean state;
        private String name;
        private String description;
        private int keyCode;
        private Category category;

        public final Minecraft mc = Minecraft.getMinecraft();

        public Module(String name, String description, int keyCode, Category category) {
            this.state = false;
            this.name = name;
            this.description = description;
            this.keyCode = keyCode;
            this.category = category;
            this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger(name));
        }

    public Module(String name, String description, Category category) {
        this.state = false;
        this.name = name;
        this.description = description;
        this.keyCode = Keyboard.KEY_NONE;
        this.category = category;
        this.logger = Logger.getLogger(name);
    }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
            if(state){
                Enable();
            }else Disable();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public void setKeyCode(int keyCode) {
            this.keyCode = keyCode;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void toggle()
        {
            setState(!state);
        }

        public void Enable() {
            Frost.INSTANCE.eventManager.register(this);
            if (!Frost.INSTANCE.loaded) return;
            onEnable();
        }
        public void Disable() {
            if (!Frost.INSTANCE.loaded) return;
            onDisable();
            Frost.INSTANCE.eventManager.unregister(this);
        }

        public void onEnable(){

        }

        public void onDisable(){

        }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Module o){
            return name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
