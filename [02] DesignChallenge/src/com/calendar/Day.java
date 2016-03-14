package com.calendar;

import java.util.ArrayList;

import com.entry.Entry;

public class Day {

	private int day;
	private ArrayList<Entry> entries;
	private boolean[] timeslots;
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Entry[] getEntries() {
		return entries;
	}
	public void setEntries(Entry[] entries) {
		this.entries = entries;
	}
	public boolean[] getTimeslots() {
		return timeslots;
	}
	public void setTimeslots(boolean[] timeslots) {
		this.timeslots = timeslots;
	}
}
