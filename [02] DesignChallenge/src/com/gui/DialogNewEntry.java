package com.gui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.calendar.TimeRange;
import com.entry.EntryFactory;
import com.view.EntryType;

public class DialogNewEntry extends JDialog implements ChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;

	private Application frmParent;
	private JLabel lblDialogBg;
	
	private JButton btnCreate;
	private JButton btnCancel;
	
	private JLabel lblTitle;
	private JLabel lblDate;
	
	private JTextArea txtDescription;
//	private JScrollPane scrlDescription;
	private JComboBox<Time> cmbTimeStart;
	private JComboBox<Time> cmbTimeEnd;
	
	private ButtonGroup grpEventTask;
	private JRadioButton rdiEvent;
	private JRadioButton rdiTask;
	
	private JTextField txtTimeStart;
	private JTextField txtTimeEnd;
	private JTextField txtDate;
	
	private int currentMonth;
	private int currentDay;
	private int currentYear;
	
	public DialogNewEntry(Application frmParent) {	
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);		
		this.setSize(400, 400);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		this.setBackground(Application.Color_TRANS);
		
		this.frmParent = frmParent;
		this.initComponents();
		this.setSize(this.lblDialogBg.getSize());
		this.setModal(true);
//		this.setBounds(1366/2-(this.getWidth()/2), 768/2-(this.getHeight()/2), this.getWidth(), this.getHeight());
		this.setBounds(562, 290, this.getWidth(), this.getHeight());

		this.add(btnCreate);
		this.add(btnCancel);
		
		this.add(lblTitle);
		this.add(lblDate);
		this.add(rdiEvent);
		this.add(rdiTask);
		this.add(txtDescription);
		this.add(cmbTimeStart);
		this.add(cmbTimeEnd);
		this.add(lblDialogBg);
	}
	
	public void initComponents() {
		Date date = new Date();
		String strYear = date.toString().substring(date.toString().length()-4, date.toString().length());
		int year = Integer.parseInt(strYear);	
//		GregorianCalendar calendar = new GregorianCalendar(year, date.getMonth(), date.getDate());	
		this.currentMonth = date.getMonth();
		this.currentDay = date.getDate();
		this.currentYear = year;
		
		this.lblDialogBg = new JLabel();
		lblDialogBg.setIcon(new ImageIcon("images/Dialog NewEntryBg.png"));
		lblDialogBg.setSize(lblDialogBg.getIcon().getIconWidth(), lblDialogBg.getIcon().getIconHeight());
		
		this.txtDescription = new JTextArea("description");
		txtDescription.setBounds(25, 97, 237, 102);
		txtDescription.setBackground(Application.Color_TRANS);
		txtDescription.setFont(Application.fntBebas18);
		txtDescription.setOpaque(false);
		txtDescription.setForeground(Application.Color_NIGHT);
//		txtDescription.setHorizontalAlignment(JTextField.CENTER);
		txtDescription.setBorder(null);
		txtDescription.setMargin(new Insets(7, 7, 7, 7));
		txtDescription.setLineWrap(true);
		
//		this.scrlDescription = new JScrollPane(txtDescription);		
//		scrlDescription.setBounds(txtDescription.getBounds());
//		scrlDescription.setBackground(txtDescription.getBackground());
//		scrlDescription.setOpaque(false);
//		scrlDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrlDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrlDescription.getVerticalScrollBar().setUI(new CustomScrollBarUI());
//		scrlDescription.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
//		scrlDescription.setBorder(null);
//		scrlDescription.getComponent(0).addMouseMotionListener(this);
//		scrlDescription.getComponent(1).addMouseMotionListener(this);
//		scrlDescription.getComponent(2).addMouseMotionListener(this);
//		scrlDescription.getComponent(0).addMouseWheelListener(this);
//		scrlDescription.getComponent(1).addMouseWheelListener(this);
//		scrlDescription.getComponent(2).addMouseWheelListener(this);
		
		this.cmbTimeStart = new JComboBox<Time>();
		cmbTimeStart.setBackground(Application.Color_BEIGE);
		cmbTimeStart.setForeground(Application.Color_NIGHT);
		cmbTimeStart.setBounds(42, 197, 90, 30);
		cmbTimeStart.setFocusable(false);
		cmbTimeStart.setOpaque(false);
		cmbTimeStart.setBorder(null);
		cmbTimeStart.setFont(Application.fntBebas18);
		cmbTimeStart.setEnabled(true);
		cmbTimeStart.addMouseListener(this);
		cmbTimeStart.setMaximumRowCount(3);
		
		int hours = 0;
		int minutes = 0;
		for(int i = 0; i < 48; i++) {			
			cmbTimeStart.addItem((new Time(hours, minutes, 00)));
			if(minutes == 30) {
				hours += 1;
				minutes = 0;
			}
			else {
				minutes = 30;
			}
		}
		
		this.cmbTimeEnd = new JComboBox<Time>();
		cmbTimeEnd.setBackground(Application.Color_BEIGE);
		cmbTimeEnd.setForeground(Application.Color_NIGHT);
		cmbTimeEnd.setBounds(155, 197, 90, 30);
		cmbTimeEnd.setFocusable(false);
		cmbTimeEnd.setOpaque(false);
		cmbTimeEnd.setBorder(null);
		cmbTimeEnd.setFont(Application.fntBebas18);
		cmbTimeEnd.setEnabled(true);
		cmbTimeEnd.addMouseListener(this);
		cmbTimeEnd.setMaximumRowCount(3);
		
		hours = 0;
		minutes = 0;
		for(int i = 0; i < 48; i++) {			
			cmbTimeEnd.addItem((new Time(hours, minutes, 00)));
			if(minutes == 30) {
				hours += 1;
				minutes = 0;
			}
			else {
				minutes = 30;
			}
		}
		
		
		this.rdiEvent = new JRadioButton();
		rdiEvent.setIcon(new ImageIcon("images/btnRadioEvent_off.png"));
		rdiEvent.setSelectedIcon(new ImageIcon("images/btnRadioEvent_on.png"));
		rdiEvent.setBounds(270, 145, rdiEvent.getIcon().getIconWidth()+5, rdiEvent.getIcon().getIconHeight());
		rdiEvent.addChangeListener(this);
		
		this.rdiTask = new JRadioButton();
		rdiTask.setSelected(true);
		rdiTask.setIcon(new ImageIcon("images/btnRadioTask_off.png"));
		rdiTask.setSelectedIcon(new ImageIcon("images/btnRadioTask_on.png"));
		rdiTask.setBounds(270, 108, rdiTask.getIcon().getIconWidth()+5, rdiTask.getIcon().getIconHeight());
		rdiTask.addChangeListener(this);
		
		this.grpEventTask = new ButtonGroup();
		grpEventTask.add(rdiEvent);
		grpEventTask.add(rdiTask);
		
		this.lblTitle = new JLabel("New Task");
		lblTitle.setBounds(0, 7, lblDialogBg.getWidth(), 30);
		lblTitle.setForeground(Application.Color_NIGHT);
		lblTitle.setFont(Application.fntBebas25);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		
		this.lblDate = new JLabel("INSERT DATE HERE");
		lblDate.setBounds(0, 40, lblDialogBg.getWidth(), 43);
		lblDate.setForeground(Application.Color_PEARL);
		lblDate.setFont(Application.fntBebas25);
		lblDate.setHorizontalAlignment(JLabel.CENTER);
		
		this.btnCancel = new JButton();
		Frame.initButtons(btnCancel, "btnCancel", this);
		
		this.btnCreate = new JButton();
		Frame.initButtons(btnCreate, "btnEnter", this);
		
		btnCancel.setBounds(lblDialogBg.getWidth()-btnCancel.getWidth(), 0, btnCancel.getWidth(), btnCancel.getHeight());
		btnCreate.setBounds(24, 240, btnCreate.getWidth(), btnCreate.getHeight());
	}
	
	public void open(int month, int day, int year) {
		this.currentMonth = month;
		this.currentDay = day;
		this.currentYear = year;
		this.lblDate.setText(convertToMonth(month)+" "+day+", "+year);
		this.setVisible(true);
	}
	
	public void close() {
		this.frmParent.refresh(this.currentMonth, this.currentDay, this.currentYear);
		this.txtDescription.setText("description");
		this.setVisible(false);
		
	}
	
	public String convertToMonth(int month) {
		switch(month) {
		case 0: return "January";
		case 1: return "February";
		case 2: return "March";
		case 3: return "April";
		case 4: return "May";
		case 5: return "June";
		case 6: return "July";
		case 7: return "August";
		case 8: return "September";
		case 9: return "October";
		case 10: return "November";
		case 11: return "December";
		default: return "";
		}
	}
	
	// TODO add overlap check & earlier end than start/vice-versa
	public void create() {
		System.out.println("time start: "+cmbTimeStart.getSelectedItem());
		System.out.println("time end: "+cmbTimeEnd.getSelectedItem());
		
		EntryType type;
		if(this.rdiEvent.isSelected())
			type = EntryType.EVENT;
		else
			type = EntryType.TASK;
		
		Date date = new Date(this.currentYear-1900, this.currentMonth, this.currentDay);
		TimeRange timeRange= new TimeRange((Time)cmbTimeStart.getSelectedItem(), (Time)cmbTimeEnd.getSelectedItem());
		System.out.println("added to date: "+date);
		
		if(frmParent.getController().isValidEntry(date, timeRange)) {
			frmParent.getController().addEntries(type, txtDescription.getText(),date, (Time)cmbTimeStart.getSelectedItem(), (Time)cmbTimeEnd.getSelectedItem());
			this.close();
		}
		else {
			invalid();
		}
	}
	
	public void invalid() {
		lblTitle.setText("ERROR: Invalid Time Range");
		this.repaint();		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.btnCreate) {
			this.create();			
		}
		if(e.getSource() == this.btnCancel) {
			this.close();
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == this.rdiEvent) {
			if(rdiEvent.isSelected()) {
				lblTitle.setText("New Event");
			}
			else {
				lblTitle.setText("New Task");
			}
			this.repaint();
		}
		if(e.getSource() == this.rdiTask) {
			if(rdiEvent.isSelected()) {
				lblTitle.setText("New Event");
			}
			else {
				lblTitle.setText("New Task");
			}
			this.repaint();
		}
	}

	public Application getFrmParent() {
		return frmParent;
	}

	public void setFrmParent(Application frmParent) {
		this.frmParent = frmParent;
	}

	public JLabel getLblDialogBg() {
		return lblDialogBg;
	}

	public void setLblDialogBg(JLabel lblDialogBg) {
		this.lblDialogBg = lblDialogBg;
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}

	public void setBtnCreate(JButton btnCreate) {
		this.btnCreate = btnCreate;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblDate() {
		return lblDate;
	}

	public void setLblDate(JLabel lblDate) {
		this.lblDate = lblDate;
	}

	public JTextArea getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextArea txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JComboBox<Time> getCmbTimeStart() {
		return cmbTimeStart;
	}

	public void setCmbTimeStart(JComboBox<Time> cmbTimeStart) {
		this.cmbTimeStart = cmbTimeStart;
	}

	public JComboBox<Time> getCmbTimeEnd() {
		return cmbTimeEnd;
	}

	public void setCmbTimeEnd(JComboBox<Time> cmbTimeEnd) {
		this.cmbTimeEnd = cmbTimeEnd;
	}

	public ButtonGroup getGrpEventTask() {
		return grpEventTask;
	}

	public void setGrpEventTask(ButtonGroup grpEventTask) {
		this.grpEventTask = grpEventTask;
	}

	public JRadioButton getRdiEvent() {
		return rdiEvent;
	}

	public void setRdiEvent(JRadioButton rdiEvent) {
		this.rdiEvent = rdiEvent;
	}

	public JRadioButton getRdiTask() {
		return rdiTask;
	}

	public void setRdiTask(JRadioButton rdiTask) {
		this.rdiTask = rdiTask;
	}

	public JTextField getTxtTimeStart() {
		return txtTimeStart;
	}

	public void setTxtTimeStart(JTextField txtTimeStart) {
		this.txtTimeStart = txtTimeStart;
	}

	public JTextField getTxtTimeEnd() {
		return txtTimeEnd;
	}

	public void setTxtTimeEnd(JTextField txtTimeEnd) {
		this.txtTimeEnd = txtTimeEnd;
	}

	public JTextField getTxtDate() {
		return txtDate;
	}

	public void setTxtDate(JTextField txtDate) {
		this.txtDate = txtDate;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
}
