package com.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.entry.Entry;
import com.gui.DataFileFilter;

public class Handler {
	private ArrayList<Entry> entries;
	
	public String openFile(JFrame baseFrame) throws IOException {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new DataFileFilter());
		int returnVal = fc.showOpenDialog(baseFrame);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File fileName = fc.getSelectedFile();
			//this.getEventsFromFile(fileName);
			return fileName.getName();
		}
		return "";
	}
	//TODO Added this for the preloading
	public void openFile(String path) throws IOException {
		//this.getEventsFromFile(new File(path));
		FileHandlerCSV handlerCsv = new FileHandlerCSV();
		handlerCsv.importFile(new File(path));
		this.entries = handlerCsv.getEntries();
	}
	
	public ArrayList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}

}
