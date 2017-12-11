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
public class SubJob {

    private String subContent;
    private Time time;
    private String extend;
    private boolean Alarm;

    public SubJob() throws Exception{
       this(new String(),new Time(), new String(), false);
    }
    
    public SubJob(String theSubContent, Time theTime, String theExtend, boolean doesIsAlarm) {
        setExtend(theExtend);
        setAlarm(doesIsAlarm);
        setSubContent(theSubContent);
        setTime(theTime);
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public boolean isAlarm() {
        return Alarm;
    }

    public void setAlarm(boolean Alarm) {
        this.Alarm = Alarm;
    }

    
    
}
