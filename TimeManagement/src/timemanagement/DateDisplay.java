/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Thành
 */
public class DateDisplay implements IDisplay, IGetlDateDisplay {

    private static final int COUNT_DAY_DISPLAY = 5; //number of day display 
    private JButton[] jButtonsDay = new JButton[COUNT_DAY_DISPLAY];
    private JLabel jLabelMonth = new JLabel("", SwingConstants.CENTER); //display month
    private JLabel jLabelYear = new JLabel("", SwingConstants.CENTER); //display year
    private JFrame jFrame; //refering Main Screen
    private MyDate[] datesDisplay = new MyDate[COUNT_DAY_DISPLAY];

    private JButton jButtonNextDay = new JButton(new ImageIcon("next.png"));
    private JButton jButtonBackDay = new JButton(new ImageIcon("back.png"));

    private MyDate dateCurrent;
    private int indexDay;

    private CalendarScreen calendarScreen;
    private JButton buttonCalender = new JButton(new ImageIcon("calendar.png"));

    public DateDisplay(JFrame mainScreen) {
        jFrame = mainScreen;

        calendarScreen = new CalendarScreen(mainScreen);
        //set day display button
        for (int i = 0; i < COUNT_DAY_DISPLAY; ++i) {
            jButtonsDay[i] = new JButton();
            jButtonsDay[i].addActionListener(new DateButtonListener(this));
            jFrame.add(jButtonsDay[i]);
            jButtonsDay[i].setBounds((MainScreen.FRAME_WIDTH - 200) / 2 + 50 * i, 0, 50, 40);
        }

        jButtonNextDay.setBounds((MainScreen.FRAME_WIDTH - 200) / 2 + 250, 0, 40, 40);
        jFrame.add(jButtonNextDay);
        jButtonNextDay.addActionListener(new NextButtonListener());

        jButtonBackDay.setBounds((MainScreen.FRAME_WIDTH - 200) / 2 - 40, 0, 40, 40);
        jFrame.add(jButtonBackDay);
        jButtonBackDay.addActionListener(new BackButtonListener());

        jLabelMonth.setBounds(MainScreen.FRAME_WIDTH / 2, 40, 50, 40);
        jFrame.add(jLabelMonth);
        jLabelYear.setBounds(MainScreen.FRAME_WIDTH / 2, 60, 50, 40);
        jFrame.add(jLabelYear);

        datesDisplay = new MyDate[COUNT_DAY_DISPLAY];
        MyDate dateLocal = new MyDate(LocalDate.now());
        dateCurrent = new MyDate(dateLocal);
        indexDay = COUNT_DAY_DISPLAY / 2;
        jButtonsDay[indexDay].setBackground(Color.LIGHT_GRAY.brighter());
        setDisplayDates(dateCurrent);

        buttonCalender.setBounds(600, 0, 40, 40);
        buttonCalender.addActionListener(new ButtonCalendarListener());
        mainScreen.add(buttonCalender);

        calendarScreen.addComponentListener(new ComponentAdapter() {

            public void componentHidden(ComponentEvent e) {
                if (calendarScreen.getDateReturn() != null) {
                    dateCurrent = calendarScreen.getDateReturn();
                    display();
                }
            }
        }
        );

    }

    @Override
    public void display() {
        setDisplayDates(dateCurrent);
        for (int i = 0; i < COUNT_DAY_DISPLAY; ++i) {
            jButtonsDay[i].setText(String.valueOf(datesDisplay[i].getDay()));
        }
        jLabelMonth.setText(String.valueOf(dateCurrent.getMonth()));
        jLabelYear.setText(String.valueOf(dateCurrent.getYear()));
    }

    @Override
    public void setDisplayDates(MyDate dateCurrent) {

        datesDisplay[indexDay] = new MyDate(dateCurrent);
        for (int i = indexDay; i > 0; --i) {
            datesDisplay[i - 1] = MyDate.backDay(datesDisplay[i]);
        }
        for (int i = indexDay; i < COUNT_DAY_DISPLAY - 1; ++i) {
            datesDisplay[i + 1] = MyDate.nextDay(datesDisplay[i]);
        }
    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dateCurrent = MyDate.backDay(dateCurrent);
            setDisplayDates(dateCurrent);
            display();
        }
    }

    private class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dateCurrent = MyDate.nextDay(dateCurrent);
            setDisplayDates(dateCurrent);
            display();
        }
    }

    private class DateButtonListener implements ActionListener {

        DateDisplay dateDisplay;

        public DateButtonListener(DateDisplay display) {
            dateDisplay = display;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < COUNT_DAY_DISPLAY; ++i) {
                if (e.getSource() == dateDisplay.jButtonsDay[i]) {
                    jButtonsDay[i].setBackground(Color.LIGHT_GRAY.brighter());
                    dateCurrent = dateDisplay.datesDisplay[i];
                    indexDay = i;
                    display();
                } else {
                    jButtonsDay[i].setBackground(null);
                }
            }
        }
    }

    public MyDate getDateCurrent() {
        return dateCurrent;
    }

    private class ButtonCalendarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            calendarScreen.display();
        }
    }

    public void setDateCurrent(MyDate dateCurrent) {
        this.dateCurrent = dateCurrent;
    }

}
