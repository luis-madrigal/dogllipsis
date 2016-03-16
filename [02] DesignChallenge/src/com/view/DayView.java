package com.view;

import java.util.ArrayList;

import com.controller.ProductivityToolController;
import com.entry.Entry;

public class DayView extends EntryView {

	public DayView(ProductivityToolController controller) {
		this.entries = new ArrayList<Entry>();
		this.type = new EntryType[48];
		this.controller = controller;
		
	}
	
	@Override
	public void viewTasks() {
		
	}

	@Override
	public void viewEvents() {
	
	}

	@Override
	public void viewAll() {
		
	}

	@Override
	public void viewNone() {
	
	}

}
