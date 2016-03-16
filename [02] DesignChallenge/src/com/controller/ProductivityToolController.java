package com.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.calendar.TimeRange;
import com.entry.Entry;
import com.model.Model;
import com.model.ProductivityToolModel;
import com.view.EntryType;

public class ProductivityToolController extends Controller{

	public ProductivityToolController() {
		this.models = new ArrayList<Model>();
		this.models.add(new ProductivityToolModel());
	}

	@Override
	public ArrayList<Entry> getEntries(Date date) {
		return this.models.get(0).getEntries(date);
	}

	@Override
	public ArrayList<Entry> getEntries(Date date, EntryType type) {
		
		return this.models.get(0).getEntries(date, type);
	}

	@Override
	public void addEntries(EntryType type, String description, Date dateDue, Time startTime, Time endTime) {
		this.models.get(0).addEntries(type, description, dateDue, startTime, endTime);
	}

	@Override
	public boolean isValidEntry(Date date, TimeRange timeRange) {
		return this.models.get(0).isValidEntry(date, timeRange);
	}

	@Override
	public void removeEntries(Entry entry) {
		this.models.get(0).removeEntries(entry);	
	}

}
