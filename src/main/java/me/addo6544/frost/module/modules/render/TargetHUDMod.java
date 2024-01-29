package me.addo6544.frost.module.modules.render;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.modules.combat.AutoAttack;
import me.addo6544.frost.ui.hud.target.TargetHUD;
import net.minecraft.client.entity.AbstractClientPlayer;

public class TargetHUDMod extends Module {
    private TargetHUD hud;
    public TargetHUDMod(){
        super("Target HUD", "Display target info", Category.Render);
        this.hud = new TargetHUD(5,50);
    }

    public TargetHUD getHud() {
        return hud;
    }
}
