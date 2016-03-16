package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import com.controller.ProductivityToolController;
import com.entry.Entry;
import com.view.AgendaView;
import com.view.CalendarView;

public class Application extends JFrame implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	public static int MAX_WIDTH = 1366;
	public static int MAX_HEIGHT = 768;
	public static Color Color_TRANS = new Color(1.0f,1.0f,1.0f,0.0f);
	public static Color Color_NIGHT = new Color(0, 10, 41);
	public static Color Color_FLASH = new Color(236, 184, 60);
	public static Color Color_PEARL = new Color(247, 245, 241);
	public static Color Color_BEIGE = new Color(218, 209, 171);
	public static Font fntBebas45 = new Font("Bebas", Font.PLAIN, 45);
	public static Font fntBebas29 = new Font("Bebas", Font.PLAIN, 29);
	public static Font fntBebas27 = new Font("Bebas", Font.PLAIN, 27);
	public static Font fntBebas25 = new Font("Bebas", Font.PLAIN, 25);
	public static Font fntBebas18 = new Font("Bebas", Font.PLAIN, 18);
	
	private JLabel lblLampPost;
	private JLabel lblStarPost;
	private JLabel lblCurrentDateBg;
	private JLabel lblCurrentDateTxt;
	private JLabel lblCalendarBg;
	
	private int posX;
	private int posY;
	
//	public JPanel pnlFrame;
	private FrameCalendar pnlCalendar;
	private FrameDay pnlDay;
	private FrameAgenda pnlAgenda;
	
	private JButton btnClose;
	private JButton btnNewEntry;
	private JButton btnToday;
	private JButton btnAgendaView;
	private JButton btnDayView;
	
	private JButton btnViewEvents;
	private JButton btnViewTasks;
	
	private boolean isDayView; // to toggle between the agenda and day icons
	private boolean isSelectedEvent;
	private boolean isSelectedTask;
	
	
	private ProductivityToolController controller;
	private CalendarView viewCalendar;
//	private DayView viewDay;
	private AgendaView viewAgenda;
	private DialogNewEntry dlgNewEntry;
	
	public Application() {
		this.setLayout(null);
		this.setBounds(0, 0, MAX_WIDTH, MAX_WIDTH);
		this.setUndecorated(true);
		this.setBackground(Color_TRANS);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);	
		this.setAutoRequestFocus(true);
		this.setFocusable(true);
		this.setFocusableWindowState(true);
		
		this.viewCalendar = new CalendarView();
//		this.viewDay = new DayView();
		this.viewAgenda = new AgendaView();
	
		this.posX = 0;
		this.posY = 0;
		this.initComponents();
		
		this.add(lblLampPost);
		this.add(lblStarPost);
		
		this.add(lblCurrentDateTxt);
		this.add(lblCurrentDateBg);		
		
		this.add(pnlCalendar);
//		this.add(pnlFrame);
		this.add(pnlAgenda);
		this.add(pnlDay);
		
		this.add(pnlCalendar);
		
		this.add(btnClose);
		this.add(btnToday);
		this.add(btnAgendaView);
		this.add(btnDayView);
		this.add(btnViewEvents);
		this.add(btnViewTasks);
		this.add(btnNewEntry);
		
		this.btnDayView.setIcon(btnDayView.getRolloverIcon());
		this.btnViewEvents.setIcon(btnViewEvents.getRolloverIcon());
		this.btnViewTasks.setIcon(btnViewTasks.getRolloverIcon());
		this.setVisible(true);
		
		Date today = new Date();
		this.lblCurrentDateTxt.setText(getSelectedDateString(new Date()));
