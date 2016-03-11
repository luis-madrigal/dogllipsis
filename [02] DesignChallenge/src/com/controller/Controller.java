package com.controller;

import java.util.ArrayList;
import java.util.Date;

import com.model.Model;

public abstract class Controller {

	private ArrayList<Model> models;
	
	public abstract void getEntries(Date date);
}
