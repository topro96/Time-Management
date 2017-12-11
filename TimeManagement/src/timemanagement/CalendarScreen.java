/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.Icon;
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
public class CalendarScreen extends JDialog {

    private static final int HEIGHT_FRAME = 300;
    private static final int WIDTH_FRAME = 300;

    private static Icon iConNextMonth = new ImageIcon("nextMonth.png");
    private static Icon iConBackMonth = new ImageIcon("backMonth.png");
    private static Icon iConNextYear = new ImageIcon("nextYear.jpg");
    private static Icon iConBackYear = new ImageIcon("backYear.jpg");

    private JButton buttonNextMonth = new JButton(iConNextMonth);
    private JButton buttonBackMonth = new JButton(iConBackMonth);
    private JButton buttonNextYear = new JButton(iConNextYear);
    private JButton buttonBackYear = new JButton(iConBackYear);

    private static final int COUNT_DAY_BUTTON = 42;
    private static final int MY_DAY_OF_WEEK = 7;
    private static final String[] WEEK_STRING = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
    private JLabel[] labelsWeek = new JLabel[MY_DAY_OF_WEEK];
    private JButton[] buttonsDay = new JButton[COUNT_DAY_BUTTON];

    private JLabel labelMonth = new JLabel("", SwingConstants.CENTER);
    private JLabel labelYear = new JLabel("", SwingConstants.CENTER);

    private MyDate dateCurrent = new MyDate();

    private MyDate dateReturn;
    
    private int indexDateLocal; //determine index day local in daysbutton 

    public CalendarScreen(JFrame frame) {
        super(frame);
        this.setBackground(Color.white);
        this.setSize(HEIGHT_FRAME, WIDTH_FRAME);
        this.setLayout(null);
        int height = 80;
        int width = 30;

        buttonBackYear.setBounds(10, 10, 40, 30);

        this.add(buttonBackYear);
        buttonBackMonth.setBounds(50, 10, 30, 30);
        this.add(buttonBackMonth);

        buttonNextYear.setBounds(WIDTH_FRAME - 70, 10, 40, 30);
        this.add(buttonNextYear);
        buttonNextMonth.setBounds(WIDTH_FRAME - 100, 10, 30, 30);
        this.add(buttonNextMonth);

        ButtonsArrowListener arrowListener = new ButtonsArrowListener();

        buttonBackMonth.addActionListener(arrowListener);
        buttonNextMonth.addActionListener(arrowListener);
        buttonBackYear.addActionListener(arrowListener);
        buttonNextYear.addActionListener(arrowListener);

        //month
        labelMonth.setBounds(WIDTH_FRAME / 2 - 50, 10, 30, 30);
        labelMonth.setOpaque(true);
        labelMonth.setBackground(Color.BLUE.brighter());
        this.add(labelMonth);

        labelYear.setBounds(WIDTH_FRAME / 2 - 20, 10, 50, 30);
        labelYear.setOpaque(true);
        labelYear.setBackground(Color.LIGHT_GRAY.brighter());
        this.add(labelYear);

        //title week
        for (int i = 0; i < MY_DAY_OF_WEEK; ++i) {
            labelsWeek[i] = new JLabel(WEEK_STRING[i], SwingConstants.CENTER);
            labelsWeek[i].setBounds(40 + width * i, height - width, width, width);
            this.add(labelsWeek[i]);
        }

        ButtonsDayListener dayListener = new ButtonsDayListener(this);

        //days of month
        for (int i = 0; i < COUNT_DAY_BUTTON; ++i) {
            buttonsDay[i] = new JButton();
            if (i % 7 == 0 && i != 0) {
                height += width;
            }
            buttonsDay[i].setMargin(new Insets(0, 0, 0, 0));
            buttonsDay[i].setBounds(40 + width * (i % 7), height, width, width);
            buttonsDay[i].addActionListener(dayListener);
            this.add(buttonsDay[i]);
        }

        MyDate dateLocal = new MyDate(LocalDate.now());
        Calendar cal = getCalendar(1, dateLocal.getMonth(), dateLocal.getYear());
        indexDateLocal = cal.get(Calendar.DAY_OF_WEEK) + dateLocal.getDay() - 2;
        
        this.addWindowFocusListener(new CalendarListenner());
        
    }

    public void display() {
        this.setVisible(true);
        MyDate dateLocal = new MyDate(LocalDate.now());
        dateCurrent.setDay(1);
        dateCurrent.setMonth(dateLocal.getMonth());
        dateCurrent.setYear(dateLocal.getYear());
        printMonthForm();
    }

    private Calendar getCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        if (month == 1) {
            cal.set(year, 0, day);
        } else {
            cal.set(year, month - 1, day);
        }
        return cal;
    }

    public void printMonthForm() {
        labelMonth.setText(String.valueOf(dateCurrent.getMonth()));
        labelYear.setText(String.valueOf(dateCurrent.getYear()));
        Calendar cal = getCalendar(1, dateCurrent.getMonth(), dateCurrent.getYear());

        int index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < index; ++i) {
            buttonsDay[i].setText("");
        }

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = index, day = 1; day <= daysInMonth; ++i, ++day) {
            buttonsDay[i].setText(String.valueOf(day));
        }

        for (int i = daysInMonth + index; i < COUNT_DAY_BUTTON; ++i) {
            buttonsDay[i].setText("");
        }

        MyDate dateLocal = new MyDate(LocalDate.now());
        if (dateLocal.getMonth() == dateCurrent.getMonth()
                && dateLocal.getYear() == dateCurrent.getYear()) {
            buttonsDay[indexDateLocal].setBackground(Color.LIGHT_GRAY);
        } else {
            buttonsDay[indexDateLocal].setBackground(null);
        }
    }

    private class ButtonsArrowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonBackMonth) {
                dateCurrent = MyDate.backMonth(dateCurrent);
            } else if (e.getSource() == buttonNextMonth) {
                dateCurrent = MyDate.nextMonth(dateCurrent);
            } else if (e.getSource() == buttonNextYear) {
                dateCurrent = MyDate.nextYear(dateCurrent);
            } else if (e.getSource() == buttonBackYear) {
                dateCurrent = MyDate.backYear(dateCurrent);
            }
            printMonthForm();
        }

    }

    private class ButtonsDayListener implements ActionListener {
        JDialog dialog;
        @Override
        public void actionPerformed(ActionEvent e) {
            
            JButton button = (JButton)e.getSource();
            if (!"".equals(button.getText())) {
                dateCurrent.setDay(Integer.parseInt(button.getText()));
                dateReturn = dateCurrent;
                dialog.setVisible(false);
            }
        }

        public ButtonsDayListener(JDialog jd) {
            dialog = jd;
        }

    }

    private class CalendarListenner extends WindowAdapter {
        public void windowClosed(WindowEvent e) {
            dateReturn = null;
        }
    }

    public MyDate getDateReturn() {
        return dateReturn;
    }
    
    

}
