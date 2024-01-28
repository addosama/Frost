package me.addo6544.frost.ui.clickgui.component.components.sub;

import me.addo6544.frost.module.Module;
import me.addo6544.frost.module.setting.Setting;
import me.addo6544.frost.module.setting.exceptions.InvalidModeException;
import me.addo6544.frost.module.setting.settings.ModeSetting;
import me.addo6544.frost.ui.clickgui.component.Component;
import me.addo6544.frost.ui.clickgui.component.components.Button;
import me.addo6544.frost.ui.font.Fonts;
import me.addo6544.frost.utils.ChatHelper;
import net.minecraft.client.gui.Gui;

import java.awt.Color;

/**
 *  Made by Pandus1337
 *  it's free to use,
 *  but you have to credit me
 *  @author Pandus1337
 *  <a href="https://github.com/Pandus1337/ClickGUI">...</a>
 */

public class ModeButton extends Component {

	private boolean hovered;
	private Button parent;
	private ModeSetting set;
	private int offset;
	private int x;
	private int y;
	private Module mod;

	private int modeIndex;
	
	public ModeButton(Setting set, Button button, Module mod, int offset) {
		this.set = (ModeSetting) set;
		this.parent = button;
		this.mod = mod;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		this.modeIndex = 0;
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX() + 1, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth()), parent.parent.getY() + offset + 12, this.hovered ? new Color(20, 20, 20, 191).getRGB() : new Color(0, 0, 0, 191).getRGB());
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, new Color(0, 0, 0, 191).getRGB());
		Fonts.HMRegular18.drawString(set.getName() + ": " + set.getConfigValue(), (parent.parent.getX() + 6), (parent.parent.getY() + offset) + (6-(Fonts.HMRegular18.FONT_HEIGHT/2)), new Color(255, 255, 255, 255).getRGB());
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && this.parent.open) {
			int maxIndex = set.getModes().size();

			if (button == 0){
				if(modeIndex + 1 >= maxIndex)
					modeIndex = 0;
				else
					modeIndex++;
			}else if (button == 1){
				if(modeIndex - 1 < 0)
					modeIndex = maxIndex-1;
				else
					modeIndex--;
			}


            try {
                set.setMode(set.getModes().get(modeIndex));
            } catch (InvalidModeException e) {
				ChatHelper.addMessage("诶呦我草你干什么呢老弟");
            }
        }
	}

	public boolean isMouseOnButton(int x, int y) {
		return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
	}
}
