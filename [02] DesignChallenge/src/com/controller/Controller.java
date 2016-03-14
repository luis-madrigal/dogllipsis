package com.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.model.Model;

public abstract class Controller {
	protected ArrayList<Model> models;
	
	public abstract ArrayList<Entry> getEntries(Date date);
	public abstract ArrayList<Entry> getEntries(Date date, EntryType type);
	
	public abstract void addEntries(EntryType type, String description, Date dateDue, Time startTime, Time endTime);
	public abstract boolean isValidEntry(Date date, TimeRange timeRange);
	public abstract void removeEntries(Entry entry);

}
