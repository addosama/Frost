package me.addo6544.frost.module;

//
// Created by AddoSama
// 2023/6/6 10:30
//

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventKey;
import me.addo6544.frost.module.modules.combat.AutoAttack;
import me.addo6544.frost.module.modules.combat.KillAura;
import me.addo6544.frost.module.modules.combat.Velocity;
import me.addo6544.frost.module.modules.movement.Fly;
import me.addo6544.frost.module.modules.movement.NoSlow;
import me.addo6544.frost.module.modules.movement.Speed;
import me.addo6544.frost.module.modules.movement.Sprint;
import me.addo6544.frost.module.modules.other.NoCommands;
import me.addo6544.frost.module.modules.other.Panic;
import me.addo6544.frost.module.modules.player.AntiDebuff;
import me.addo6544.frost.module.modules.player.NoFall;
import me.addo6544.frost.module.modules.render.*;
import me.addo6544.frost.module.modules.world.AntiBan;
import me.addo6544.frost.module.setting.Setting;
import me.addo6544.frost.module.setting.SettingBase;
import me.addo6544.frost.module.setting.SettingGroup;
import me.addo6544.frost.utils.SimpleConsoleFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class ModuleManager {
    private ArrayList<Module> modules;
    private Logger logger;
    public ModuleManager() {
        this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger("ModuleManager"));
        this.modules = new ArrayList();
        Frost.INSTANCE.eventManager.register(this);
    }

    public void loadMods() {
        this.addModule(new Interface());
        this.addModule(new CGuiM());
        this.addModule(new Sprint());
        //this.addModule(new FastLiquid());
        this.addModule(new Fly());
        this.addModule(new AutoAttack());
        this.addModule(new Panic());
        this.addModule(new AntiBan());
        this.addModule(new NoSlow());
        this.addModule(new DebugUI());
        this.addModule(new FullBright());
        this.addModule(new AntiDebuff());
        this.addModule(new NoCommands());
        this.addModule(new TargetHUDMod());
        this.addModule(new Velocity());
        this.addModule(new Speed());
        this.addModule(new NoFall());
        this.addModule(new KillAura());

        this.loadExtern();

        this.resortModules();


        for (Module m : modules){
            logger.info("Loaded Module : " + m.getName());
        }

        printInfo();
    }

    private void printInfo(){
        logger.info("Loaded " + getModulesCount() + " modules and " + getSettingsCount() + " settings");
    }

    public int getModulesCount(){
        return modules.size();
    }

    public int getSettingsCount(){
        int s = 0;
        for (Module m : modules){
            for (SettingBase b : m.getSettings().getSettings()){
                if (b.isGroup){
                    SettingGroup sg = (SettingGroup) b;
                    s = s + sg.getSettings().size();
                }else {
                    s = s+1;
                }
            }
        }
        return s;
    }

    public void resortModules(){
        modules.sort(Module::compareTo);
    }

    public void loadExtern(){

    }


    private void addModule(Module m) {
        modules.add(m);
    }

    @EventTarget
    public void onKey(EventKey eventKeyBoard) {
        for (Module mod : modules) {
            if (mod.getKeyCode() == eventKeyBoard.getKey())
                mod.toggle();
        }
    }

    public Module getModuleByName(String name, boolean removeSpace){
        if (removeSpace){
            for (Module m : modules){
                if (m.getName().replace(" ", "").equalsIgnoreCase(name)) return m;
            }
        }else {
            for (Module m : modules){
                if(m.getName().equalsIgnoreCase(name)) return m;
            }
        }

        return null;
    }

    public Module getModule(Class<? extends Module> cls){
        for (Module module : modules){
            if (module.getClass() == cls) return module;
        }
        return null;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
    public ArrayList<Module> getModuleByCategory(Category category){
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : modules){
            if(module.getCategory() == category) arrayList.add(module);
        }
        return arrayList;
    }
    public ArrayList<Category> getAllCategories(){
        return new ArrayList<>(Arrays.asList(Category.values()));
    }
}
