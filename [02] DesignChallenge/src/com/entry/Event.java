package com.entry;

public class Event extends Entry{
  
  	public Event(String description, Date dateDue, Time startTime, Time endTime) {
		this.description = description;
		this.dateDue = dateDue;
		this.timeRange.setStartTime(startTime);
		this.timeRange.setEndTime(endTime);
		this.color = Color.BLUE;
		this.isCompleted = false;
	}

}
