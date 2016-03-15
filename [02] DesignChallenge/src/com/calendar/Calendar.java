package com.calendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Calendar {
	
	private ArrayList<Year> years;
	private int yearBound;
	private int dayBound;
	private int yearToday;
	private int monthToday;
	private int minYear;
	private String[] headers;
	private String[] months;
	public Calendar(){

		this.years = new ArrayList<Year>();		
		this.minYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)-100;
		
		for(int i = 0; i < 200; i++){
			this.years.add(new Year(this.minYear+i));
		}
		GregorianCalendar cal = new GregorianCalendar();
		this.dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		this.monthToday = cal.get(GregorianCalendar.MONTH);
		this.yearToday = cal.get(GregorianCalendar.YEAR);
		this.yearBound = 100;
		
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		this.months = months;

//		String defaultPath = System.getProperty("user.home") + System.getProperty("file.separator")+"Calendar.csv";
	}

	public int getYearBound() {
		return yearBound;
	}

	public void setYearBound(int yearBound) {
		this.yearBound = yearBound;
	}

	public int getDayBound() {
		return dayBound;
	}

	public void setDayBound(int dayBound) {
		this.dayBound = dayBound;
	}

	public int getMonthToday() {
		return monthToday;
	}

	public void setMonthToday(int monthToday) {
		this.monthToday = monthToday;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public ArrayList<Year> getYears() {
		return years;
	}

	public void setYears(ArrayList<Year> years) {
		this.years = years;
	}

	public int getYearToday() {
		return yearToday;
	}

	public void setYearToday(int yearToday) {
		this.yearToday = yearToday;
	}

}
