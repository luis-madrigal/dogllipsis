package com.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.entry.Entry;
import com.entry.EntryFactory;
import com.view.EntryType;

public class FileHandlerCSV extends ImportFile{

	public FileHandlerCSV() {
		entries = new ArrayList<Entry>();
	}
	
	public ArrayList<Entry> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}
	
	@Override
	public void importFile(File file) {
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				Time startTime = new Time(Long.parseLong(values[0].trim()));
				Time endTime = new Time(Long.parseLong(values[1].trim()));
				String description = values[2].trim();
				Color color = new Color(Integer.parseInt(values[3].trim()));
				boolean isCompleted = Boolean.parseBoolean(values[4].trim());
				Date dateDue = new Date(Long.parseLong(values[5].trim()));
				EntryType type = null;
				if(color.equals(Color.BLUE))
					type = EntryType.EVENT;
				else if(color.equals(Color.GREEN))
					type = EntryType.TASK;
				
				Entry newEntry = EntryFactory.createEntry(type, description, dateDue, startTime, endTime);
				newEntry.setCompleted(isCompleted);
				
				this.entries.add(newEntry);
			}
		} catch(Exception e1) {
			
		}
		
	}		
}
