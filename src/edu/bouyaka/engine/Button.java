package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button extends Concrete {

	Graphics g;
	Font f;
	private int rW;
	private int rH;
	private Color textColor;
	private Color buttonColor;
	private String text;
	private int textSize;



	public void setSize(int rW, int rH) {
		this.rW = rW;
		this.rH = rH;
		engine.interfaceEdited = true;
	}

	public void setColor(Color buttonColor) {
		this.buttonColor = buttonColor;
		engine.interfaceEdited = true;

	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
		engine.interfaceEdited = true;

	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		f = new Font("Courier New", Font.BOLD, textSize);
		engine.interfaceEdited = true;

	}

	public void addText(String text) {
		this.text = this.text + text;
		engine.interfaceEdited = true;
		System.out.println(this.text);
	}

	public void replaceText(String text) {
		this.text = String.valueOf(text);
		engine.interfaceEdited = true;
	}

	public void show() {

		g.setColor(buttonColor);
		g.fillRect((int)objectPos[0],(int) objectPos[1], rW, rH);
		g.setColor(textColor);
		g.setFont(f);
		try {
			g.drawString(text, (int)objectPos[0] + 1,(int) (objectPos[1] + (rH + textSize) / 2 - 1));
		} catch (Exception e) {
		}
	}

	public void setG(Graphics g) {
		this.g = g;
	}

}
