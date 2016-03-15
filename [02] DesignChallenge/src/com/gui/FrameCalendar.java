package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.view.CalendarView;

public class FrameCalendar extends Frame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private CalendarView viewCalendar;
	private JLabel lblCalendarMonth;
	private JLabel lblCalendarYear;
	private JLabel lblCalendarBg;
	
	private JButton btnNext;
	private JButton btnPrev;
	
	public FrameCalendar(int width, int height, CalendarView viewCalendar) {
		super(width, height);
		
		this.viewCalendar = viewCalendar;
		this.initComponents();
		
		this.add(btnNext);
		this.add(btnPrev);
		
		this.add(lblCalendarMonth);
		this.add(lblCalendarYear);		
		this.add(lblCalendarBg);
	}

	@Override
	protected void initComponents() {
		this.lblCalendarBg = new JLabel();
		this.lblCalendarMonth = new JLabel("December");
		this.lblCalendarYear = new JLabel("2016");
		
		Frame.initLabels(lblCalendarBg, "Sign CalendarBg", null);
		lblCalendarBg.setBounds(0, 0, lblCalendarBg.getWidth(), lblCalendarBg.getHeight());
		
		lblCalendarMonth.setFont(Application.fntBebas25);
		lblCalendarMonth.setForeground(Application.Color_NIGHT);
		lblCalendarMonth.setHorizontalAlignment(JLabel.CENTER);		
		lblCalendarMonth.setBounds(0, 10, 168, 28);
		
		lblCalendarYear.setFont(Application.fntBebas25);
		lblCalendarYear.setForeground(Application.Color_NIGHT);
		lblCalendarYear.setHorizontalAlignment(JLabel.CENTER);
		lblCalendarYear.setBounds(0, 225, 168, 28);
		
		this.btnNext = new JButton(">");
		btnNext.setBounds(this.getWidth()-14, 0, 14, 42);
		btnNext.addMouseListener(this);
		
		this.btnPrev = new JButton("<");
		btnPrev.setBounds(0, 0, 14, 42);
		btnPrev.addMouseListener(this);
		
	}

	@Override
	public void open() {
		
	}

	@Override
	public void close() {
	
	}

	@Override
	public void display() {
		
	}

	@Override
	public void refresh() {
	
	}

	public void next() {
		this.viewCalendar.setMonthToday(this.viewCalendar.getMonthToday()+1);
	}
	
	public void prev() {
		this.viewCalendar.setMonthToday(this.viewCalendar.getMonthToday()-1);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
}
