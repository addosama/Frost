package me.addo6544.frost.config.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.addo6544.frost.config.Config;
import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.ModuleSettings;
import me.addo6544.frost.module.setting.Setting;
import me.addo6544.frost.module.setting.SettingBase;
import me.addo6544.frost.module.setting.SettingGroup;
import me.addo6544.frost.module.setting.exceptions.InvalidModeException;
import me.addo6544.frost.module.setting.settings.*;
import org.lwjgl.Sys;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModuleConfig extends Config {

    public ModuleConfig(){
        Path modules = Paths.get(Frost.CLIENT_PATH, "modules");

        if (!modules.toFile().exists()){
            modules.toFile().mkdirs();
        }
    }

    @Override
    public Path getPath() {
        return Paths.get(Frost.CLIENT_PATH, "modules", Frost.INSTANCE.configManager.getCurrent().currentModule()+".json");
    }

    @Override
    public void load(){

        try {
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(getPath()), StandardCharsets.UTF_8), JsonObject.class);
            JsonObject clientModules = jsonObject.getAsJsonObject("clientModules");
            JsonObject externModules = jsonObject.getAsJsonObject("externModules");
            for (Module module : Frost.INSTANCE.moduleManager.getModules()){
                if(clientModules.has(module.getName())){
                    module.setState(false);

                    JsonObject modJsonObject = clientModules.get(module.getName()).getAsJsonObject();
                    if (modJsonObject.has("keybind")){
                        module.setKeyCode(modJsonObject.get("keybind").getAsInt());
                    }
                    if (modJsonObject.has("settings")){
                        JsonObject modSetJsonO = modJsonObject.get("settings").getAsJsonObject();
                        ModuleSettings ms = module.getSettings();

                        for (SettingBase base : ms.getSettings()){
                            if (modSetJsonO.has(base.name)){
                                if (base.isGroup){
                                    SettingGroup group = (SettingGroup) base;
                                    JsonObject j = modSetJsonO.get(base.name).getAsJsonObject();

                                    for (Setting s : group.getSettings()){
                                        if (j.has(s.getName())){
                                            if (s instanceof BooleanSetting)
                                                ((BooleanSetting) s).setState(j.get(s.name).getAsBoolean());
                                            if (s instanceof ColorSetting)
                                                ((ColorSetting) s).setColor(
                                                        new Color(
                                                                j.get(s.name).getAsInt()
                                                        )
                                                );
                                            if (s instanceof NumberSetting)
                                                ((NumberSetting) s).setValue(j.get(s.name).getAsDouble());
                                            if (s instanceof ModeSetting)
                                                try {
                                                    ((ModeSetting) s).setMode(j.get(s.name).getAsString());
                                                } catch (InvalidModeException e) {
                                                    System.out.println("Invalid Mode");
                                                }
                                            if (s instanceof TextSetting)
                                                ((TextSetting) s).setText(j.get(s.name).getAsString());
                                        }
                                    }
                                } else {
                                    Setting s = (Setting) base;
                                    if (s instanceof BooleanSetting)
                                        ((BooleanSetting) s).setState(modSetJsonO.get(s.getName()).getAsBoolean());
                                    if (s instanceof ColorSetting)
                                        ((ColorSetting) s).setColor(
                                                new Color(
                                                        modSetJsonO.get(s.getName()).getAsInt()
                                                )
                                        );
                                    if (s instanceof NumberSetting)
                                        ((NumberSetting) s).setValue(modSetJsonO.get(s.getName()).getAsDouble());
                                    if (s instanceof ModeSetting)
                                        try {
                                            ((ModeSetting) s).setMode(modSetJsonO.get(s.getName()).getAsString());
                                        } catch (InvalidModeException e) {
                                            System.out.println("Invalid Mode");
                                        }
                                    if (s instanceof TextSetting)
                                        ((TextSetting) s).setText(modSetJsonO.get(s.getName()).getAsString());
                                }
                            }
                        }

                    }

                    if (modJsonObject.has("state")){
                        module.setState(modJsonObject.get("state").getAsBoolean());
                    }

                } else if (externModules.has(module.getName())){
                    module.setState(false);

                    JsonObject modJsonObject = externModules.get(module.getName()).getAsJsonObject();
                    if (modJsonObject.has("keybind")){
                        module.setKeyCode(modJsonObject.get("keybind").getAsInt());
                    }
                    if (modJsonObject.has("settings")){
                        JsonObject modSetJsonO = modJsonObject.get("settings").getAsJsonObject();
                        ModuleSettings ms = module.getSettings();

                        for (SettingBase base : ms.getSettings()){
                            if (modSetJsonO.has(base.name)){
                                if (base.isGroup){
                                    SettingGroup group = (SettingGroup) base;
                                    JsonObject j = modSetJsonO.get(group.name).getAsJsonObject();

                                    for (Setting s : group.getSettings()){
                                        if (j.has(s.name)){
                                            if (s instanceof BooleanSetting)
                                                ((BooleanSetting) s).setState(j.get(s.name).getAsBoolean());
                                            if (s instanceof ColorSetting)
                                                ((ColorSetting) s).setColor(
                                                        new Color(
                                                                j.get(s.name).getAsInt()
                                                        )
                                                );
                                            if (s instanceof NumberSetting)
                                                ((NumberSetting) s).setValue(j.get(s.name).getAsDouble());
                                            if (s instanceof ModeSetting)
                                                try {
                                                    ((ModeSetting) s).setMode(j.get(s.name).getAsString());
                                                } catch (InvalidModeException e) {
                                                    System.out.println("Invalid Mode");
                                                }
                                            if (s instanceof TextSetting)
                                                ((TextSetting) s).setText(j.get(s.name).getAsString());
                                        }
                                    }
                                } else {
                                    Setting s = (Setting) base;
                                    if (s instanceof BooleanSetting)
                                        ((BooleanSetting) s).setState(modSetJsonO.get(s.getName()).getAsBoolean());
                                    if (s instanceof ColorSetting)
                                        ((ColorSetting) s).setColor(
                                                new Color(
                                                        modSetJsonO.get(s.getName()).getAsInt()
                                                )
                                        );
                                    if (s instanceof NumberSetting)
                                        ((NumberSetting) s).setValue(modSetJsonO.get(s.getName()).getAsDouble());
                                    if (s instanceof ModeSetting)
                                        try {
                                            ((ModeSetting) s).setMode(modSetJsonO.get(s.getName()).getAsString());
                                        } catch (InvalidModeException e) {
                                            System.out.println("Invalid Mode");
                                        }
                                    if (s instanceof TextSetting)
                                        ((TextSetting) s).setText(modSetJsonO.get(s.getName()).getAsString());
                                }
                            }
                        }

                    }

                    if (modJsonObject.has("state")){
                        module.setState(modJsonObject.get("state").getAsBoolean());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(){
        String name = Frost.INSTANCE.configManager.getCurrent().currentModule();
        JsonObject jsonObject = new JsonObject();
        JsonObject clientModules = new JsonObject();
        JsonObject externModules = new JsonObject();
        for (Module module : Frost.INSTANCE.moduleManager.getModules()){
            if (module.isExtern()) continue;
            JsonObject modJsonObject = new JsonObject();
            modJsonObject.addProperty("state", module.isState());
            modJsonObject.addProperty("keybind", module.getKeyCode());

            JsonObject modSetJsonO = new JsonObject();

            if (!module.getSettings().getSettings().isEmpty()){
                ModuleSettings ms = module.getSettings();
                for (SettingBase set : ms.getSettings()){
                    if (set.isGroup){
                        JsonObject modSJ = new JsonObject();
                        SettingGroup settingGroup = (SettingGroup) set;
                        for (Setting s : settingGroup.getSettings()){
                            if (s instanceof NumberSetting)
                                modSJ.addProperty(s.getName(), ((NumberSetting) s).getValue());
                            else if (s instanceof ColorSetting)
                                modSJ.addProperty(s.getName(), ((ColorSetting) s).getConfigValue());
                            else if (s instanceof BooleanSetting)
                                modSJ.addProperty(s.getName(), ((BooleanSetting) s).getConfigValue());
                            else
                                modSJ.addProperty(s.getName(), (String) s.getConfigValue());
                        }
                        modSetJsonO.add(settingGroup.name, modSJ);
                    } else {
                        Setting s = (Setting) set;
                        if (s instanceof NumberSetting)
                            modSetJsonO.addProperty(s.getName(), ((NumberSetting) s).getValue());
                        else if (s instanceof ColorSetting)
                            modSetJsonO.addProperty(s.getName(), ((ColorSetting) s).getConfigValue());
                        else if (s instanceof BooleanSetting)
                            modSetJsonO.addProperty(s.getName(), ((BooleanSetting) s).getConfigValue());
                        else
                            modSetJsonO.addProperty(s.getName(), (String) s.getConfigValue());
                    }
                }
            }

            modJsonObject.add("settings", modSetJsonO);
            if (module.isExtern()){
                externModules.add(module.getName(), modJsonObject);
            } else {
                clientModules.add(module.getName(), modJsonObject);
            }
        }
        jsonObject.add("clientModules", clientModules);
        jsonObject.add("externModules", externModules);
        try {
            Files.write(getPath(), new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
