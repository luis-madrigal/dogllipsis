package com.entry;

public class Task extends Entry{
  
  public boolean isSelected;
	
	private boolean isSelected;
	
	public Task(String description, Date dateDue, Time startTime, Time endTime) {
		this.description = description;
		this.dateDue = dateDue;
		this.timeRange.setStartTime(startTime);
		this.timeRange.setEndTime(endTime);
		this.color = Color.GREEN;
		this.isCompleted = false;
		this.isSelected = false;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
