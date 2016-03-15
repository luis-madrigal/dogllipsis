package com.entry;

public abstract class EntryFactory {
	
	public static Entry createEntry(EntryType type, String description, Date dateDue, Time startTime, Time endTime) {
		Entry newEntry = null;
		if(type == EntryType.EVENT) {
			newEntry = new Event(description, dateDue, startTime, endTime);
		}
		else if(type == EntryType.TASK) {
			newEntry = new Task(description, dateDue, startTime, endTime);
		}
		return newEntry;
		
	}

}