//		this.lblCurrentDateBg.addMouseMotionListener(this);
		
		//for loading file
		try {
			this.controller.getModels().get(0).getHandler().openFile("entries.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!this.controller.getModels().get(0).getHandler().getEntries().isEmpty()) {
			ArrayList<Entry> handlerEntries;
			ArrayList<Entry> modelEntries;
			for(int i = 0; i < this.controller.getModels().get(0).getHandler().getEntries().size(); i++) {
				handlerEntries = this.controller.getModels().get(0).getHandler().getEntries();
				modelEntries = this.controller.getModels().get(0).getEntries(this.controller.getModels().get(0).getHandler().getEntries().get(i).getDateDue());
				for(int j = Application.getTimeIndex(handlerEntries.get(i).getTimeRange().getStartTime()); j < Application.getTimeIndex(handlerEntries.get(i).getTimeRange().getEndTime()); j++) {
					modelEntries.set(j, handlerEntries.get(i));
				}	
			}
		}
		
//		Date date = new Date(dlgNewEntry.getCurrentYear()-1900, dlgNewEntry.getCurrentMonth(), dlgNewEntry.getCurrentDay());
		this.refresh(dlgNewEntry.getCurrentMonth(), dlgNewEntry.getCurrentDay(), dlgNewEntry.getCurrentYear());
	}
	
	public void initComponents() {
		UIManager.put("ToolTip.background", new Color(45,45, 45));
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	
		this.controller = new ProductivityToolController();
		this.dlgNewEntry = new DialogNewEntry(this);
		dlgNewEntry.setVisible(false);
		
		this.isDayView = true;
		this.isSelectedEvent = true;
		this.isSelectedTask = true;
		
		this.btnToday = new JButton();
		this.btnAgendaView = new JButton();
		this.btnDayView = new JButton();
		this.btnNewEntry = new JButton();
		this.btnViewEvents = new JButton();
		this.btnViewTasks = new JButton();
		this.btnClose = new JButton();
		
		this.lblLampPost = new JLabel();
		this.lblStarPost = new JLabel();
		this.lblCurrentDateBg = new JLabel();
		this.lblCurrentDateTxt = new JLabel();
		lblCurrentDateTxt.setFont(fntBebas25);
		lblCurrentDateTxt.setForeground(Color_NIGHT);
		lblCurrentDateTxt.setHorizontalAlignment(JLabel.CENTER);
				
		Frame.initButtons(btnToday, "btnToday", this);
		Frame.initButtons(btnAgendaView, "btnAgendaView", this);
		Frame.initButtons(btnDayView, "btnDayView", this);
		Frame.initButtons(btnNewEntry, "btnNewEntry", this);
		Frame.initButtons(btnViewEvents, "btnEvent", this);
		Frame.initButtons(btnViewTasks, "btnTask", this);
		
		Frame.initLabels(lblLampPost, "Post Lamp", null);
		Frame.initLabels(lblStarPost, "Post Star", null);
		Frame.initLabels(lblCurrentDateBg, "Sign CurrentDateBg", null);
		
		lblLampPost.setBounds(100, 10, lblLampPost.getWidth(), lblLampPost.getHeight());
		lblStarPost.setBounds(1012, 44, lblStarPost.getWidth(), lblStarPost.getHeight());
		lblCurrentDateBg.setBounds(384, 151, lblCurrentDateBg.getWidth(), lblCurrentDateBg.getHeight());
	
		
		btnToday.setBounds(290, 67, btnToday.getWidth(), btnToday.getHeight());
		btnAgendaView.setBounds(1154, 134, btnAgendaView.getWidth(), btnAgendaView.getHeight());
		btnDayView.setBounds(1020, 117, btnDayView.getWidth(), btnDayView.getHeight());
		btnNewEntry.setBounds(1121, 282, btnNewEntry.getWidth(), btnNewEntry.getHeight());
		btnViewEvents.setBounds(244, 655, btnViewEvents.getWidth(), btnViewEvents.getHeight());
		btnViewTasks.setBounds(240, 573, btnViewTasks.getWidth(), btnViewTasks.getHeight());
		
		
		lblCurrentDateTxt.setBounds(lblCurrentDateBg.getBounds());
		this.lblCalendarBg = new JLabel();
		Frame.initLabels(lblCalendarBg, "Sign CalendarBg", null);
		lblCalendarBg.setBounds(122, 217, lblCalendarBg.getWidth(), lblCalendarBg.getHeight());
		
		this.pnlCalendar = new FrameCalendar(lblCalendarBg.getWidth(), lblCalendarBg.getHeight(), this);
		pnlCalendar.setBounds(lblCalendarBg.getBounds());
		
		
		this.pnlDay = new FrameDay(MAX_WIDTH, MAX_HEIGHT, this.controller);
		this.pnlAgenda = new FrameAgenda(MAX_WIDTH, MAX_HEIGHT, viewAgenda, this.controller);
//		this.pnlFrame = this.pnlDay;
		this.pnlAgenda.close();
	}
	
	public void refresh(int month, int day, int year) {
		this.lblCurrentDateTxt.setText(convertToMonth(month)+" "+day+", "+year);
		this.pnlDay.refresh(month, day, year);
		this.pnlAgenda.refresh(month, day, year);
	}
	
	public String getSelectedDateString(Date date) {
		String strYear = date.toString().substring(date.toString().length()-4, date.toString().length());
		int year = Integer.parseInt(strYear);		
		GregorianCalendar calendar = new GregorianCalendar(year, date.getMonth(), date.getDate());		
		int month = calendar.get(calendar.MONTH);
		int day = calendar.get(calendar.DATE);	
		
		int minYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)-100;
		int indexYear = year-minYear;		
		int indexMonth = month;
		int indexDay = day-1;
				
		String strDate = convertToMonth(month)+" "+day+", "+year;
		return strDate;
