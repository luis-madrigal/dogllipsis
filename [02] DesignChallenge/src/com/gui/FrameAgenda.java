package com.gui;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class AgendaEntry extends JPanel{
	
	private String startTime;
	private String endTime;
	private JCheckBox cbStatus;
	private int y;
	
	public AgendaEntry(String startTime, String endTime, JCheckBox entry){
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.cbStatus = entry;
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
