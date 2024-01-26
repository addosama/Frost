package me.addo6544.frost.core;

import me.addo6544.frost.commond.CommandManager;
import me.addo6544.frost.event.EventManager;
import me.addo6544.frost.module.ModuleManager;
import me.addo6544.frost.module.modules.render.DebugUI;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.test.TestUI;
import me.addo6544.frost.utils.SimpleConsoleFormatter;
import org.lwjgl.opengl.Display;

import java.util.logging.Logger;

public enum Frost {
    INSTANCE;

    //Client Info
    public static final String CLIENT_NAME = "Frost";
    public static final float CLIENT_VERSION = 1.0F;
    public static final String CLIENT_WEBSITE = "frostmc.gg";
    public static final ReleaseType RELEASE_TYPE = ReleaseType.Development;
    public static boolean loaded = false;

    //Managers
    public EventManager eventManager;
    public CommandManager commandManager;
    public ModuleManager moduleManager;

    //UI
    public FrostUI debugUI;

    //Utils
    private Logger logger;


    public void preInit(){
        this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger("Client Core"));
        logger.info("Starting | Version: " + RELEASE_TYPE.getType() + " - " + CLIENT_VERSION);
        Display.setTitle("Starting " + CLIENT_NAME);
        this.eventManager = new EventManager();
        this.moduleManager = new ModuleManager();
        this.commandManager = new CommandManager();

        init();
    }

    public void init(){
        moduleManager.loadMods();
        commandManager.loadCommands();

        debugUI = new TestUI();

        Display.setTitle(CLIENT_NAME + " | " + RELEASE_TYPE.getType() + " " + CLIENT_VERSION);
        loaded = true;
    }
}

