package com.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JFrame;

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
}
