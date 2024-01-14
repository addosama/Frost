package me.addo6544.frost.module;

//
// Created by AddoSama
// 2023/6/6 10:30
//

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventKey;
import me.addo6544.frost.module.modules.combat.AutoAttack;
import me.addo6544.frost.module.modules.movement.FastLiquid;
import me.addo6544.frost.module.modules.movement.Fly;
import me.addo6544.frost.module.modules.movement.Sprint;
import me.addo6544.frost.module.modules.other.Panic;
import me.addo6544.frost.module.modules.render.DebugUI;
import me.addo6544.frost.module.modules.render.FullBright;
import me.addo6544.frost.module.modules.render.Interface;
import me.addo6544.frost.utils.SimpleConsoleFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
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
        this.addModule(new Sprint());
        this.addModule(new FastLiquid());
        this.addModule(new Fly());
        this.addModule(new AutoAttack());
        this.addModule(new Panic());
        this.addModule(new DebugUI());
        this.addModule(new FullBright());

        this.resortModules();


        for (Module m : modules){
            logger.info("Loaded Module : " + m.getName());
        }

    }

    public void resortModules(){
        modules.sort(Module::compareTo);
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
