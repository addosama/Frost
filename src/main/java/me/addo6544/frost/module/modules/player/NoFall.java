package me.addo6544.frost.module.modules.player;

import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventMotion;
import me.addo6544.frost.module.Category;
import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.utils.PacketSender;
import net.minecraft.network.play.client.C03PacketPlayer;

import java.util.Arrays;

public class NoFall extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "",
            "Packet",
            Arrays.asList("Packet")
            );
    public NoFall(){
        super("No Fall", "Remove fall damage", Category.Player);
        this.settings.addSetting(mode);
    }

    @EventTarget
    public void onMotionEvent(EventMotion event) {
        if (event.getType().equals(Event.Type.PRE)) {
            if (mc.thePlayer.fallDistance > 3.0) {
                if (mode.getConfigValue().equalsIgnoreCase("Packet")){
                    PacketSender.sendPacket(new C03PacketPlayer(true));
                }
                /***
                else if (mode.getConfigValue().equalsIgnoreCase("Vanilla")) {
                    event.setOnGround(true);
                }
                 ***/
                mc.thePlayer.fallDistance = 0;
            }
        }
    }

    @Override
    public String getTag() {
        return mode.getConfigValue();
    }


}
