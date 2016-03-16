package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.controller.ProductivityToolController;
import com.entry.Entry;
import com.entry.Event;
import com.entry.Task;
import com.view.AgendaView;
import com.view.EntryType;

public class FrameAgenda extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private ProductivityToolController controller;
	private AgendaView viewAgenda;

	private int indexDay;
	private int indexMonth;
	private int indexYear;
	private boolean isSelectedEvent;
	private boolean isSelectedTask;
	
	private JLabel lblFrameMain;	
	private ArrayList<Entry> entries;
	private ArrayList<AgendaEntry> entryLabels;
	
	private JLabel lblEntriesBg;
	private JPanel pnlEntries;
	private JScrollPane scrlEntries;
	
	private int entryIndex;
	
	public FrameAgenda(int width, int height, AgendaView viewAgenda, ProductivityToolController controller) {
		super(width, height);
		this.controller = controller;
		
		Calendar cal = new GregorianCalendar();
		this.indexDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		this.indexMonth = cal.get(GregorianCalendar.MONTH);
		this.indexYear = cal.get(GregorianCalendar.YEAR);
		
		this.viewAgenda = viewAgenda;
		this.initComponents();
		
		this.add(scrlEntries);
		this.add(lblFrameMain);
	}
	
	@Override
	protected void initComponents() {	
		this.isSelectedEvent = true;
		this.isSelectedTask = true;
		this.entries = controller.getEntries(new Date());
		this.entryLabels = new ArrayList<AgendaEntry>();
		
		this.entries = controller.getEntries(new Date());
		
		this.lblFrameMain = new JLabel();
		this.lblEntriesBg = new JLabel();
		this.pnlEntries = new JPanel();
		this.scrlEntries = new JScrollPane();
		
		this.entryIndex = 1;
		
		Frame.initLabels(lblEntriesBg, "Frame Main AgendaView", null);
		Frame.initLabels(lblFrameMain, "Frame Main AgendaView", null);	
		lblFrameMain.setBounds(381, 259, lblFrameMain.getWidth(), lblFrameMain.getHeight());
		lblEntriesBg.setBounds(lblFrameMain.getX()+35, lblFrameMain.getY()+47, lblEntriesBg.getWidth(), lblEntriesBg.getHeight());
	
		pnlEntries.setLayout(new BoxLayout(pnlEntries, BoxLayout.Y_AXIS));
//		pnlEntries.setBounds(lblEntriesBg.getBounds());
		pnlEntries.setBounds(0, 0, 504, 318);
		pnlEntries.setBackground(Application.Color_TRANS);
		pnlEntries.setFont(Application.fntBebas25);
		pnlEntries.setBorder(null);
		pnlEntries.setFocusable(false);
		
//		this.scrlEntries = new JScrollPane(pnlEntries);
		this.scrlEntries = new JScrollPane(pnlEntries, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrlEntries.setBounds(lblEntriesBg.getX()+8, lblEntriesBg.getY(), 604-8, 318);
		scrlEntries.setBackground(pnlEntries.getBackground());
//		scrlEntries.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrlEntries.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrlEntries.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrlEntries.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrlEntries.setBorder(null);
		scrlEntries.getComponent(0).addMouseMotionListener(this);
		scrlEntries.getComponent(1).addMouseMotionListener(this);
		scrlEntries.getComponent(2).addMouseMotionListener(this);
		scrlEntries.getComponent(0).addMouseWheelListener(this);
		scrlEntries.getComponent(1).addMouseWheelListener(this);
		scrlEntries.getComponent(2).addMouseWheelListener(this);
		scrlEntries.setAlignmentX(LEFT_ALIGNMENT);
		
	}
	
	/*
	  This method toggles the visibility of different
	  labels in the gui.
	 */
	public void updatePanel() {
		EntryType type;
		System.out.println("COUNT: "+this.pnlEntries.getComponentCount());

		for(int i = 0; i < entryIndex; i++) {
			if(entries.get(i).getColor().equals(Color.BLUE)) {
				entryLabels.add(new AgendaEntry(pnlEntries.getWidth(), entries.get(i), entryIndex-1));
				this.pnlEntries.add(entryLabels.get(entryIndex-1));
				type = EntryType.EVENT;
			}
			else if(entries.get(i).getColor().equals(Color.GREEN)) {
				entryLabels.add(new AgendaEntry(pnlEntries.getWidth(), entries.get(i), entryIndex-1, new JCheckBox()));
				this.pnlEntries.add(entryLabels.get(entryIndex-1));
				type = EntryType.TASK;
			}
		}
		System.out.println("COUNT: "+this.pnlEntries.getComponentCount());
		this.pnlEntries.repaint();
//		EntryType type;
//		
//		if(entry instanceof Event) {
//			type = EntryType.EVENT;
//		}
//		else if(entry instanceof Task) {
//			type = EntryType.TASK;
//		}
//		else {
//			type = EntryType.NONE;
//		}
//		this.setViewType(type);	
	}
	
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
		this.refresh(this.indexMonth, this.indexDay, this.indexYear);
	}
	
	public void refresh(int month, int day, int year) {
		System.out.println("refresh agenda");
		Date date = new Date(year-1900, month, day);
		this.entries = this.controller.getEntries(date);

		this.indexDay = day;
		this.indexMonth = month;
		this.indexYear = year;
		
		if(isSelectedEvent && isSelectedTask) {
			this.entries = this.controller.getEntries(date);
			System.out.println("Both");
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
			System.out.println("NONE");
		}
		
		
		this.pnlEntries.removeAll();
		Entry temp = null;
		
		for(int i = 0; i < this.entries.size(); i++) {
			System.out.println("i: "+i);
			if(this.entries.get(i) != null) {
				System.out.println("add");				
				if(temp == null || 
						(this.entries.get(i).getDateDue() != temp.getDateDue())) {
					this.pnlEntries.add(this.createAgendaEntry(this.entries.get(i), i));
				}
				System.out.println("i = "+i+"    entry: "+entries.get(i));
			}
			temp = this.entries.get(i);
		}
		this.repaint();
		this.revalidate();
	}
	
	public JPanel createAgendaEntry(Entry entry, int index) {
		JPanel agendaEntry = new JPanel();
		agendaEntry.setLayout(new BoxLayout(agendaEntry, BoxLayout.X_AXIS));
		agendaEntry.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
//		agendaEntry.setLayout(new FlowLayout(FlowLayout.LEFT));
		agendaEntry.setSize(604, 20);
		agendaEntry.setBackground(Application.Color_TRANS);
		agendaEntry.setOpaque(true);
		
		String startTime = entry.getTimeRange().getStartTime().toString();
		String endTime = entry.getTimeRange().getEndTime().toString();
		
		JLabel lblStart = new JLabel(startTime);
		lblStart.setFont(Application.fntBebas18);
		lblStart.setForeground(Application.Color_NIGHT);
//		lblStart.setIcon(new ImageIcon("images/lblStartTimeBg.png"));
		lblStart.setMinimumSize(new Dimension(71, 20));
		lblStart.setPreferredSize(new Dimension(71, 20));
		lblStart.setMaximumSize(new Dimension(71, 20));
		lblStart.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		
		
		JLabel lblEnd = new JLabel(endTime);
		lblEnd.setFont(Application.fntBebas18);
		lblEnd.setForeground(Application.Color_NIGHT);
//		lblEnd.setIcon(new ImageIcon("images/lblEndTimeBg.png"));
		lblEnd.setMinimumSize(new Dimension(77, 20));
		lblEnd.setPreferredSize(new Dimension(77, 20));
		lblEnd.setMaximumSize(new Dimension(77, 20));
		lblEnd.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		
		agendaEntry.add(lblStart);
		agendaEntry.add(lblEnd);
		if(entry instanceof Task) {
			JCheckBox chkDescription = new JCheckBox(entry.getDescription());
		
			chkDescription.setMinimumSize(new Dimension(460, 20));
			chkDescription.setPreferredSize(new Dimension(460, 20));
			chkDescription.setMaximumSize(new Dimension(460, 20));
			
			chkDescription.setFont(Application.fntBebas18);
			chkDescription.setForeground(Application.Color_FLASH);
			chkDescription.setBorder(null);
			chkDescription.setBackground(Application.Color_TRANS);
			chkDescription.setSelected(((Task)entry).isCompleted());
			chkDescription.setIcon(new ImageIcon("images/chkIcon_off.png"));
			chkDescription.setDisabledIcon(new ImageIcon("images/chkIcon_off.png"));
			chkDescription.setSelectedIcon(new ImageIcon("images/chkIcon_on.png"));
			chkDescription.setFocusable(false);
			
			chkDescription.addActionListener(this);
			chkDescription.setAlignmentX(JCheckBox.LEFT_ALIGNMENT);
			agendaEntry.add(chkDescription);
			
			lblStart.setText("");
		}
		else if(entry instanceof Event){
			JLabel lblDescription = new JLabel(entry.getDescription());
//			lblDescription.setIcon(new ImageIcon("images/lblDescBg.png"));
//			lblDescription.setSize(460, 20);
			lblDescription.setMinimumSize(new Dimension(460, 20));
			lblDescription.setPreferredSize(new Dimension(460, 20));
			lblDescription.setMaximumSize(new Dimension(460, 20));
			lblDescription.setFont(Application.fntBebas18);
			lblDescription.setForeground(Application.Color_NIGHT);	
			lblDescription.setAlignmentX(JLabel.LEFT_ALIGNMENT);
			agendaEntry.add(lblDescription);
		}
		JLabel lblIndex = new JLabel(""+index);
		lblIndex.setVisible(false);
		agendaEntry.add(lblIndex);
		return agendaEntry;
	}
	
//	public void update(Entry entry) {
//		
//		this.entryLabels.add(new AgendaEntry(entry, this.entryIndex));
//		this.entryIndex++;
//	}
	
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
	public void markPoster(int index, JCheckBox source) {
		if(this.entries.size() > 0) {
			Date date = new Date(this.indexYear-1900, this.indexMonth, this.indexDay);
			Entry entry = entries.get(index);		
			System.out.println("entry in markposter: "+entry);
			
			if(entries.get(index) instanceof Task) {
				System.out.println("task");
				int indexEntry = this.controller.getEntries(date).indexOf(entry);
				this.controller.getEntries(date).get(indexEntry).setCompleted(!this.controller.getEntries(date).get(indexEntry).isCompleted());
			}
		}
		this.refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JCheckBox) {
			System.out.println("chk");
			
			JPanel parent = (JPanel)(((JCheckBox)e.getSource()).getParent());
			int index = ((JCheckBox)e.getSource()).getParent().getComponentCount()-1;
			int lastComponent = Integer.parseInt(((JLabel)parent.getComponent(index)).getText());
			
			this.markPoster(lastComponent, (JCheckBox)e.getSource());
		}
		this.repaint();
	}
}
