package com.view;

import java.util.ArrayList;

import com.entry.Entry;

public abstract class EntryView extends View{
	protected ArrayList<Entry> entries;
	protected EntryType[] type;
	
	public abstract void viewTasks();
	public abstract void viewEvents();
	public abstract void viewAll();
	public abstract void viewNone();
	
	public ArrayList<Entry> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
	public EntryType[] getType() {
		return type;
	}
	public void setType(EntryType[] type) {
		this.type = type;
	}
}
