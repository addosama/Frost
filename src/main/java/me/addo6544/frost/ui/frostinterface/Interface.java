package me.addo6544.frost.ui.frostinterface;

import me.addo6544.frost.core.Frost;
import me.addo6544.frost.event.Event;
import me.addo6544.frost.event.EventTarget;
import me.addo6544.frost.event.events.EventDrawScreen;
import me.addo6544.frost.event.events.EventRender2D;
import me.addo6544.frost.ui.frostinterface.widgets.InterfaceWidget;
import me.addo6544.frost.ui.frostinterface.widgets.impl.WaterMark;

import java.util.Arrays;
import java.util.List;

public class Interface {
    private boolean state = false;
    private final List<InterfaceWidget> widgets = Arrays.asList(
            new WaterMark()
    );
    private InterfaceSettings settings;

    public Interface(){
        this.setState(true);
        this.settings = new InterfaceSettings(true);
    }

    @EventTarget
    public void onDrawScreen(EventDrawScreen e){
        if (e.getType() == Event.Type.POST){
            this.drawWidgets(e.getX(),e.getY(),e.getpTicks());
        }
    }

    @EventTarget
    public void onRender(EventRender2D e){
        this.drawInGameWidgets(-6544,-6544,-6544);
    }

    public void drawInGameWidgets(float mouseX, float mouseY, float pTicks){
        for (InterfaceWidget widget : widgets){
            if (
                    !widget.isState() || !widget.isInGameWidget()
            ) return;
            widget.drawWidget(mouseX, mouseY, pTicks);
        }
    }

    public void drawWidgets(float mouseX, float mouseY, float pTicks){
        for (InterfaceWidget widget : widgets){
            if (
                    !widget.isState() || widget.isInGameWidget()
            ) return;
            widget.drawWidget(mouseX, mouseY, pTicks);
        }
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if (state) Frost.INSTANCE.eventManager.register(this);
        else Frost.INSTANCE.eventManager.unregister(this);
    }

    public List<InterfaceWidget> getWidgets() {
        return widgets;
    }
}
