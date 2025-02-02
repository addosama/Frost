package me.addo6544.frost.ui.clickgui.classic.component.components;

import java.awt.Color;
import java.util.ArrayList;

import me.addo6544.frost.module.Module;
import me.addo6544.frost.setting.Setting;
import me.addo6544.frost.setting.SettingBase;
import me.addo6544.frost.setting.SettingGroup;
import me.addo6544.frost.setting.settings.BooleanSetting;
import me.addo6544.frost.setting.settings.ModeSetting;
import me.addo6544.frost.setting.settings.NumberSetting;
import me.addo6544.frost.ui.clickgui.classic.component.Component;
import me.addo6544.frost.ui.clickgui.classic.component.Frame;
import me.addo6544.frost.ui.clickgui.classic.component.components.sub.*;
import me.addo6544.frost.ui.font.Fonts;
import net.minecraft.client.gui.Gui;


/**
 *  Made by Pandus1337
 *  it's free to use,
 *  but you have to credit me
 *  @author Pandus1337
 *  <a href="https://github.com/Pandus1337/ClickGUI">...</a>
 */

public class Button extends Component {

	public Module mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	private ArrayList<Component> subcomponents;
	public boolean open;
	private int height;
	
	public Button(Module mod, Frame parent, int offset) {
		this.mod = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<>();
		this.open = false;
		height = 12;
		int opY = offset + 12;
		if(mod.getSettings().getSettings() != null) {
			for(SettingBase se : mod.getSettings().getSettings()){
				if (se.isGroup){
					SettingGroup s = (SettingGroup) se;
					Text t = new Text(this, s.name, opY);
					this.subcomponents.add(t);
					opY += 12;
					for (Setting s1 : s.getSettings()){
						if(s1 instanceof ModeSetting){
							this.subcomponents.add(new ModeButton(s1, this, mod, opY));
							opY += 12;
						}
						if(s1 instanceof NumberSetting){
							this.subcomponents.add(new Slider(s1, this, opY));
							opY += 12;
						}
						if(s1 instanceof BooleanSetting){
							this.subcomponents.add(new Checkbox(s1, this, opY));
							opY += 12;
						}
					}
				}else {
					Setting s = (Setting) se;
					if(s instanceof ModeSetting){
						this.subcomponents.add(new ModeButton(s, this, mod, opY));
						opY += 12;
					}
					if(s instanceof NumberSetting){
						this.subcomponents.add(new Slider(s, this, opY));
						opY += 12;
					}
					if(s instanceof BooleanSetting){
						this.subcomponents.add(new Checkbox(s, this, opY));
						opY += 12;
					}
				}
			}
		}
		this.subcomponents.add(new Keybind(this, opY));
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
		int opY = offset + 12;
		for(Component comp : this.subcomponents) {
			comp.setOff(opY);
			opY += 12;
		}
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, this.isHovered ? (this.mod.isState() ? new Color(0, 153, 235, 255).darker().getRGB() : new Color(15, 15, 15, 191).getRGB()) : (this.mod.isState() ? new Color(0, 153, 235, 255).getRGB() : new Color(30, 30, 30, 191).getRGB()));
		Fonts.HMRegular18.drawString(this.mod.getName(), (parent.getX() + 2) + 2, (parent.getY() + offset + (6-(Fonts.HMRegular18.FONT_HEIGHT/2))) + 1, new Color(255, 255, 255).getRGB());
		if(this.subcomponents.size() >= 1)
			Fonts.HMRegular18.drawString(this.open ? "-" : "+", (parent.getX() + parent.getWidth() - 10), (parent.getY() + offset) + (6-(Fonts.HMRegular18.FONT_HEIGHT/2)), new Color(255, 255, 255, 255).getRGB());
		if(this.open) {
			if(!this.subcomponents.isEmpty()) {
				for(Component comp : this.subcomponents) {
					comp.renderComponent();
				}
				Gui.drawRect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), new Color(0, 153, 235, 255).getRGB());
			}
		}
	}


	@Override
	public int getHeight() {
		if(this.open) {
			return (12 * (this.subcomponents.size() + 1));
		}
		return 12;
	}

	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.isHovered = isMouseOnButton(mouseX, mouseY);
		if(!this.subcomponents.isEmpty()) {
			for(Component comp : this.subcomponents) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0) {
			this.mod.toggle();
		}
		if(isMouseOnButton(mouseX, mouseY) && button == 1) {
			this.open = !this.open;
			this.parent.refresh();
		}
		for(Component comp : this.subcomponents) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for(Component comp : this.subcomponents) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void keyTyped(char typedChar, int key) {
		for(Component comp : this.subcomponents) {
			comp.keyTyped(typedChar, key);
		}
	}

	public boolean isMouseOnButton(int x, int y) {
		return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
	}

}
