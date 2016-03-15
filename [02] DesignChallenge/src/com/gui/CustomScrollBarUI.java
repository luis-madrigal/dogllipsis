package com.gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class CustomScrollBarUI extends BasicScrollBarUI {
	@Override 
	public JButton createDecreaseButton(int orientation) {
		JButton btnDecrease = new JButton();
		btnDecrease.setBorder(null);
		btnDecrease.setContentAreaFilled(false);
	
	    return btnDecrease;
	}
	
	@Override    
	public JButton createIncreaseButton(int orientation) {
		JButton btnIncrease = new JButton();
		btnIncrease.setBorder(null);
		btnIncrease.setContentAreaFilled(false);
		
		return btnIncrease;
	}
	@Override 
	public void configureScrollBarColors() {
		//this.thumbColor = new Color(250, 4, 87, 150);
		this.thumbColor = new Color(32, 227, 255, 150);
		this.trackColor = new Color(50, 50, 50);

	}
}
