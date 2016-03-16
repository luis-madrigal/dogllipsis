package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.view.CalendarView;

public class FrameCalendar extends Frame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Application frmParent;
	private CalendarView viewCalendar;
	private JLabel lblCalendarMonth;
	private JLabel lblCalendarYear;
	private JLabel lblCalendarBg;	
	
	private JButton btnNext;
	private JButton btnPrev;
	
	private JLabel[] lblCalendarDay; 
	private JButton[][] btnDay;	
	
	private GregorianCalendar cal;
	private String[] month = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private int currentDay;
	private int currentMonth;
	private int currentYear;
	private int rows = 6;
	private int cols = 7;
	
	public FrameCalendar(int width, int height, Application frmParent) {
		super(width, height);
		this.frmParent = frmParent;
//		this.viewCalendar = viewCalendar;
		
		this.cal = new GregorianCalendar();
		this.currentDay = this.cal.get(GregorianCalendar.DAY_OF_MONTH);
		this.currentMonth = this.cal.get(GregorianCalendar.MONTH);
		this.currentYear = this.cal.get(GregorianCalendar.YEAR);
		
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
		this.lblCalendarMonth = new JLabel(this.month[this.currentMonth]);
		this.lblCalendarYear = new JLabel(String.valueOf(this.currentYear));
		
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
		
		this.btnNext = new JButton();
		Frame.initButtons(btnNext, "btnNext", this);
		btnNext.setBounds(this.getWidth()-btnNext.getWidth()-6, 15, btnNext.getWidth(), btnNext.getHeight());
//		btnNext.addMouseListener(this);
		
		this.btnPrev = new JButton();
		Frame.initButtons(btnPrev, "btnPrev", this);
		btnPrev.setBounds(6, 15, btnPrev.getWidth(), btnPrev.getHeight());
//		btnPrev.addMouseListener(this);
		
		int size = 18;	
		int x = 12;
		int y = 91;
		ImageIcon iiBtnDay = new ImageIcon("images/btnDay_off");
		
		this.btnDay = new JButton[cols][rows];
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				this.btnDay[i][j] = new JButton();
				this.btnDay[i][j].setBounds(x+((size+3)*i), y+((size+3)*j), 18, 18);
				this.btnDay[i][j].setBorder(null);
				this.btnDay[i][j].setIcon(iiBtnDay);
				this.btnDay[i][j].setContentAreaFilled(false);
				this.btnDay[i][j].setFocusable(false);
				this.btnDay[i][j].setHorizontalAlignment(JButton.CENTER);
				this.btnDay[i][j].setVisible(true);
//				this.btnDay[i][j].addMouseListener(this);
				if(i == 0){
					this.btnDay[i][j].setForeground(Application.Color_PEARL);
				}
				else {
					this.btnDay[i][j].setForeground(Application.Color_NIGHT);
				}
				
				this.add(this.btnDay[i][j]);				
				this.repaint();
			}
		}
		this.refresh();
		
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
		
		YearMonth yearMonth = YearMonth.of(this.currentYear, this.currentMonth+1);		
		int noOfDays = yearMonth.lengthOfMonth(); //29
		int startOfMonth = new GregorianCalendar(currentYear, currentMonth, 1).get(GregorianCalendar.DAY_OF_WEEK);
		
		int day = 1;
		boolean startDays = false;
		System.out.println(startOfMonth);
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				this.btnDay[j][i].setText("");
				this.btnDay[j][i].removeMouseListener(this);
				if(day> noOfDays){
					startDays=false;
				}
				if(startDays==true){
					this.btnDay[j][i].setText(String.valueOf(day));
					this.btnDay[j][i].addMouseListener(this);
					day++;
				}
					
				if(i == 0 && j == startOfMonth-1){
					System.out.println("SOM: " + startOfMonth);
					this.btnDay[j][i].setText(String.valueOf(day));
					this.btnDay[j][i].addMouseListener(this);
					startDays=true;
					day++;
				}
			}
		}
		this.repaint();
		
	}

	public void next() {
		this.viewCalendar.setMonthToday(this.viewCalendar.getMonthToday()+1);
	}
	
	public void prev() {
		this.viewCalendar.setMonthToday(this.viewCalendar.getMonthToday()-1);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(this.btnPrev)){
			if(this.currentMonth==0){
				if(this.currentYear > this.cal.get(GregorianCalendar.YEAR)-100){
					this.currentYear--;
					this.currentMonth = 11;
				}
				else
					this.currentMonth = 0;
			}
			
			else{
				this.currentMonth--;
			}
			this.lblCalendarMonth.setText(this.month[this.currentMonth]);
			this.lblCalendarYear.setText(String.valueOf(this.currentYear));
			this.refresh();
		}
		else if(e.getSource().equals(this.btnNext)){
			if(this.currentMonth==11){
				if(this.currentYear < this.cal.get(GregorianCalendar.YEAR)+100){
					this.currentYear++;
					this.currentMonth = 0;
				}
				else
					this.currentMonth = 11;
			}
			else{
				this.currentMonth++;
			}
			this.lblCalendarMonth.setText(this.month[this.currentMonth]);
			this.lblCalendarYear.setText(String.valueOf(this.currentYear));
			this.refresh();
		}
		else if(e.getSource() instanceof JButton) {
			System.out.println("ins");
			
			this.currentDay = Integer.parseInt(((JButton)e.getComponent()).getText());
			this.frmParent.refresh(this.currentMonth, this.currentDay, this.currentYear);
			System.out.println("Date set: "+this.currentDay);
		}
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

	public Application getFrmParent() {
		return frmParent;
	}

	public void setFrmParent(Application frmParent) {
		this.frmParent = frmParent;
	}

	public CalendarView getViewCalendar() {
		return viewCalendar;
	}

	public void setViewCalendar(CalendarView viewCalendar) {
		this.viewCalendar = viewCalendar;
	}

	public JLabel getLblCalendarMonth() {
		return lblCalendarMonth;
	}

	public void setLblCalendarMonth(JLabel lblCalendarMonth) {
		this.lblCalendarMonth = lblCalendarMonth;
	}

	public JLabel getLblCalendarYear() {
		return lblCalendarYear;
	}

	public void setLblCalendarYear(JLabel lblCalendarYear) {
		this.lblCalendarYear = lblCalendarYear;
	}

	public JLabel getLblCalendarBg() {
		return lblCalendarBg;
	}

	public void setLblCalendarBg(JLabel lblCalendarBg) {
		this.lblCalendarBg = lblCalendarBg;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnPrev() {
		return btnPrev;
	}

	public void setBtnPrev(JButton btnPrev) {
		this.btnPrev = btnPrev;
	}

	public JLabel[] getLblCalendarDay() {
		return lblCalendarDay;
	}

	public void setLblCalendarDay(JLabel[] lblCalendarDay) {
		this.lblCalendarDay = lblCalendarDay;
	}

	public JButton[][] getBtnDay() {
		return btnDay;
	}

	public void setBtnDay(JButton[][] btnDay) {
		this.btnDay = btnDay;
	}

	public GregorianCalendar getCal() {
		return cal;
	}

	public void setCal(GregorianCalendar cal) {
		this.cal = cal;
	}

	public String[] getMonth() {
		return month;
	}

	public void setMonth(String[] month) {
		this.month = month;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}
