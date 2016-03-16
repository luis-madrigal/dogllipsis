package com.entry;

import java.awt.Color;
import java.sql.Time;
import java.util.Date;

import com.calendar.TimeRange;

public class Event extends Entry{
  
  	public Event(String description, Date dateDue, Time startTime, Time endTime) {
		this.description = description;
		this.dateDue = dateDue;
		this.timeRange = new TimeRange(startTime, endTime);
		this.color = Color.BLUE;
		this.isCompleted = false;
	}

}
