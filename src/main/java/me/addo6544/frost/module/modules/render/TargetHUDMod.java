package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.ui.hud.target.TargetHUD;

public class TargetHUDMod extends Module {
    private TargetHUD hud;
    public TargetHUDMod(){
        super("Target HUD", "Display target info", Category.Render);
        this.hud = new TargetHUD(5,50);
    }

    public TargetHUD getHud() {
        return hud;
    }

    @EventTarget
    public void onRender(EventRender2D e){
        hud.render();
    }
}
