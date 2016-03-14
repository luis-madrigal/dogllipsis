package com.view;

public class CalendarView extends View{
	
	private int yearToday;
	private int monthToday;
	private int dayToday;
	private int selectedDay;
	
	public int getYearToday() {
		return yearToday;
	}
	public void setYearToday(int yearToday) {
		this.yearToday = yearToday;
	}
	public int getMonthToday() {
		return monthToday;
	}
	public void setMonthToday(int monthToday) {
		this.monthToday = monthToday;
	}
	public int getDayToday() {
		return dayToday;
	}
	public void setDayToday(int dayToday) {
		this.dayToday = dayToday;
	}
	public int getSelectedDay() {
		return selectedDay;
	}
	public void setSelectedDay(int selectedDay) {
		this.selectedDay = selectedDay;
	}
	
}
