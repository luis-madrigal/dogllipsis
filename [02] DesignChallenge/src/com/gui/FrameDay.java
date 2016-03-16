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

public class FrameDay extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	
	
	
	private ProductivityToolController controller;
	private DayView viewDay;
	private ArrayList<Entry> entries;
	private ArrayList<String> time;
	private int indexEntry;
	private int indexDay;
	private int indexMonth;
	private int indexYear;
	private boolean isSelectedEvent;
	private boolean isSelectedTask;
	
	private JLabel lblFrameMain;	
	private JLabel lblFrameSide;
	
	private JLabel lblPosterMainDone;
	private JLabel lblPosterSideLeftDone;
	private JLabel lblPosterSideRightDone;
	
	private JLabel lblPosterSideLeft;
	private JLabel lblPosterSideRight;
	private JLabel lblPosterMain;
	private JButton btnRemove;
	
	private JLabel lblLeftTime;
	private JLabel lblMainTime;
	private JLabel lblRightTime;
	
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
	
	public FrameDay(int width, int height, ProductivityToolController controller) {
		super(width, height);
		this.controller = controller;		
		this.viewDay = new DayView(controller);
		
		this.initComponents();
		
		this.add(lblFrameMain);
		this.add(lblFrameSide);		
		this.add(btnRemove);
		this.add(lblPosterMainDone);
		this.add(lblPosterSideLeftDone);
		this.add(lblPosterSideRightDone);		
		this.add(lblMainTime);
		this.add(lblTimeDue);
		this.add(lblEntryNum);
		this.add(scrlDescription);
		this.add(lblLeftTime);
		this.add(lblRightTime);
		this.add(lblPosterMain);
		this.add(lblPosterSideLeft);
		this.add(lblPosterSideRight);
		
		this.setViewType(EntryType.NONE);
		this.setTime();
	}

	@Override
	protected void initComponents() {		
		this.indexYear = 2016;
		this.indexDay = 0;
		this.indexMonth = 1;
		this.isSelectedEvent = true;
		this.isSelectedTask = true;
		
		this.time = new ArrayList<String>();
		this.initTimeLabels();
		
		
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
		
		Frame.initLabels(lblPosterMainDone, "Poster Done", null);
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
		
		this.txtDescription = new JTextArea();
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
		if(indexEntry < 48)
			this.lblMainTime.setText(this.time.get(indexEntry));
		if((this.indexEntry-1) >= 0) {
			this.lblLeftTime.setText(""+this.time.get((this.indexEntry-1)));			
		}
			
		if((this.indexEntry+1) < 48) {
			this.lblRightTime.setText(""+this.time.get((this.indexEntry+1)));
			
		}
		this.refresh();
	}
	
	public void setPoster() {
		if(this.entries.size() > 0) {
			Entry entry = this.entries.get(indexEntry);
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
			
			if(entry != null) {
				this.lblTimeDue.setText(entry.getTimeRange().getEndTime().toString().substring(0, 5));
				this.txtDescription.setText(entry.getDescription());
			}
			
			this.setPosterSide();
			this.setViewType(type);	
		}
	}
	
	public void setPosterSide() {
		if(this.entries.size() > 0) {
			EntryType type = EntryType.NONE;
			
			Entry entryLeft;
			if(indexEntry-1 < 0) {
				entryLeft = null;
			}
			else {
				entryLeft = entries.get(indexEntry-1);
			}
			
			Entry entryRight;
			if(indexEntry+1 > 47) {
				entryRight = null;
			}
			else {
				entryRight = entries.get(indexEntry+1);
			}
			
			this.lblPosterSideLeftDone.setVisible(false);
			if(entryLeft == null) {
				type = EntryType.NONE;
			}
			else if(entryLeft instanceof Event) {
				type = EntryType.EVENT;
			}
			else if(entryLeft instanceof Task) {
				type = EntryType.TASK;
				if(entryLeft.isCompleted()) {
					this.lblPosterSideLeftDone.setVisible(true);
				}
			}
			
			this.setLeftSideViewType(type);	
			this.lblPosterSideRightDone.setVisible(false);
			if(entryRight == null) {
				type = EntryType.NONE;			
			}
			else if(entryRight instanceof Event) {
				type = EntryType.EVENT;
			}
			else if(entryRight instanceof Task) {
				type = EntryType.TASK;
				if(entryRight.isCompleted()) {
					this.lblPosterSideRightDone.setVisible(true);
				}
			}
	
			this.setRightSideViewType(type);	
		}
	}
	
	public void setRightSideViewType(EntryType type) {
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
	
	public void setLeftSideViewType(EntryType type) {
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
	
	/*
	  This method toggles the visibility of different
	  labels in the gui.
	*/
	public void setViewType(EntryType type) {
		this.lblPosterMainDone.setVisible(false);
		switch(type) {
			case TASK:
				this.lblTimeDue.setVisible(true);
				this.lblEntryNum.setVisible(true);
				this.scrlDescription.setVisible(true);
				
				this.lblPosterMain.setIcon(iiMainTask);
				Entry entry = this.entries.get(indexEntry);
				if(entry.isCompleted()) {
					this.lblPosterMainDone.setVisible(true);
				}
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
		this.refresh(this.indexMonth, this.indexDay, this.indexYear);		
	}
	
	@SuppressWarnings("deprecation")
	public void refresh(int month, int day, int year) {
		System.out.println("month: "+month+"   day: "+day+"   year"+year);
		this.indexDay = day;
		this.indexMonth = month;
		this.indexYear = year;
		
		Date date = new Date(year-1900, month, day);
		
		if(isSelectedEvent && isSelectedTask) {
			this.entries = this.controller.getEntries(date);
			System.out.println("Bot");
		}
		else if(isSelectedEvent) {
			this.entries = this.controller.getEntries(date, EntryType.EVENT);
			System.out.println("Event");
		}
		else if(isSelectedTask) {
			this.entries = this.controller.getEntries(date, EntryType.TASK);
			System.out.println("Task");
		}
		else {
			this.entries = this.controller.getEntries(date, EntryType.NONE);
		}
		
		for(int i = 0; i < this.entries.size(); i++) {
			if(this.entries.get(i) != null)
			System.out.println("i = "+i+"    entry: "+entries.get(i));
		}
		
		this.setPoster();	
	}
	
	public void next() {
		this.setIndexEntry(this.indexEntry+1);
	}
	
	public void prev() {
		this.setIndexEntry(this.indexEntry-1);
	}
	
	public void remove() {
		this.controller.removeEntries(entries.get(indexEntry));
		this.refresh();
	}
	
	@SuppressWarnings("deprecation")
	public void markPoster() {
		if(this.entries.size() > 0) {
			Date date = new Date(this.indexYear-1900, this.indexMonth, this.indexDay);
			Entry entry = entries.get(indexEntry);		
			
			if(entries.get(indexEntry) instanceof Task) {
				System.out.println("task");
				int indexStart = Application.getTimeIndex(entry.getTimeRange().getStartTime());
				int indexEnd = Application.getTimeIndex(entry.getTimeRange().getEndTime());
				for(int i = indexStart; i < indexEnd; i++) {
					this.controller.getEntries(date).get(i).setCompleted(!this.controller.getEntries(date).get(i).isCompleted());
					System.out.println("Completed: "+this.controller.getEntries(date).get(i).isCompleted());
				}
			}
			this.refresh();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.lblPosterMain) {
			this.markPoster();
			System.out.println("Poster Main");
		}
		if(e.getSource() == this.lblPosterSideLeft) {
			this.prev();
		}
		if(e.getSource() == this.lblPosterSideRight) {
			this.next();
		}
		if(e.getSource() == this.btnRemove) {
			this.remove();
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

	public boolean isSelectedEvent() {
		return isSelectedEvent;
	}

	public void setSelectedEvent(boolean isSelectedEvent) {
		this.isSelectedEvent = isSelectedEvent;
		this.refresh();
	}

	public boolean isSelectedTask() {
		return isSelectedTask;
	}

	public void setSelectedTask(boolean isSelectedTask) {
		this.isSelectedTask = isSelectedTask;
		this.refresh();
	}

	public ProductivityToolController getController() {
		return controller;
	}

	public void setController(ProductivityToolController controller) {
		this.controller = controller;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}

	public int getIndexDay() {
		return indexDay;
	}

	public void setIndexDay(int indexDay) {
		this.indexDay = indexDay;
	}

	public int getIndexMonth() {
		return indexMonth;
	}

	public void setIndexMonth(int indexMonth) {
		this.indexMonth = indexMonth;
	}

	public int getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(int indexYear) {
		this.indexYear = indexYear;
	}

	public JLabel getLblFrameMain() {
		return lblFrameMain;
	}

	public void setLblFrameMain(JLabel lblFrameMain) {
		this.lblFrameMain = lblFrameMain;
	}

	public JLabel getLblFrameSide() {
		return lblFrameSide;
	}

	public void setLblFrameSide(JLabel lblFrameSide) {
		this.lblFrameSide = lblFrameSide;
	}

	public JLabel getLblPosterMainDone() {
		return lblPosterMainDone;
	}

	public void setLblPosterMainDone(JLabel lblPosterMainDone) {
		this.lblPosterMainDone = lblPosterMainDone;
	}

	public JLabel getLblPosterSideLeftDone() {
		return lblPosterSideLeftDone;
	}

	public void setLblPosterSideLeftDone(JLabel lblPosterSideLeftDone) {
		this.lblPosterSideLeftDone = lblPosterSideLeftDone;
	}

	public JLabel getLblPosterSideRightDone() {
		return lblPosterSideRightDone;
	}

	public void setLblPosterSideRightDone(JLabel lblPosterSideRightDone) {
		this.lblPosterSideRightDone = lblPosterSideRightDone;
	}

	public JLabel getLblPosterSideLeft() {
		return lblPosterSideLeft;
	}

	public void setLblPosterSideLeft(JLabel lblPosterSideLeft) {
		this.lblPosterSideLeft = lblPosterSideLeft;
	}

	public JLabel getLblPosterSideRight() {
		return lblPosterSideRight;
	}

	public void setLblPosterSideRight(JLabel lblPosterSideRight) {
		this.lblPosterSideRight = lblPosterSideRight;
	}

	public JLabel getLblPosterMain() {
		return lblPosterMain;
	}

	public void setLblPosterMain(JLabel lblPosterMain) {
		this.lblPosterMain = lblPosterMain;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public JLabel getLblLeftTime() {
		return lblLeftTime;
	}

	public void setLblLeftTime(JLabel lblLeftTime) {
		this.lblLeftTime = lblLeftTime;
	}

	public JLabel getLblMainTime() {
		return lblMainTime;
	}

	public void setLblMainTime(JLabel lblMainTime) {
		this.lblMainTime = lblMainTime;
	}

	public JLabel getLblRightTime() {
		return lblRightTime;
	}

	public void setLblRightTime(JLabel lblRightTime) {
		this.lblRightTime = lblRightTime;
	}

	public JLabel getLblTimeDue() {
		return lblTimeDue;
	}

	public void setLblTimeDue(JLabel lblTimeDue) {
		this.lblTimeDue = lblTimeDue;
	}

	public JLabel getLblEntryNum() {
		return lblEntryNum;
	}

	public void setLblEntryNum(JLabel lblEntryNum) {
		this.lblEntryNum = lblEntryNum;
	}

	public JTextArea getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextArea txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JScrollPane getScrlDescription() {
		return scrlDescription;
	}

	public void setScrlDescription(JScrollPane scrlDescription) {
		this.scrlDescription = scrlDescription;
	}

	public ImageIcon getIiDoneHead() {
		return iiDoneHead;
	}

	public void setIiDoneHead(ImageIcon iiDoneHead) {
		this.iiDoneHead = iiDoneHead;
	}

	public ImageIcon getIiDoneTail() {
		return iiDoneTail;
	}

	public void setIiDoneTail(ImageIcon iiDoneTail) {
		this.iiDoneTail = iiDoneTail;
	}

	public ImageIcon getIiMainNone() {
		return iiMainNone;
	}

	public void setIiMainNone(ImageIcon iiMainNone) {
		this.iiMainNone = iiMainNone;
	}

	public ImageIcon getIiSideNone() {
		return iiSideNone;
	}

	public void setIiSideNone(ImageIcon iiSideNone) {
		this.iiSideNone = iiSideNone;
	}

	public ImageIcon getIiMainEvent() {
		return iiMainEvent;
	}

	public void setIiMainEvent(ImageIcon iiMainEvent) {
		this.iiMainEvent = iiMainEvent;
	}

	public ImageIcon getIiMainEventTail() {
		return iiMainEventTail;
	}

	public void setIiMainEventTail(ImageIcon iiMainEventTail) {
		this.iiMainEventTail = iiMainEventTail;
	}

	public ImageIcon getIiSideEvent() {
		return iiSideEvent;
	}

	public void setIiSideEvent(ImageIcon iiSideEvent) {
		this.iiSideEvent = iiSideEvent;
	}

	public ImageIcon getIiMainTask() {
		return iiMainTask;
	}

	public void setIiMainTask(ImageIcon iiMainTask) {
		this.iiMainTask = iiMainTask;
	}

	public ImageIcon getIiMainTaskTail() {
		return iiMainTaskTail;
	}

	public void setIiMainTaskTail(ImageIcon iiMainTaskTail) {
		this.iiMainTaskTail = iiMainTaskTail;
	}

	public ImageIcon getIiSideTask() {
		return iiSideTask;
	}

	public void setIiSideTask(ImageIcon iiSideTask) {
		this.iiSideTask = iiSideTask;
	}
	
	private void initTimeLabels() {
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
	}
}
