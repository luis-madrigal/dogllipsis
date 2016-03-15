package com.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.calendar.Calendar;
import com.calendar.TimeRange;
import com.entry.Entry;
import com.entry.EntryFactory;
import com.entry.Event;
import com.entry.Task;
import com.view.EntryType;

public class ProductivityToolModel extends Model {

	public ProductivityToolModel() {
		this.calendar = new Calendar();
		
	}
	
	@Override
	public ArrayList<Entry> getEntries(Date date) {
		String strYear = date.toString().substring(date.toString().length()-4, date.toString().length());
		int year = Integer.parseInt(strYear);		
		GregorianCalendar calendar = new GregorianCalendar(year, date.getMonth(), date.getDate());		
		int month = calendar.get(calendar.MONTH);
		int day = calendar.get(calendar.DATE);	
		
		int minYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)-100;
		int indexYear = year-minYear;		
		int indexMonth = month;
		int indexDay = day-1;
		
		System.out.println("year: "+year);
		System.out.println("month: "+month);
		System.out.println("day: "+day);
		System.out.println("indexYear: "+indexYear);
		return this.calendar.getYears().get(indexYear).getMonths().get(indexMonth).getDays().get(indexDay).getEntries();
	}

	@Override
	public ArrayList<Entry> getEntries(Date date, EntryType type) {
		ArrayList<Entry> entries = this.getEntries(date);
		ArrayList<Entry> sortedEntries = new ArrayList<Entry>();
		
		switch(type) {
			case EVENT:
				for(int i = 0; i < entries.size(); i++) {
					if(entries.get(i) instanceof Event) {
						sortedEntries.add(entries.get(i));
					}					
				}				
				break;
				
			case TASK:
				for(int i = 0; i < entries.size(); i++) {
					if(entries.get(i) instanceof Task) {
						sortedEntries.add(entries.get(i));
					}					
				}				
				break;
				
			case NONE:
				sortedEntries = entries;
				break;
		}
		
		return null;
	}

	@Override
	public void addEntries(EntryType type, String description, Date dateDue,
			Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		this.getEntries(dateDue, type).add(EntryFactory.createEntry(type, description, dateDue, startTime, endTime));
	}
	
	@Override
	public boolean isValidEntry(Date date, TimeRange timeRange) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ExportFile(String fileName, ArrayList<Entry> entries) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntries(Entry entry) {
		// TODO Auto-generated method stub
		this.getEntries(entry.getDateDue()).remove(entry);
	}

	@Override
	public void importFile(JFrame baseFrame) {
		// TODO Auto-generated method stub
		
	}

	
}
