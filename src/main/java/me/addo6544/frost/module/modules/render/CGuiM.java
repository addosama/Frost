package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

public class CGuiM extends Module {
    public ModeSetting mode = new ModeSetting("Style", "Only Classic yet",
            "Classic",
            Arrays.asList("Classic", "FullScreen")
            );
    public CGuiM(){
        super("Click Gui", "", Keyboard.KEY_RSHIFT, Category.Render);
        settings.addSetting(mode);
    }

    @Override
    public void onEnable(){
        mc.displayGuiScreen(Frost.INSTANCE.CGUI_Classic);
        /*
        if (mode.getConfigValue().equalsIgnoreCase("Classic")){
            mc.displayGuiScreen(Frost.INSTANCE.CGUI_Classic);
        } else if (mode.getConfigValue().equalsIgnoreCase("FullScreen")){
            mc.displayGuiScreen(Frost.INSTANCE.CGUI_FS);
        }
        */
        setState(false);

    }
}
