package me.addo6544.frost.notifications;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.ui.notification.Notification;
import me.addo6544.frost.utils.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;

public class NotificationManager {
    public ArrayList<Notification> notifications = new ArrayList<>();

    private ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());


    public NotificationManager(){
        Frost.INSTANCE.eventManager.register(this);
    }

    public void newNotification(Notification notification){
        notifications.add(notification);
    }

    @EventTarget
    public void onRender(EventRender2D e){
        if (!Frost.INSTANCE.loaded) return;
        if (notifications.isEmpty()) return;

        ArrayList<Notification> removeList = new ArrayList<>();

        for (Notification n : notifications){
            if (removeList.contains(n)) continue;
            if (n.isState()){
                float offSet = (notifications.indexOf(n)-1)*40;
                n.drawNotification(sr.getScaledHeight()-offSet);
            } else removeList.add(n);
        }

        if (removeList.isEmpty()) return;
        for (Notification r : removeList){
            notifications.remove(r);
        }
    }
}
