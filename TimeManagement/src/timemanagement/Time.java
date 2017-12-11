/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

/**
 *
 * @author Thành
 */
public class Time {
    
    
    private int hour;
    private int minute;
    private int second;

    public Time()throws Exception{
        this(0,0,0);
    }
    
    public Time(int theHour, int theMinute, int theSecond) throws Exception{
        setHour(theHour);
        setMinute(theMinute);
        setSecond(theSecond);
    }
    
    public int getHour() {
        return hour;
    }

    public void setHour(int theHour) throws Exception{
        if(theHour >= 0 && theHour < 24){
           hour = theHour;
       }else{
           throw new Exception();
       }
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int theMinute) {
        this.minute = theMinute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int theSecond) {
        this.second = theSecond;
    }
    
    
    
    
}
