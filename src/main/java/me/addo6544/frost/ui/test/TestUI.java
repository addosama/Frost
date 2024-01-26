package me.addo6544.frost.ui.test;

import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.ui.guihelper.FrostUI;
import me.addo6544.frost.ui.guihelper.widget.impl.CButton;
import me.addo6544.frost.ui.guihelper.widget.impl.CDebug;
import me.addo6544.frost.utils.ChatHelper;
import me.addo6544.frost.utils.RenderUtil;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

public class TestUI extends FrostUI {
    public CDebug debugInfo = new CDebug(this);


    private final FontRenderer fr = Fonts.mc;
    public TestUI() {
        super(false);
        widgets.add(debugInfo);
        widgets.add(b1);

    }


    private CButton b1 = new CButton(50,50,50,25,this) {
        @Override
        public void drawWidget() {
            RenderUtil.drawRect(x,y,width,height,new Color(54, 180, 222).getRGB());
            fr.drawStringWithShadow("Button",
                    x+(width- fr.getStringWidth("Button"))/2,
                    y+(height-fr.FONT_HEIGHT)/2,
                    -1
            );
        }

        @Override
        public void onClick(int mouseX, int mouseY, int mouseButton) {
            ChatHelper.addMessage("Clicked b1");
        }
    };

}
