package me.addo6544.frost.module.modules.world;

import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventUpdate;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;

public class FastPlace extends Module {
    public FastPlace(){
        super("FastPlace", "", Category.World);
    }

    @EventTarget
    public void update(EventUpdate e) {
        if(Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null){
            if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock){
                Minecraft.getMinecraft().rightClickDelayTimer = 0;
            }
        }
    }

}
