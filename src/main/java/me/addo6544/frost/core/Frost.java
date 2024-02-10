package me.addo6544.frost.core;

import me.addo6544.frost.auth.FrostUser;
import me.addo6544.frost.commond.CommandManager;
import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventManager;
import me.addo6544.frost.event.events.EventInit;
import me.addo6544.frost.module.ModuleManager;
import me.addo6544.frost.module.modules.render.DebugUI;
import me.addo6544.frost.module.modules.render.TargetHUDMod;
import me.addo6544.frost.ui.clickgui.ClickGui;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.hud.target.TargetHUD;
import me.addo6544.frost.ui.test.TestUI;
import me.addo6544.frost.utils.SimpleConsoleFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.util.logging.Logger;

public enum Frost {
    INSTANCE;

    //Client Info
    public static final String CLIENT_NAME = "Frost";
    public static final float CLIENT_VERSION = 1.5F;
    //public static final String CLIENT_WEBSITE = "frostmc.gg";
    public static final ReleaseType RELEASE_TYPE = ReleaseType.Development;
    public static boolean loaded = false;

    //Managers
    public EventManager eventManager;
    public CommandManager commandManager;
    public ModuleManager moduleManager;

    //UI
    public FrostUI debugUI;
    public ClickGui CGUI_Classic;

    //Utils
    private Logger logger;
    private String loadState = "Please Wait";

    //Auth
    public FrostUser user;


    public void preInit(){
        this.logger = SimpleConsoleFormatter.installFormatter(Logger.getLogger("Client Core"));
        logger.info("Starting | Version: " + RELEASE_TYPE.getType() + " - " + CLIENT_VERSION);
        Display.setTitle("Starting " + CLIENT_NAME);
        loadState = "Initializing EventManager";
        this.eventManager = new EventManager();
        loadState = "Initializing ModuleManager";
        this.moduleManager = new ModuleManager();
        loadState = "Initializing CommandManager";
        this.commandManager = new CommandManager();
        Frost.INSTANCE.init();
    }

    public void init(){
        new EventInit(Event.Type.PRE).call();
        loadState = "Loading Modules";
        moduleManager.loadMods();
        loadState = "Loading Commands";
        commandManager.loadCommands();

        loadState = "Loading UI";
        TargetHUDMod tgHUD = (TargetHUDMod) moduleManager.getModule(TargetHUDMod.class);
        tgHUD.hud = new TargetHUD(5,50);
        debugUI = new TestUI();
        CGUI_Classic = new ClickGui();

        loadState = "Finishing";
        Display.setTitle(CLIENT_NAME + " | " + RELEASE_TYPE.getType() + " " + CLIENT_VERSION);
        loaded = true;
        new EventInit(Event.Type.POST).call();
    }

    public String getLoadState() {
        return loadState;
    }

    public void setUser(FrostUser user) {
        this.user = user;
    }
}

