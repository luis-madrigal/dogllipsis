package com.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;
 
public class DataFileFilter extends FileFilter {
 
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
 
        String extension = DataFileFilter.getExtension(file.getName());
        if (extension != null) {
            if (extension.equals("csv")) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
    
    public static String getExtension(String f) {
    	String ext = null;
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            ext = f.substring(i+1).toLowerCase();
        }        
        return ext;
    }

	@Override
	public String getDescription() {
		return "Just CSV files";
	}
}
