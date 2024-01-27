package me.addo6544.frost.ui.clickgui.component.components.sub;

import me.addo6544.frost.module.setting.Setting;
import me.addo6544.frost.module.setting.settings.NumberSetting;
import me.addo6544.frost.ui.clickgui.component.Component;
import me.addo6544.frost.ui.clickgui.component.components.Button;
import me.addo6544.frost.ui.font.Fonts;
import net.minecraft.util.EnumChatFormatting;

import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Made by Pandus1337
 *  it's free to use,
 *  but you have to credit me
 *  @author Pandus1337
 *  <a href="https://github.com/Pandus1337/ClickGUI">...</a>
 */

public class Slider extends Component {

	private boolean hovered;

	private NumberSetting set;
	private Button parent;
	private int offset;
	private int x;
	private int y;
	private boolean dragging = false;

	private double renderWidth;

	public Slider(Setting value, Button button, int offset) {
		this.set = (NumberSetting) value;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + parent.parent.getWidth(), parent.parent.getY() + offset + 12, this.hovered ? new Color(20, 20, 20, 191).getRGB() : new Color(0, 0, 0, 191).getRGB());
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (int) renderWidth, parent.parent.getY() + offset + 12, new Color(150, 150, 150, 128).getRGB());
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, new Color(0, 0, 0, 191).getRGB());
		Fonts.HMRegular18.drawString(EnumChatFormatting.WHITE + this.set.getName() + ": " + EnumChatFormatting.RESET + this.set.getValue().doubleValue() , (parent.parent.getX() + 6), (parent.parent.getY() + offset) + (6-(Fonts.HMRegular18.FONT_HEIGHT/2)), new Color(255, 255, 255, 255).getRGB());
	}

	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}

	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();

		double diff = Math.min(88, Math.max(0, mouseX - this.x));

		double min = set.getMin().doubleValue();
		double max = set.getMax().doubleValue();

		renderWidth = (88) * (set.getValue().doubleValue() - min) / (max - min);

		if (dragging) {
			if (diff == 0) {
				set.setValue(set.getMin());
			}
			else {
				double newValue = roundToPlace(((diff / 88) * (max - min) + min));
				set.setValue(newValue);
			}
		}
	}

	private static double roundToPlace(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
		if(isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		dragging = false;
	}

	public boolean isMouseOnButtonD(int x, int y) {
		return x > this.x && x < this.x + (parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 12;
	}

	public boolean isMouseOnButtonI(int x, int y) {
		return x > this.x + parent.parent.getWidth() / 2 && x < this.x + parent.parent.getWidth() && y > this.y && y < this.y + 12;
	}
}