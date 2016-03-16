package com.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.calendar.TimeRange;
import com.entry.Entry;
import com.model.Model;
import com.view.EntryType;

public abstract class Controller {
	protected ArrayList<Model> models;
	
	public abstract ArrayList<Entry> getEntries(Date date);
	public abstract ArrayList<Entry> getEntries(Date date, EntryType type);
	
	public abstract void addEntries(EntryType type, String description, Date dateDue, Time startTime, Time endTime);
	public abstract boolean isValidEntry(Date date, TimeRange timeRange);
	public abstract void removeEntries(Entry entry);
	
	public ArrayList<Model> getModels() {
		return models;
	}
	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}
}
