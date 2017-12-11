/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import javax.swing.JFrame;

/**
 *
 * @author Thành
 */
public class MainScreen extends JFrame implements IDisplay{

    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_HEIGHT = 600;
  
   

    private DateDisplay dateDisplay = new DateDisplay(this);
    private TableDisplay tableDisplay = new TableDisplay(this);
    
    public MainScreen() {
        setLayout(null);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void display(){
        IDisplay display = dateDisplay;
        display.display();
        display = tableDisplay;
        display.display();
    }
    


}
