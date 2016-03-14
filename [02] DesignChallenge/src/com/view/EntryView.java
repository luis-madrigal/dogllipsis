package com.view;

import java.util.ArrayList;

public abstract class EntryView extends View{
	protected ArrayList<Entry> entries;
	protected EntryType[] type;
	
	public abstract void viewTasks();
	public abstract void viewEvents();
	public abstract void viewAll();
	public abstract void viewNone();
}
