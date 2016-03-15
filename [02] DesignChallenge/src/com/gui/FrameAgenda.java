package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.controller.ProductivityToolController;
import com.view.AgendaView;
import com.view.EntryType;

public class FrameAgenda extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	
	private ProductivityToolController controller;
	private AgendaView viewAgenda;
	
	private JLabel lblFrameMain;	
	private ArrayList<JCheckBox> entries;
	private JLabel lblEntriesBg;
	private JPanel pnlEntries;
	private JScrollPane scrlEntries;

	public FrameAgenda(int width, int height, AgendaView viewAgenda, ProductivityToolController controller) {
		super(width, height);
		this.controller = controller;
		
		this.viewAgenda = viewAgenda;
		this.initComponents();
	
		this.add(lblFrameMain);
		this.add(scrlEntries);
	}
	
	@Override
	protected void initComponents() {		
		this.entries = new ArrayList<JCheckBox>();
		
		this.lblFrameMain = new JLabel();
		this.lblEntriesBg = new JLabel();
		
		this.pnlEntries = new JPanel();
		this.scrlEntries = new JScrollPane();
		
		Frame.initLabels(lblFrameMain, "Frame Main AgendaView", null);			
		Frame.initLabels(lblEntriesBg, "Frame Main AgendaViewBg", null);
		
		lblFrameMain.setBounds(381, 259, lblFrameMain.getWidth(), lblFrameMain.getHeight());
		lblEntriesBg.setBounds(lblFrameMain.getX()+35, lblFrameMain.getY()+47, lblEntriesBg.getWidth(), lblEntriesBg.getHeight());
		pnlEntries.setBounds(lblEntriesBg.getBounds());
		pnlEntries.setBackground(Application.Color_TRANS);
		pnlEntries.setLayout(null);
		pnlEntries.setOpaque(false);
		
		this.pnlEntries = new JPanel();
		pnlEntries.setBounds(lblEntriesBg.getBounds());
		pnlEntries.setBackground(Application.Color_TRANS);
		pnlEntries.setFont(Application.fntBebas25);
		
		
		this.scrlEntries = new JScrollPane(pnlEntries);
		scrlEntries.setBounds(pnlEntries.getBounds());
		scrlEntries.setBackground(pnlEntries.getBackground());
		scrlEntries.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrlEntries.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrlEntries.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrlEntries.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrlEntries.setBorder(null);
		scrlEntries.getComponent(0).addMouseMotionListener(this);
		scrlEntries.getComponent(1).addMouseMotionListener(this);
		scrlEntries.getComponent(2).addMouseMotionListener(this);
		scrlEntries.getComponent(0).addMouseWheelListener(this);
		scrlEntries.getComponent(1).addMouseWheelListener(this);
		scrlEntries.getComponent(2).addMouseWheelListener(this);
	}
	
	/*
	  This method toggles the visibility of different
	  labels in the gui.
	 */
	public void setViewType(EntryType type) {
		switch(type) {
			case TASK:
				break;
			case EVENT:
				break;
			case NONE:
				break;
		}
	}
	
	@Override
	public void open() {
		this.setVisible(true);
	}
	@Override
	public void close() {
		this.setVisible(false);
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getSource() == this.scrlEntries.getComponent(0)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlEntries.getComponent(1)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlEntries.getComponent(2)) {
			this.repaint();
			this.revalidate();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getSource() == this.scrlEntries.getComponent(0)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlEntries.getComponent(1)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlEntries.getComponent(2)) {
			this.repaint();
			this.revalidate();
		}
	}	
}
