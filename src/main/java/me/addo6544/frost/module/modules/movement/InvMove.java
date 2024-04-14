package me.addo6544.frost.module.modules.movement;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InvMove extends Module {

    public InvMove(){
        super("InvMove", "", Category.Movement);
    }

    @EventTarget
    public void update(EventUpdate e) {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] keyBindings = {gameSettings.keyBindForward, gameSettings.keyBindBack, gameSettings.keyBindLeft, gameSettings.keyBindRight, gameSettings.keyBindJump, gameSettings.keyBindSprint, gameSettings.keyBindSneak};
        for (KeyBinding keyBinding : keyBindings) {
            KeyBinding.setKeyBindState(keyBinding.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
        }
    }

}
