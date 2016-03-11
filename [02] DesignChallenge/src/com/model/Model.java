package com.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.entry.Entry;

public abstract class Model {
	
	private Calendar calendar;
	public abstract ArrayList<Entry> getEntries(Date date);

}
