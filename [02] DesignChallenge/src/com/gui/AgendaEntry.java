package com.gui;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.entry.Entry;
import com.view.EntryType;

public class AgendaEntry extends JPanel{
	
	private String startTime;
	private String endTime;
	private JCheckBox cbStatus;
	private JLabel agendaTime;
	private JLabel textDescription;
	private int y;
	
	public AgendaEntry(int width, Entry entry, int entryIndex, JCheckBox status){
		
		this.setLayout(null);
		this.setBounds(0, 0, width, 60);
		
		this.setBackground(Application.Color_TRANS);
		this.setOpaque(false);
		
		this.initComponents(entry);
		this.startTime = entry.getTimeRange().getStartTime().toString();
		this.endTime = entry.getTimeRange().getEndTime().toString();
		this.cbStatus = status;
		this.y = 0;
		
		
		this.add(agendaTime);
		this.add(textDescription);
		this.setVisible(true);
	}
	
	public AgendaEntry(int width, Entry entry, int entryIndex){
		
		this.setLayout(null);
		this.setBounds(0, 60*entryIndex, width, 60);
		this.setVisible(true);
				
		this.startTime = entry.getTimeRange().getStartTime().toString();
		this.endTime = entry.getTimeRange().getEndTime().toString();
		this.y = 0;
		
		this.initComponents(entry);
		this.add(this.agendaTime);
		this.add(this.textDescription);
	}
	
	public void initComponents(Entry entry){
		System.out.println("start time: "+this.startTime);
		this.agendaTime = new JLabel(this.startTime);
		this.agendaTime.setBounds(0, 0, this.getWidth()/6, this.getHeight());
		
		
		System.out.println("entry description: "+entry.getDescription());
		this.textDescription = new JLabel(entry.getDescription());
		this.textDescription.setBounds(this.agendaTime.getWidth(), 0, this.getWidth()- this.agendaTime.getWidth(), this.getHeight());
		
		
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public JCheckBox getEntry() {
		return cbStatus;
	}

	public void setEntry(JCheckBox entry) {
		this.cbStatus = entry;
	}
	
}
