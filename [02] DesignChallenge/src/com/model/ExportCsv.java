package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.entry.Entry;

public class ExportCsv extends ExportFile
{
	public void generate(String sFileName, ArrayList<Entry> entries) {
		try
		{
			FileWriter writer = new FileWriter(sFileName+".csv");
			
			for(int i = 0; i < entries.size(); i++) {
				if(entries.get(i) != null) {
					writer.append(String.valueOf(entries.get(i).getTimeRange().getStartTime().getTime()));
					writer.append(",");
					writer.append(String.valueOf(entries.get(i).getTimeRange().getEndTime().getTime()));
					writer.append(",");
					writer.append(entries.get(i).getDescription());
					writer.append(",");
					writer.append(String.valueOf(entries.get(i).getColor().getRGB()));
					writer.append(",");
					writer.append(String.valueOf(entries.get(i).isCompleted()));
					writer.append(",");
					writer.append(String.valueOf(entries.get(i).getDateDue().getTime()));
					writer.append(System.getProperty("line.separator"));
				}
//				else {
//					writer.append("null");
//					writer.append(System.getProperty("line.separator"));
//				}
			}
				
			
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
  }
}
