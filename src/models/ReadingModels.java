/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ReadingModels {
    private int user_id;
    private int chapter_id;
    private String time_reading;
    private SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public ReadingModels(int user_id, int chapter_id) {
        this.user_id = user_id;
        this.chapter_id = chapter_id;
        this.time_reading = time.format(new Date());
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getTime_reading() {
        return time_reading;
    }

    public void setTime_reading(String time_reading) {
        this.time_reading = time_reading;
    }

    public SimpleDateFormat getTime() {
        return time;
    }

    public void setTime(SimpleDateFormat time) {
        this.time = time;
    }
    
    
}
