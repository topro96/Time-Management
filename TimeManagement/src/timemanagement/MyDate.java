/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement;

import java.time.LocalDate;

/**
 *
 * @author Thành
 */
public final class MyDate {

    public static final int YEAR_MIN = 2000;

    private int day;
    private int month;
    private int year;

    public MyDate() {
        this(0, 0, 0);
    }

    public MyDate(int theDay, int theMonth, int theYear) {
        setDay(theDay);
        setMonth(theMonth);
        setYear(theYear);
    }

    public MyDate(MyDate theDate) {
        setDay(theDate.getDay());
        setMonth(theDate.getMonth());
        setYear(theDate.getYear());
    }
    
    public MyDate(LocalDate localDate){
        setDay(localDate.getDayOfMonth());
        setMonth(localDate.getMonthValue());
        setYear(localDate.getYear());
    }

    public static MyDate nextDay(MyDate date) {

        MyDate next = new MyDate(date);

        switch (next.getMonth()) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (next.getDay() == 30) {
                    next.setMonth(next.getMonth() + 1);
                    next.setDay(1);
                } else {
                    next.setDay(next.getDay() + 1);
                }   break;
            case 2:
                if (next.getYear() % 400 == 0
                        || (next.getYear() % 4 == 0 && next.getYear() % 100 != 0)) {
                    if (next.getDay() == 29) {
                        next.setMonth(next.getMonth() + 1);
                        next.setDay(1);
                    } else {
                        next.setDay(next.getDay() + 1);
                    }
                } else {
                    if (next.getDay() == 28) {
                        next.setMonth(next.getMonth() + 1);
                        next.setDay(1);
                    } else {
                        next.setDay(next.getDay() + 1);
                    }
                }   break;
            default:
                if (next.getDay() == 31) {
                    if (next.getMonth() == 12) {
                        next.setYear(next.getYear() + 1);
                        next.setDay(1);
                        next.setMonth(1);
                    } else {
                        next.setMonth(next.getMonth() + 1);
                        next.setDay(1);
                    }
                } else {
                    next.setDay(next.getDay() + 1);
                }   break;
        }

        return next;
    }

    public static MyDate backDay(MyDate date){
          MyDate back = new MyDate(date);

        switch (back.getMonth()) {
            case 5:
            case 7:
            case 10:
            case 12:
                if (back.getDay() == 1) {
                    back.setMonth(back.getMonth() - 1);
                    back.setDay(30);
                } else {
                    back.setDay(back.getDay() - 1);
                }   break;
            case 3:
                if (back.getYear() % 400 == 0 //nam nhuan
                        || (back.getYear() % 4 == 0 && back.getYear() % 100 != 0)) {
                    if (back.getDay() == 1) {
                        back.setMonth(2);
                        back.setDay(29);
                    } else {
                        back.setDay(back.getDay() - 1);
                    }
                } else { //nam khong nhuan
                    if (back.getDay() == 1) {
                        back.setMonth(2);
                        back.setDay(28);
                    } else {
                        back.setDay(back.getDay() - 1);
                    }
                }   break;
            default:
                if (back.getDay() == 1) {
                    if (back.getMonth() == 1) {
                        back.setYear(back.getYear() - 1);
                        back.setDay(31);
                        back.setMonth(12);
                    } else {
                        back.setMonth(back.getMonth() - 1);
                        back.setDay(31);
                    }
                } else {
                    back.setDay(back.getDay() - 1);
                }   break;
        }

        return back;
    }
    
    public static MyDate nextMonth(MyDate date){
         MyDate next = new MyDate(date);
         next.setDay(1);
         if(next.getMonth() == 12){
             next.setMonth(1);
             next.setYear(next.getYear() + 1);
         }else{
              next.setMonth(next.getMonth() +1);
         }
         return next;
    }
    
     public static MyDate backMonth(MyDate date){
         MyDate back = new MyDate(date);
         back.setDay(1);
         if(back.getMonth() == 1){
             back.setMonth(12);
             back.setYear(back.getYear() - 1);
         }else{
              back.setMonth(back.getMonth() -1);
         }
         return back;
    }
    
    public static MyDate backYear(MyDate date){
         MyDate back = new MyDate(date);
         back.setDay(1);
         back.setYear(back.getYear() -1);
         return back;
    }
     
     public static MyDate nextYear(MyDate date){
         MyDate next = new MyDate(date);
         next.setDay(1);
         next.setYear(next.getYear() + 1);
         return next;
    }
    
    public String toString() {
        return String.format("%d/%d/%d", day, month, year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
