package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

import com.entry.Entry;


public abstract class ImportFile {
	
	protected ArrayList<Entry> entries;
	protected BufferedReader br;
	public abstract void importFile(File file);

}
