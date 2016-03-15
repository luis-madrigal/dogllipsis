package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import com.controller.ProductivityToolController;
import com.view.AgendaView;
import com.view.CalendarView;
import com.view.DayView;
import com.view.EntryView;

public class Application extends JFrame implements MouseListener {
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
	private DayView viewDay;
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
		this.viewDay = new DayView();
		this.viewAgenda = new AgendaView();
	
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
	}
	
	public void initComponents() {
		UIManager.put("ToolTip.background", new Color(45,45, 45));
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	
		this.controller = new ProductivityToolController();
		this.dlgNewEntry = new DialogNewEntry();
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
		this.lblCurrentDateTxt = new JLabel("MAR 03, 2016");
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
		
		this.pnlCalendar = new FrameCalendar(lblCalendarBg.getWidth(), lblCalendarBg.getHeight(), this.viewCalendar);
		pnlCalendar.setBounds(lblCalendarBg.getBounds());
		
		
		this.pnlDay = new FrameDay(MAX_WIDTH, MAX_HEIGHT, viewDay, this.controller);
		this.pnlAgenda = new FrameAgenda(MAX_WIDTH, MAX_HEIGHT, viewAgenda, this.controller);
//		this.pnlFrame = this.pnlDay;
		this.pnlAgenda.close();
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
		
		if(this.isSelectedTask == true) {
			this.btnViewTasks.setIcon(btnViewTasks.getRolloverIcon());
		}
		else {
			this.btnViewTasks.setIcon(btnViewTasks.getDisabledIcon());
		}
	}
	
	public void toggleViewEvent() {
		this.isSelectedEvent = !this.isSelectedEvent;
		
		if(this.isSelectedEvent == true) {
			this.btnViewEvents.setIcon(btnViewEvents.getRolloverIcon());
		}
		else {
			this.btnViewEvents.setIcon(btnViewEvents.getDisabledIcon());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.btnNewEntry) {
			this.dlgNewEntry.setVisible(true);
		}
		
		if(e.getSource() == this.btnDayView) {
			this.toggleDayAgenda();
		}
		if(e.getSource() == this.btnAgendaView) {
			this.toggleDayAgenda();
		}
		
		if(e.getSource() == this.btnViewEvents) {
			this.toggleViewEvent();
		}
		if(e.getSource() == this.btnViewTasks) {
			this.toggleViewTask();
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
