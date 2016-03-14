package com.calendar;

import java.io.FileWriter;
import java.io.IOException;
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

		this.years = new ArrayList<CalYear>();		
		this.minYear = Calendar.getInstance().get(Calendar.YEAR)-100;
		
		for(int i = 0; i < 200; i++){
			this.years.add(new CalYear(this.minYear+i));
		}
		GregorianCalendar cal = new GregorianCalendar();
		this.dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		this.monthToday = cal.get(GregorianCalendar.MONTH);
		this.yearToday = cal.get(GregorianCalendar.YEAR);
		this.yearBound = 100;
		this.row = 0;
		this.column = 0;
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		this.months = months;
		this.handler = new Handler();
		this.tableRenderer = new TableRenderer(); //**************************************
		
		for (int i = this.yearToday-yearBound; i <= this.yearToday+yearBound; i++){
			cmbYear.addItem(String.valueOf(i));
		}			
		this.yearToday = cal.get(GregorianCalendar.YEAR);
		
		//TODO New A
		//Change this to the directory of the CSV file you want to preload.
		String defaultPath = System.getProperty("user.home") + System.getProperty("file.separator")+"Calendar.csv";
		try {			
			handler.openFile(defaultPath);
		} catch (IOException e1) {
			e1.printStackTrace();
			FileWriter writer = new FileWriter(defaultPath);
			writer.flush();
			writer.close();
		}
		ArrayList<CalEvent> importedEvents = handler.getEvents();		
		for(int i = 0; i < importedEvents.size(); i++) {
			this.addEvent(importedEvents.get(i));
		}
		//TODO New Z
		refreshCalendar(this.monthToday, this.yearToday);
	}
	
	public Year[] getYear() {
		return year;
	}

	public void setYear(Year[] year) {
		this.year = year;
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

}
