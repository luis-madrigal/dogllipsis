package com.model;

import java.util.ArrayList;

import com.entry.Entry;

public abstract class ExportFile {
	public abstract void generate(String sFileName, ArrayList<Entry> entries);

}
