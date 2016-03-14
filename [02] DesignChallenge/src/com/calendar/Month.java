package com.calendar;

import java.util.ArrayList;

public class Month {
	
	private ArrayList<Day> days;
	private int month;
	private int startOfMonth;
	private int endOfMonth;
	
	public ArrayList<Day> getDays() {
		return days;
	}
	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getStartOfMonth() {
		return startOfMonth;
	}
	public void setStartOfMonth(int startOfMonth) {
		this.startOfMonth = startOfMonth;
	}
	public int getEndOfMonth() {
		return endOfMonth;
	}
	public void setEndOfMonth(int endOfMonth) {
		this.endOfMonth = endOfMonth;
	}


}
