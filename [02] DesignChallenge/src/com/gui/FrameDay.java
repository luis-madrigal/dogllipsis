package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.controller.ProductivityToolController;
import com.entry.Entry;
import com.entry.Event;
import com.entry.Task;
import com.view.DayView;
import com.view.EntryType;
import com.view.EntryView;

public class FrameDay extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	
	private ProductivityToolController controller;
	private DayView viewDay;
	private ArrayList<Entry> entries;
	private ArrayList<String> time;
	private int indexEntry;
	
	private JLabel lblFrameMain;	
	private JLabel lblFrameSide;
	
	private JLabel lblPosterMainDone;
	private JLabel lblPosterSideLeftDone;
	private JLabel lblPosterSideRightDone;
	
	private JLabel lblPosterSideLeft;
	private JLabel lblPosterSideRight;
	private JLabel lblPosterMain;
	private JButton btnRemove;
	
	// 3 labels of time slots 00:00
	private JLabel lblLeftTime;
	private JLabel lblMainTime;
	private JLabel lblRightTime;
	
	// Main poster labels
	private JLabel lblTimeDue;
	private JLabel lblEntryNum;
	private JTextArea txtDescription;
	private JScrollPane scrlDescription;
	
	
	private ImageIcon iiDoneHead;
	private ImageIcon iiDoneTail;
	
	private ImageIcon iiMainNone;
	private ImageIcon iiSideNone;
	private ImageIcon iiMainEvent;
	private ImageIcon iiMainEventTail;
	private ImageIcon iiSideEvent;
	private ImageIcon iiMainTask;
	private ImageIcon iiMainTaskTail;
	private ImageIcon iiSideTask;

	
	public FrameDay(int width, int height, DayView viewDay, ProductivityToolController controller) {
		super(width, height);
		this.controller = controller;
		
		this.viewDay = viewDay;
		this.initComponents();
		this.setViewType(EntryType.NONE);
		// frame
		this.add(lblFrameMain);
		this.add(lblFrameSide);
		
		this.add(btnRemove);
		// done highlight
		this.add(lblPosterMainDone);
		this.add(lblPosterSideLeftDone);
		this.add(lblPosterSideRightDone);
		
		// poster details
		this.add(lblMainTime);
		this.add(lblTimeDue);
		this.add(lblEntryNum);
		this.add(scrlDescription);
		
		this.add(lblLeftTime);
		this.add(lblRightTime);
		
		// poster
		this.add(lblPosterMain);
		this.add(lblPosterSideLeft);
		this.add(lblPosterSideRight);
		
		this.setTime();
	}
	
	@Override
	protected void initComponents() {		
		this.time = new ArrayList<String>();
		time.add("00:00");
		time.add("00:30");
		time.add("01:00");
		time.add("01:30");
		time.add("02:00");
		time.add("02:30");
		time.add("03:00");
		time.add("03:30");
		time.add("04:00");
		time.add("04:30");
		time.add("05:00");
		time.add("05:30");
		
		time.add("06:00");
		time.add("06:30");
		time.add("07:00");
		time.add("07:30");
		time.add("08:00");
		time.add("08:30");
		time.add("09:00");
		time.add("09:30");
		time.add("10:00");
		time.add("10:30");
		time.add("11:00");
		time.add("11:30");
		
		time.add("12:00");
		time.add("12:30");
		time.add("13:00");
		time.add("13:30");
		time.add("14:00");
		time.add("14:30");
		time.add("15:00");
		time.add("15:30");
		time.add("16:00");
		time.add("16:30");
		time.add("17:00");
		time.add("17:30");
		
		time.add("18:00");
		time.add("18:30");		
		time.add("19:00");
		time.add("19:30");
		time.add("20:00");
		time.add("20:30");
		time.add("21:00");
		time.add("21:30");
		time.add("22:00");
		time.add("22:30");
		time.add("23:00");
		time.add("23:30");
		time.add("24:00");

		
		this.entries = new ArrayList<Entry>();
		System.out.println("Date: "+new Date());
		this.entries = controller.getEntries(new Date());
		this.indexEntry = 0;
		
		this.iiDoneHead = new ImageIcon("images/Poster Done.png");
		this.iiDoneTail = new ImageIcon("images/Poster Done Tail.png");
		
		this.iiMainNone = new ImageIcon("images/Poster None.png");
		this.iiSideNone = new ImageIcon("images/Preview None.png");
		
		this.iiMainEvent = new ImageIcon("images/Poster Event.png");
		this.iiMainEventTail = new ImageIcon("images/Poster Event Tail.png");
		this.iiSideEvent = new ImageIcon("images/Preview Event.png");
		
		this.iiMainTask = new ImageIcon("images/Poster Task.png");
		this.iiMainTaskTail = new ImageIcon("images/Poster Task Tail.png");
		this.iiSideTask = new ImageIcon("images/Preview Task.png");
		
		this.lblFrameMain = new JLabel();
		this.lblFrameSide = new JLabel();
		
		this.lblPosterMainDone = new JLabel();
		lblPosterMainDone.setVisible(false);
		this.lblPosterSideLeftDone = new JLabel();
		lblPosterSideLeftDone.setVisible(false);
		this.lblPosterSideRightDone = new JLabel();
		lblPosterSideRightDone.setVisible(false);
		
		this.lblPosterMain = new JLabel();
		this.lblPosterSideLeft = new JLabel();
		this.lblPosterSideRight = new JLabel();
		
		Frame.initLabels(lblFrameMain, "Frame Main DayView", null);		
		Frame.initLabels(lblFrameSide, "Frame Side DayView", null);
		
		Frame.initLabels(lblPosterMainDone, "Poster Done", this);
		Frame.initLabels(lblPosterSideLeftDone, "Frame Side Done", null);
		Frame.initLabels(lblPosterSideRightDone, "Frame Side Done", null);
		
		Frame.initLabels(lblPosterMain, "Poster None", this);
		Frame.initLabels(lblPosterSideLeft, "Preview None", this);
		Frame.initLabels(lblPosterSideRight, "Preview None", this);
		
		this.lblMainTime = new JLabel("00:00");
		lblMainTime.setBounds(lblPosterMain.getX()+671 , +lblPosterMain.getY()+50, 138, 548);
		lblMainTime.setFont(Application.fntBebas45);
		lblMainTime.setForeground(Application.Color_NIGHT);
		
		this.lblTimeDue = new JLabel("00:00");
		lblTimeDue.setBounds(lblPosterMain.getX()+724 , +lblPosterMain.getY()+359, 138, 29);
		lblTimeDue.setFont(Application.fntBebas27);
		lblTimeDue.setForeground(Application.Color_NIGHT);
		
		this.lblEntryNum = new JLabel("00");
		lblEntryNum.setBounds(lblPosterMain.getX()+740 , +lblPosterMain.getY()+373, 138, 548);
		lblEntryNum.setFont(Application.fntBebas27);
		lblEntryNum.setForeground(Application.Color_NIGHT);
		
		this.lblLeftTime = new JLabel("00:00");
		lblLeftTime.setBounds(lblPosterMain.getX()+442 , +lblPosterMain.getY()+70, 138, 548);
		lblLeftTime.setFont(Application.fntBebas29);
		lblLeftTime.setForeground(Application.Color_NIGHT);
		
		this.lblRightTime = new JLabel("00:00");
		lblRightTime.setBounds(lblPosterMain.getX()+929 , +lblPosterMain.getY()+70, 138, 548);
		lblRightTime.setFont(Application.fntBebas29);
		lblRightTime.setForeground(Application.Color_NIGHT);
		
		lblFrameMain.setBounds(512, 230, lblFrameMain.getWidth(), lblFrameMain.getHeight());
		lblFrameSide.setBounds(381, 278, lblFrameSide.getWidth(), lblFrameSide.getHeight());
		
		lblPosterMain.setBounds(586, 266, lblPosterMain.getWidth(), lblPosterMain.getHeight());
		lblPosterSideLeft.setBounds(414, 305, lblPosterSideLeft.getWidth(), lblPosterSideLeft.getHeight());
		lblPosterSideRight.setBounds(902, 305, lblPosterSideRight.getWidth(), lblPosterSideRight.getHeight());
	
		lblPosterMainDone.setBounds(lblPosterMain.getBounds());
		lblPosterSideLeftDone.setBounds(lblPosterSideLeft.getBounds());
		lblPosterSideRightDone.setBounds(lblPosterSideRight.getBounds());
		
		this.txtDescription = new JTextArea("asdnajsndasndkjabskdbaksjdbkasjbdkjbnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		txtDescription.setBounds(624, 411, 185, 185);
		txtDescription.setBackground(Application.Color_TRANS);
		txtDescription.setForeground(Application.Color_NIGHT);
		txtDescription.setFont(Application.fntBebas25);
		txtDescription.setLineWrap(true);
		
		this.scrlDescription = new JScrollPane(txtDescription);
		scrlDescription.setBounds(txtDescription.getBounds());
		scrlDescription.setBackground(txtDescription.getBackground());
		scrlDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrlDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrlDescription.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrlDescription.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrlDescription.setBorder(null);
		scrlDescription.getComponent(0).addMouseMotionListener(this);
		scrlDescription.getComponent(1).addMouseMotionListener(this);
		scrlDescription.getComponent(2).addMouseMotionListener(this);
		scrlDescription.getComponent(0).addMouseWheelListener(this);
		scrlDescription.getComponent(1).addMouseWheelListener(this);
		scrlDescription.getComponent(2).addMouseWheelListener(this);
		
		btnRemove = new JButton();
		Frame.initButtons(btnRemove, "btnRemove", this);
		btnRemove.setSelectedIcon(btnRemove.getRolloverIcon());
		btnRemove.setRolloverSelectedIcon(btnRemove.getRolloverIcon());
		btnRemove.setBounds(lblPosterMain.getX()+lblPosterMain.getWidth()-btnRemove.getWidth(), lblPosterMain.getY(), btnRemove.getWidth(), btnRemove.getHeight());
	}
	
	public void setTime() {
		this.lblLeftTime.setVisible(true);
		this.lblRightTime.setVisible(true);
		
		if(this.indexEntry == 0) {
			this.lblLeftTime.setVisible(false);
		}
		if(this.indexEntry == 47) {
			this.lblRightTime.setVisible(false);
		}
		
		this.lblMainTime.setText(this.time.get(indexEntry));
		if((this.indexEntry-1) >= 0) {
			this.lblLeftTime.setText(""+this.time.get((this.indexEntry-1)));
		}
		if((this.indexEntry+1) < 48) {
			this.lblRightTime.setText(""+this.time.get((this.indexEntry+1)));
		}
			
	}
	
	public void setPoster(Entry entry) {
		EntryType type;
		if(entry instanceof Event) {
			type = EntryType.EVENT;
		}
		else if(entry instanceof Task) {
			type = EntryType.TASK;
		}
		else {
			type = EntryType.NONE;
		}
		this.setViewType(type);	
	}
	
	public void setPosterSide(Entry entryLeft, Entry entryRight) {
		EntryType type;
		if(entryLeft instanceof Event) {
			type = EntryType.EVENT;
		}
		else if(entryLeft instanceof Task) {
			type = EntryType.TASK;
		}
		else {
			type = EntryType.NONE;
		}
		this.setLeftSideViewType(type);	
		
		if(entryRight instanceof Event) {
			type = EntryType.EVENT;
		}
		else if(entryRight instanceof Task) {
			type = EntryType.TASK;
		}
		else {
			type = EntryType.NONE;
		}
		this.setRightSideViewType(type);			
	}
	
	public void setRightSideViewType(EntryType type) {
		switch(type) {
			case TASK:				
				this.lblPosterSideLeft.setIcon(iiSideTask);
				break;
			case EVENT:
				this.lblPosterSideLeft.setIcon(iiSideEvent);
				break;
			case NONE:
				this.lblPosterSideLeft.setIcon(iiSideNone);
				break;
		}
	}
	
	public void setLeftSideViewType(EntryType type) {
		switch(type) {
			case TASK:				
				this.lblPosterSideRight.setIcon(iiSideTask);
				break;
			case EVENT:
				this.lblPosterSideRight.setIcon(iiSideEvent);
				break;
			case NONE:
				this.lblPosterSideRight.setIcon(iiSideNone);
				break;
		}
	}
	
	/*
	  This method toggles the visibility of different
	  labels in the gui.
	*/
	public void setViewType(EntryType type) {
		switch(type) {
			case TASK:
				this.lblTimeDue.setVisible(true);
				this.lblEntryNum.setVisible(true);
				this.scrlDescription.setVisible(true);
				
				this.lblPosterMain.setIcon(iiMainTask);
				break;
			case EVENT:
				this.lblTimeDue.setVisible(true);
				this.lblEntryNum.setVisible(true);
				this.scrlDescription.setVisible(true);
				
				this.lblPosterMain.setIcon(iiMainEvent);
				break;
			case NONE:
				this.lblTimeDue.setVisible(false);
				this.lblEntryNum.setVisible(false);
				this.scrlDescription.setVisible(false);
				
				this.lblPosterMain.setIcon(iiMainNone);
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
		
	}
	@Override
	public void refresh() {
		this.entries = this.controller.getEntries(new Date());
		
	}
	
	public void next() {
		this.setIndexEntry(this.indexEntry+1);
	}
	
	public void prev() {
		this.setIndexEntry(this.indexEntry-1);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.lblPosterSideLeft) {
			this.prev();
		}
		if(e.getSource() == this.lblPosterSideRight) {
			this.next();
		}
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
		if(e.getSource() == this.scrlDescription.getComponent(0)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlDescription.getComponent(1)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlDescription.getComponent(2)) {
			this.repaint();
			this.revalidate();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getSource() == this.scrlDescription.getComponent(0)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlDescription.getComponent(1)) {
			this.repaint();
			this.revalidate();
		}
		if(e.getSource() == this.scrlDescription.getComponent(2)) {
			this.repaint();
			this.revalidate();
		}
	}

	public DayView getViewDay() {
		return viewDay;
	}

	public void setViewDay(DayView viewDay) {
		this.viewDay = viewDay;
	}

	public ArrayList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}

	public int getIndexEntry() {
		return indexEntry;
	}

	public void setIndexEntry(int indexEntry) {
		if(indexEntry < 0) {
			indexEntry = this.entries.size()-1;
		}
		else if(indexEntry >= this.entries.size()) {
			indexEntry = 0;
		}
		this.indexEntry = indexEntry;
		this.setTime();
	}	
}
