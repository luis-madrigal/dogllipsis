package com.calendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Month {
	
	private ArrayList<Day> days;
	private int month;
	private int startOfMonth;
	private int endOfMonth;
	
	public Month(int month, int year) {
		this.month = month;
		this.days = new ArrayList<Day>();
		GregorianCalendar days = new GregorianCalendar(year, month, 1);
		this.startOfMonth = days.get(GregorianCalendar.DAY_OF_WEEK);
		this.endOfMonth = days.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		for(int i = 1; i <= this.endOfMonth; i++) {
			this.days.add(new Day(i));
		}
	}
	
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
