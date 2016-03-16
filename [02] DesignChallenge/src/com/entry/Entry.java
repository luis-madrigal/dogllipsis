package com.entry;

import java.awt.Color;
import java.util.Date;

import com.calendar.TimeRange;

public abstract class Entry {
	protected TimeRange timeRange;
	protected String description;
	protected Color color;
	protected boolean isCompleted;
	protected Date dateDue;
	
	public TimeRange getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

}
