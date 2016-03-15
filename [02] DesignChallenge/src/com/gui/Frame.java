package com.gui;

import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class Frame extends JPanel {
	private static final long serialVersionUID = 1L;

	protected abstract void initComponents();	
	public abstract void open();
	public abstract void close();
	public abstract void display();
	public abstract void refresh();
	
	public Frame(int width, int height) {
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds((Application.MAX_WIDTH-width)/2, (Application.MAX_HEIGHT-height)/2, width, height);
		this.setBackground(Application.Color_TRANS);
		this.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		g.setColor( getBackground() );
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	protected static void initLabels(JLabel label, String iconPath, MouseListener listener) {
		label.addMouseListener(listener);
		label.setIcon(new ImageIcon("images/"+iconPath+".png"));
		
		label.setSize(label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
		label.setBorder(null);
		label.setFocusable(false);
		label.setBackground(Application.Color_TRANS);
	}
	protected static void initButtons(JButton button, String iconPath, MouseListener listener) {
		button.addMouseListener(listener);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon("images/"+iconPath+"_off.png"));
		button.setRolloverIcon(new ImageIcon("images/"+iconPath+"_on.png"));
		button.setSelectedIcon(button.getRolloverIcon());
		button.setDisabledIcon(button.getIcon());
		
		button.setSize(button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
		button.setBorder(null);
		button.setFocusable(false);
		button.setBackground(Application.Color_TRANS);
	}
	
}
