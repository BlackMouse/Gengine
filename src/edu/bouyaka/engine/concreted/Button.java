package edu.bouyaka.engine.concreted;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.abstracted.Interface;

public class Button extends Concrete {

	Graphics g;
	Font f;
	private Color textColor;
	private Color buttonColor;
	protected String text;
	@SuppressWarnings("unused")
	private int textSize;
	protected Interface fatherInterface;

	public Button() {
		setColor(new Color(0, 0, 0, 255));
		setTextColor(new Color(255, 255, 255, 255));
		setTextSize(engine.displayHeight / 60);
		type = "Button";
	}

	public void setSize(int rW, int rH) {
		this.size[0] = rW;
		this.size[1] = rH;
	}

	public void setColor(Color buttonColor) {
		this.buttonColor = buttonColor;

	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;

	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		f = new Font("Courier New", Font.BOLD, textSize);

	}

	public void addText(String text) {
		this.text = this.text + text;
	}

	public void replaceText(String text) {
		this.text = String.valueOf(text);
	}

	public void show() {
		g.setColor(buttonColor);
		g.fillRect((int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2), size[0], size[1]);
		g.setColor(textColor);
		g.setFont(f);
		try {
			g.drawString(text, (int) pos.getRX() - size[0] / 2 + 1,
					(int) pos.getRY() + size[1] / 4);
		} catch (Exception e) {
		}

	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public void setInterface(Interface E) {
		fatherInterface = E;
	}

}
