package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;

public class DebugUI extends Module {
    public DebugUI(){
        super("Debug UI", "Draw debug GUIScreen", Category.Render);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Frost.INSTANCE.debugUI);
        setState(false);
    }
}
