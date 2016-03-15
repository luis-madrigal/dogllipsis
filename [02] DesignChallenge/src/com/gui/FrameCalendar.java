// Di pa nagpapalit yung mga days pag nagne-next or prev, see LINE 124

package com.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	
	private JLabel[] lblCalendarDay; 
	private JButton[][] btnDay;	
	
	private GregorianCalendar cal;
	private String[] month = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private int currentMonth, currentYear;
	private int rows =6;
	private int cols = 7;
	
	public FrameCalendar(int width, int height, CalendarView viewCalendar) {
		super(width, height);
		
		this.viewCalendar = viewCalendar;
		
		this.cal = new GregorianCalendar();
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
		
		this.btnNext = new JButton(">");
		btnNext.setBounds(this.getWidth()-14, 0, 14, 42);
		btnNext.addMouseListener(this);
		
		this.btnPrev = new JButton("<");
		btnPrev.setBounds(0, 0, 14, 42);
		btnPrev.addMouseListener(this);
		
		int size = 18;	
		int x = 12;
		int y = 91;
		
		this.btnDay = new JButton[cols][rows];
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				this.btnDay[i][j] = new JButton();
				this.btnDay[i][j].setBounds(x+((size+3)*i), y+((size+3)*j), 18, 18);
				this.btnDay[i][j].setBorder(null);
				this.btnDay[i][j].setVisible(true);
				
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
				if(day>= noOfDays){
					startDays=false;
				}
				if(startDays==true){
					this.btnDay[j][i].setText(String.valueOf(day));
					day++;
				}
					
				if(i == 0 && j == startOfMonth-1){
					System.out.println("SOM: " + startOfMonth);
					this.btnDay[j][i].setText(String.valueOf(day));
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
		if(e.getSource().equals(this.btnNext)){
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
