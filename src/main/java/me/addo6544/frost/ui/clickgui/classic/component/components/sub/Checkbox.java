package me.addo6544.frost.ui.clickgui.classic.component.components.sub;

import me.addo6544.frost.setting.Setting;
import me.addo6544.frost.setting.settings.BooleanSetting;
import me.addo6544.frost.ui.clickgui.classic.component.Component;
import me.addo6544.frost.ui.clickgui.classic.component.components.Button;
import me.addo6544.frost.ui.font.Fonts;
import net.minecraft.client.gui.Gui;

import java.awt.Color;

/**
 *  Made by Pandus1337
 *  it's free to use,
 *  but you have to credit me
 *  @author Pandus1337
 *  <a href="https://github.com/Pandus1337/ClickGUI">...</a>
 */

public class Checkbox extends Component {

	private boolean hovered;
	private BooleanSetting op;
	private Button parent;
	private int offset;
	private int x;
	private int y;
	
	public Checkbox(Setting option, Button button, int offset) {
		this.op = (BooleanSetting) option;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth()), parent.parent.getY() + offset + 12, this.hovered ? new Color(20, 20, 20, 191).getRGB() : new Color(0, 0, 0, 191).getRGB());
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, new Color(0, 0, 0, 191).getRGB());
		Gui.drawRect(parent.parent.getX() + 1 + 5, parent.parent.getY() + offset + 3, parent.parent.getX() + 9 + 3, parent.parent.getY() + offset + 9,  new Color(89, 89, 89, 191).getRGB());
		Fonts.HMRegular18.drawString(this.op.getName(), (parent.parent.getX()) + 17, (parent.parent.getY() + offset + (6-(Fonts.HMRegular18.FONT_HEIGHT/2))), new Color(255, 255, 255, 255).getRGB());
		if (this.op.getConfigValue()) {
			Gui.drawRect(parent.parent.getX() + 1 + 5, parent.parent.getY() + offset + 3, parent.parent.getX() + 9 + 3, parent.parent.getY() + offset + 9,  new Color(0, 153, 235, 255).getRGB());
		}
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
			this.op.setState(!op.getConfigValue());;
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
	}
}
