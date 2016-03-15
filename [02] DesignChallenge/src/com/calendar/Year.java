package com.calendar;

import java.util.ArrayList;

public class Year {
	
	private ArrayList<Month> months;
	private int year;
	
	public Year(int year) {
		this.year = year;
		this.months = new ArrayList<Month>();
		for(int i = 0; i < 12; i++){
			this.months.add(new Month(i, year));
		}
		
	}
	
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
