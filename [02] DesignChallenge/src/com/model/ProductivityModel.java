package com.model;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import com.calendar.Calendar;
import com.calendar.TimeRange;
import com.entry.Entry;
import com.entry.EntryFactory;
import com.entry.Event;
import com.entry.Task;
import com.gui.Application;
import com.view.EntryType;

public class ProductivityToolModel extends Model {

	public ProductivityToolModel() {
		this.calendar = new Calendar();
		this.handler = new Handler();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<Entry> getEntries(Date date) {
		System.out.println("date: "+date);
		String strYear = date.toString().substring(date.toString().length()-4, date.toString().length());
		int year = Integer.parseInt(strYear);		
		GregorianCalendar calendar = new GregorianCalendar(year, date.getMonth(), date.getDate());		
		int month = calendar.get(java.util.Calendar.MONTH);
		int day = calendar.get(java.util.Calendar.DATE);	
		
		int minYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)-100;
		int indexYear = year-minYear;		
		int indexMonth = month;
		int indexDay = day-1;
		System.out.println("indexYear: "+minYear);
		return this.calendar.getYears().get(indexYear).getMonths().get(indexMonth).getDays().get(indexDay).getEntries();
	}

	@Override
	public ArrayList<Entry> getEntries(Date date, EntryType type) {
		ArrayList<Entry> entries = this.getEntries(date);
		ArrayList<Entry> sortedEntries = new ArrayList<Entry>();
		
		switch(type) {
			case EVENT:
				for(int i = 0; i < entries.size(); i++) {					
					if(entries.get(i) instanceof Task) {
						sortedEntries.add(null);
					}				
					else {
						sortedEntries.add(entries.get(i));
					}
				}				
				break;
				
			case TASK:
				for(int i = 0; i < entries.size(); i++) {
					if(entries.get(i) instanceof Event) {
						sortedEntries.add(null);
					}					
					else {
						sortedEntries.add(entries.get(i));
					}
				}				
				break;
				
			case NONE:
				for(int i = 0; i < entries.size(); i++) {
					sortedEntries.add(null);					
				}		
				break;
		}
		
		return sortedEntries;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addEntries(EntryType type, String description, Date dateDue,
			Time startTime, Time endTime) {
		
		String strYear = dateDue.toString().substring(dateDue.toString().length()-4, dateDue.toString().length());
		int year = Integer.parseInt(strYear);		
		GregorianCalendar calendar = new GregorianCalendar(year, dateDue.getMonth(), dateDue.getDate());		
		int month = calendar.get(java.util.Calendar.MONTH);
		int day = calendar.get(java.util.Calendar.DATE);	
		
		int minYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)-100;
		int indexYear = year-minYear;		
		int indexMonth = month;
		int indexDay = day-1;
		
		int startIndex = Application.getTimeIndex(startTime);
		int endIndex = Application.getTimeIndex(endTime);
		if(type == EntryType.TASK)
			endIndex = startIndex+1;
		
		for(int i = startIndex; i < endIndex; i++) {
			this.calendar.getYears().get(indexYear).getMonths().get(indexMonth).getDays().get(indexDay).getEntries().set(i, 
					EntryFactory.createEntry(type, description, dateDue, startTime, endTime));
		}
		
//		this.calendar.getYears().get(indexYear).getMonths().get(indexMonth).getDays().get(indexDay).getEntries().set(Application.getTimeIndex(startTime), 
//				EntryFactory.createEntry(type, description, dateDue, startTime, endTime));
		System.out.println("MODEL added to date: "+Application.getTimeIndex(startTime));
	}

	@Override
	public boolean isValidEntry(Date date, TimeRange timeRange) {
		ArrayList<Entry> entries = this.getEntries(date);

		int startIndex = Application.getTimeIndex(timeRange.getStartTime());
		int endIndex = Application.getTimeIndex(timeRange.getEndTime());
		
		if(startIndex >= endIndex)
			return false;
		
		for(int i = startIndex; i < endIndex; i++) {
			if(entries.get(i) != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void ExportFile(String fileName, ArrayList<Entry> entries) {
		ExportCsv export = new ExportCsv();
		export.generate("entries", entries);
	}

	@Override
	public void removeEntries(Entry entry) {
		System.out.println("remove");
		if(entry != null) {
			ArrayList<Entry> entries = this.getEntries(entry.getDateDue());
			int startIndex = Application.getTimeIndex(entry.getTimeRange().getStartTime());
			int endIndex = Application.getTimeIndex(entry.getTimeRange().getEndTime());
			
			for(int i = startIndex; i < endIndex; i++) {
				entries.set(i, null);		
				System.out.println("Removed index: "+i);
			}	
		}
	}

	@Override
	public void importFile(JFrame baseFrame) {
		ArrayList<Entry> importedEntries = new ArrayList<Entry>();
		try {	
			ImportFile fileHandler = null;
			String ext = this.handler.openFile(baseFrame);
			if(ext == "")
				;
			else {
				fileHandler = new FileHandlerCSV();
				fileHandler.importFile(new File(ext));	
				importedEntries = fileHandler.entries;
			}
						
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i < importedEntries.size(); i++) {
			this.getEntries(importedEntries.get(i).getDateDue()).add(importedEntries.get(i));
		}
	}
	
}
