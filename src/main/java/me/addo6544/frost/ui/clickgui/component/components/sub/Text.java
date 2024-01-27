package me.addo6544.frost.ui.clickgui.component.components.sub;

import me.addo6544.frost.ui.clickgui.component.Component;
import me.addo6544.frost.ui.clickgui.component.components.Button;
import me.addo6544.frost.ui.font.Fonts;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Text extends Component {
    private Button parent;
    private String text;
    private int offset;
    public Text(Button parent, String text, int offset){
        this.text = text;
        this.offset = offset;
        this.parent = parent;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth()), parent.parent.getY() + offset + 12, new Color(0, 0, 0, 191).getRGB());
        Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, new Color(0, 0, 0, 191).getRGB());
        Fonts.HMRegular18.drawCenteredString(text, (parent.parent.getX() + ((float) parent.parent.getWidth() /2)), parent.parent.getY() + offset + (6-(Fonts.HMRegular18.FONT_HEIGHT/2)), new Color(255, 255, 255, 255).getRGB(),false);
    }
}