//		return this.calendar.getYears().get(indexYear).getMonths().get(indexMonth).getDays().get(indexDay).getEntries();

	}
	
	public String convertToMonth(int month) {
		switch(month) {
		case 0: return "Jan";
		case 1: return "Feb";
		case 2: return "Mar";
		case 3: return "Apr";
		case 4: return "May";
		case 5: return "Jun";
		case 6: return "Jul";
		case 7: return "Aug";
		case 8: return "Sept";
		case 9: return "Oct";
		case 10: return "Nov";
		case 11: return "Dec";
		default: return "";
		}
	}
	
	public void toggleDayAgenda() {
		this.isDayView = !this.isDayView;
		
		if(this.isDayView == true) {
			this.pnlAgenda.close();
			this.pnlDay.open();
//			this.pnlFrame = pnlDay;
			this.btnDayView.setIcon(btnDayView.getRolloverIcon());
			this.btnAgendaView.setIcon(btnAgendaView.getDisabledIcon());
		}
		else {
			this.pnlDay.close();
			this.pnlAgenda.open();
//			this.pnlFrame = pnlAgenda;
			this.btnAgendaView.setIcon(btnAgendaView.getRolloverIcon());
			this.btnDayView.setIcon(btnDayView.getDisabledIcon());
		}
	}
	
	public void toggleViewTask() {
		this.isSelectedTask = !this.isSelectedTask;
		this.pnlDay.setSelectedTask(isSelectedTask);
		this.pnlAgenda.setSelectedTask(isSelectedTask);
		
		if(this.isSelectedTask == true) {
			this.btnViewTasks.setIcon(btnViewTasks.getRolloverIcon());
		}
		else {
			this.btnViewTasks.setIcon(btnViewTasks.getDisabledIcon());
		}
	}
	
	
	public void toggleViewEvent() {
		this.isSelectedEvent = !this.isSelectedEvent;
		this.pnlDay.setSelectedEvent(isSelectedEvent);
		this.pnlAgenda.setSelectedEvent(isSelectedEvent);
		
		if(this.isSelectedEvent == true) {
			this.btnViewEvents.setIcon(btnViewEvents.getRolloverIcon());
			
//			Date date = new Date(dlgNewEntry.getCurrentYear()-1900, dlgNewEntry.getCurrentMonth(), dlgNewEntry.getCurrentDay());
//			this.controller.getEntries(date, EntryType.EVENT);
		}
		else {
			this.btnViewEvents.setIcon(btnViewEvents.getDisabledIcon());
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.btnNewEntry) {
			this.dlgNewEntry.open(pnlCalendar.getCurrentMonth(), pnlCalendar.getCurrentDay(), pnlCalendar.getCurrentYear(), pnlDay.getIndexEntry());
		}
		
		if(e.getSource() == this.btnDayView) {
			this.toggleDayAgenda();
			this.pnlDay.refresh(pnlDay.getIndexMonth(), pnlDay.getIndexDay(), pnlDay.getIndexYear());
			this.pnlAgenda.refresh(pnlDay.getIndexMonth(), pnlDay.getIndexDay(), pnlDay.getIndexYear());
		}
		if(e.getSource() == this.btnAgendaView) {
			this.toggleDayAgenda();
			this.pnlDay.refresh(pnlDay.getIndexMonth(), pnlDay.getIndexDay(), pnlDay.getIndexYear());
			this.pnlAgenda.refresh(pnlDay.getIndexMonth(), pnlDay.getIndexDay(), pnlDay.getIndexYear());
		}
		
		if(e.getSource() == this.btnViewEvents) {
			this.toggleViewEvent();
		}
		if(e.getSource() == this.btnViewTasks) {
			this.toggleViewTask();
		}
		if(e.getSource() == this.btnToday) {
			ArrayList<Entry> allEntries = new ArrayList<Entry>();
			for(int i = 0; i < this.controller.getModels().get(0).getCalendar().getYears().size(); i++) {
				for(int j = 0; j < this.controller.getModels().get(0).getCalendar().getYears().get(i).getMonths().size(); j++) {
					for(int k = 0; k < this.controller.getModels().get(0).getCalendar().getYears().get(i).getMonths().get(j).getDays().size(); k++) {
						allEntries.addAll(this.controller.getModels().get(0).getCalendar().getYears().get(i).getMonths().get(j).getDays().get(k).getEntries());
					}
				}
			}
			this.controller.getModels().get(0).ExportFile("entries", allEntries);
			System.exit(0);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getSource() == this.lblCurrentDateBg) {
			setLocation (e.getXOnScreen()-this.posX,e.getYOnScreen()-this.posY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public ProductivityToolController getController() {
		return controller;
	}

	public void setController(ProductivityToolController controller) {
		this.controller = controller;
	}
	
	public static int getTimeIndex(Time time) {
		time.toString();
		switch(time.toString()) {
			case "00:00:00": return 0;
			case "00:30:00": return 1;
			case "01:00:00": return 2;
			case "01:30:00": return 3;
			case "02:00:00": return 4;
			case "02:30:00": return 5;
			
			case "03:00:00": return 6;
			case "03:30:00": return 7;
			case "04:00:00": return 8;
			case "04:30:00": return 9;
			case "05:00:00": return 10;
			case "05:30:00": return 11;
			
			case "06:00:00": return 12;
			case "06:30:00": return 13;
			case "07:00:00": return 14;
			case "07:30:00": return 15;
			case "08:00:00": return 16;
			case "08:30:00": return 17;

			case "09:00:00": return 18;
			case "09:30:00": return 19;
			case "10:00:00": return 20;
			case "10:30:00": return 21;
			case "11:00:00": return 22;
			case "11:30:00": return 23;
			
			case "12:00:00": return 24;
			case "12:30:00": return 25;
			case "13:00:00": return 26;
			case "13:30:00": return 27;
			case "14:00:00": return 28;
			case "14:30:00": return 29;
			
			case "15:00:00": return 30;
			case "15:30:00": return 31;
			case "16:00:00": return 32;
			case "16:30:00": return 33;
			case "17:00:00": return 34;
			case "17:30:00": return 35;
		
			case "18:00:00": return 0;
			case "18:30:00": return 1;
			case "19:00:00": return 2;
			case "19:30:00": return 3;
			case "20:00:00": return 4;
			case "20:30:00": return 5;
			
			case "21:00:00": return 36;
			case "21:30:00": return 37;
			case "22:00:00": return 38;
			case "22:30:00": return 39;
			case "23:00:00": return 40;
			case "23:30:00": return 41;
			
			case "24:00:00": return 42;
			case "24:30:00": return 43;
			case "25:00:00": return 44;
			case "25:30:00": return 45;
			case "26:00:00": return 46;
			case "26:30:00": return 47;
			default: return 0;
		}
	}
}
