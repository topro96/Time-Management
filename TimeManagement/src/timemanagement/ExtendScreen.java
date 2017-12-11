/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Thành
 */
public class ExtendScreen extends JDialog{
    
    private static final int HEIGHT_SCREEN = 300;
    private static final int WIDTH_SCREEN = 300;
    
    private JTextArea extendText = new JTextArea(10,20);
    private JScrollPane textScroll = new JScrollPane(extendText);
    
    public void display(){
        this.setVisible(true);
    }
    
    public ExtendScreen(JFrame jFrame){
        super(jFrame, "");
        this.getContentPane().setLayout(new FlowLayout());  
        extendText.setLineWrap(true);
        extendText.setWrapStyleWord(true);
     // extendText.setBounds(20, 20, 200, 100);
        
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(textScroll);
        this.getContentPane().add(textScroll);
        this.setBounds(100, 100, 300, 300);
    }
    
     public void setExtendText(String text){
        extendText.setText(text);
    }
    
    public String getExtendText(){
        return extendText.getText();
    }
   
}
