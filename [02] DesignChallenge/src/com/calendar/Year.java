package com.calendar;

import java.util.ArrayList;

public class Year {
	
	private ArrayList<Month> months;
	private int year;
	
	public ArrayList<Month> getMonths() {
		return months;
	}
	public void setMonths(ArrayList<Month> months) {
		this.months = months;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
