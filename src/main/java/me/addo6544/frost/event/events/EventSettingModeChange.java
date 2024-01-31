package me.addo6544.frost.event.events;

import me.addo6544.frost.event.Event;

public class EventSettingModeChange extends Event {
    private final String oldMode;
    private final String newMode;
    public EventSettingModeChange(Type type, String oldMode, String newMode) {
        super(type);
        this.oldMode = oldMode;
        this.newMode = newMode;
    }

    public String getOldMode() {
        return oldMode;
    }

    public String getNewMode() {
        return newMode;
    }
}
