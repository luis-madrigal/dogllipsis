package com.entry;

import java.awt.Color;
import java.sql.Time;
import java.util.Date;

import com.calendar.TimeRange;

public class Task extends Entry{
  
	private boolean isSelected;
	
	public Task(String description, Date dateDue, Time startTime, Time endTime) {
		this.description = description;
		this.dateDue = dateDue;
		this.timeRange = new TimeRange(startTime, endTime);
		
		this.color = Color.GREEN;
		this.isCompleted = false;
		this.isSelected = false;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
