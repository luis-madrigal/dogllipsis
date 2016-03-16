package com.gui;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class AgendaEntry extends JPanel{
	
	private String startTime;
	private String endTime;
	private JCheckBox cbStatus;
	private int y;
	
	public AgendaEntry(Entry entry, int entryIndex, JCheckBox status){
		
		this.startTime = entry.getTimeRange().getStartTime().toString();
		this.endTime = entry.getTimeRange().getEndTime().toString();
		this.cbStatus = status;
		this.y = 0;
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
