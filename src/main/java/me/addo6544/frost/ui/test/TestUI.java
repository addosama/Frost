package me.addo6544.frost.ui.test;

import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.guihelper.widget.impl.CDebug;

public class TestUI extends FrostUI {
    public CDebug debugInfo = new CDebug(this);
    public TestUI() {
        super(false);
        widgets.add(debugInfo);
    }


}
