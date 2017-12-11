/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Thành
 */
public class TableDisplay implements IDisplay {
    
    private static Icon extendIcon = new ImageIcon("extend.png");
    private static Icon extendIconSelect = new ImageIcon("extendSelect.png");
    private static final int COLUMN_TABLE = 4;
    private static final int ROW_TABLE = 10;
    private static final String[] STRING_TITLE = {"Time", "Content", "Alarm", "Extend"};
    private JLabel[] jLabelsTitles = new JLabel[COLUMN_TABLE]; //display title of table
    private JTextField[] fieldsTime = new JTextField[ROW_TABLE]; //contant time field
    private JTextField[] fieldsContent = new JTextField[ROW_TABLE]; //contant content field
    private JCheckBox[] checkBoxsAlarm = new JCheckBox[ROW_TABLE];
    private JButton[] buttonsExtend = new JButton[ROW_TABLE];
    private Job job;
    
    private JFrame jFrame;
    private ExtendScreen extendScreen;
 
    
    private ExtendScreenListener extendScreenListener = new ExtendScreenListener();
    
    public TableDisplay(JFrame mainScreen) {
        jFrame = mainScreen;
        extendScreen = new ExtendScreen(jFrame);
      
        extendScreen.addWindowListener(extendScreenListener);
        int heightRowTable = 30;
        int boundUpperTable = 120;
        int distanceRows = 7;

        //set for text field Time
        int boundLeftTime = 50;
        int widthTime = 70;
        jLabelsTitles[0] = new JLabel(STRING_TITLE[0], SwingConstants.CENTER);
        jLabelsTitles[0].setBounds(boundLeftTime, boundUpperTable - heightRowTable - distanceRows,
                widthTime, heightRowTable);
        mainScreen.add(jLabelsTitles[0]);
        for (int i = 0; i < ROW_TABLE; ++i) {
            fieldsTime[i] = new JTextField();
            fieldsTime[i].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char caracter = e.getKeyChar();
                    if (((caracter < '0') || (caracter > '9'))
                            && (caracter != '\b') && (caracter != ':')) {
                        e.consume();
                    }
                }
            }
            );
            mainScreen.add(fieldsTime[i]);
            fieldsTime[i].setBounds(boundLeftTime, boundUpperTable + (distanceRows + heightRowTable) * (i),
                    widthTime, heightRowTable);
        }

        //set for text field content 
        int widthContentColumn = 300;
        int boundLeftContent = boundLeftTime + widthTime + 30;
        jLabelsTitles[1] = new JLabel(STRING_TITLE[1], SwingConstants.CENTER);
        jLabelsTitles[1].setBounds(boundLeftContent, boundUpperTable - heightRowTable - distanceRows,
                widthContentColumn, heightRowTable);
        mainScreen.add(jLabelsTitles[1]);
        for (int i = 0; i < ROW_TABLE; ++i) {
            fieldsContent[i] = new JTextField();
            mainScreen.add(fieldsContent[i]);
            fieldsContent[i].setBounds(boundLeftContent, boundUpperTable + (distanceRows + heightRowTable) * (i),
                    widthContentColumn, heightRowTable);
        }

        //setfor checkbox alarm
        int boundLeftAlarm = boundLeftContent + widthContentColumn + 15;
        int widthAlarm = 20;
        int devi = 5; //sai so chinh lai cho dep
        jLabelsTitles[2] = new JLabel(STRING_TITLE[2], SwingConstants.CENTER);
        jLabelsTitles[2].setBounds(boundLeftAlarm - 5, boundUpperTable - heightRowTable - distanceRows,
                widthAlarm * 2, heightRowTable);
        mainScreen.add(jLabelsTitles[2]);
        for (int i = 0; i < ROW_TABLE; ++i) {
            checkBoxsAlarm[i] = new JCheckBox();
            mainScreen.add(checkBoxsAlarm[i]);
            checkBoxsAlarm[i].setBounds(boundLeftAlarm, boundUpperTable + devi + (distanceRows + heightRowTable) * (i),
                    widthAlarm, widthAlarm);
        }

        //set for extend button
        int boundLeftExtend = boundLeftAlarm + widthAlarm + 30;
        int widthExtend = 40;
        
        jLabelsTitles[3] = new JLabel(STRING_TITLE[3], SwingConstants.CENTER);
        jLabelsTitles[3].setBounds(boundLeftExtend, boundUpperTable - heightRowTable - distanceRows,
                widthExtend, heightRowTable);
        mainScreen.add(jLabelsTitles[3]);
        for (int i = 0; i < ROW_TABLE; ++i) {
            buttonsExtend[i] = new JButton(extendIcon);
            mainScreen.add(buttonsExtend[i]);
            buttonsExtend[i].addActionListener(new ExtendButtonListener(buttonsExtend[i]));
            buttonsExtend[i].setBounds(boundLeftExtend, boundUpperTable + (distanceRows + heightRowTable) * (i),
                    widthExtend, heightRowTable);
        }
        
       
    }
    
    private class ExtendButtonListener implements ActionListener {
        
        private JButton button;
        
        public ExtendButtonListener(JButton jButton) {
            button = jButton;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            button.setIcon(extendIconSelect);
            extendScreenListener.setButton(button);
            extendScreen.display();
        }
        
    }
    
    private class ExtendScreenListener extends WindowAdapter {
        
        private JButton button;
        
        @Override
        public void windowClosing(WindowEvent e) {
            //set extend for sub job
            if (button != null) {
                button.setIcon(extendIcon);
                
            }
        }
        
        public void setButton(JButton jButton) {
            button = jButton;
        }
        
    }
    
    @Override
    public void display() {
        
    }
    
    public static void main(String[]args){
        TimeManagement.main(args);
    }
    
}
