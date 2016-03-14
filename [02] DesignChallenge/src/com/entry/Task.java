package com.entry;

public class Task extends Entry{
  
  public boolean isSelected;
	
	private boolean isSelected;
	
	public Task(String description, Date dateDue, Time startTime, Time endTime) {
		
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
