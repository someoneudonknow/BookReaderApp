/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.entityPK.ReadingPK;

/**
 *
 * @author ADMIN
 */
public class ReadingModel extends ReadingPK {

    private Timestamp lastRead;


    public ReadingModel() {
    }

    public ReadingModel(int user_id, int chapter_id, Timestamp lastRead) {
        super(user_id, chapter_id);
        this.lastRead = lastRead;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.lastRead = lastRead;
    }

    public Timestamp getUpdateTime() {
        return lastRead;
    }
   

}
