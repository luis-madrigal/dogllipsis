package com.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.calendar.TimeRange;
import com.entry.Entry;
import com.entry.EntryType;

public class ProductivityToolController extends Controller{


	@Override
	public ArrayList<Entry> getEntries(java.sql.Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Entry> getEntries(java.sql.Date date, EntryType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEntries(EntryType type, String description, Date dateDue, Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		this.models.get(0).addEntries(type, description, dateDue, startTime, endTime);
		//TODO: Renew view
		
	}

	@Override
	public boolean isValidEntry(java.sql.Date date, TimeRange timeRange) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeEntries(Entry entry) {
		// TODO Auto-generated method stub
		this.models.remove(entry);
		// TODO Renew view
	}

}

