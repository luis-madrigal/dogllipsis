package com.calendar;

import java.util.ArrayList;

import com.entry.Entry;

public class Day {

	private int day;
	private ArrayList<Entry> entries;
	private boolean[] timeslots;
	
	public Day(int day) {
		this.day = day;
		this.entries = new ArrayList<Entry>();
		for(int i = 0; i < 48; i++) {
			this.entries.add(null);
		}
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public ArrayList<Entry> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
	public boolean[] getTimeslots() {
		return timeslots;
	}
	public void setTimeslots(boolean[] timeslots) {
		this.timeslots = timeslots;
	}
}
