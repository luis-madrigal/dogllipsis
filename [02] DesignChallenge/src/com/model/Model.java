package com.model;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.calendar.Calendar;
import com.calendar.TimeRange;
import com.entry.Entry;
import com.view.EntryType;

public abstract class Model {

	protected Calendar calendar;
	protected Handler handler;
	
	public abstract ArrayList<Entry> getEntries(Date date);
	public abstract ArrayList<Entry> getEntries(Date date, EntryType type);
	
	public abstract void addEntries(EntryType type, String description, Date dateDue, Time startTime, Time endTime);
	public abstract boolean isValidEntry(Date date, TimeRange timeRange);
	
	public abstract void ExportFile(String fileName, ArrayList<Entry> entries);
	public abstract void removeEntries(Entry entry);
	public abstract void importFile(JFrame baseFrame);
	
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
