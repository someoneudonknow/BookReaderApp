/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import models.entityPK.SavedPK;

/**
 *
 * @author ADMIN
 */
public class SavedModel extends SavedPK {
    private Timestamp savedTime;
    public SavedModel(int user_id, int book_id) {
        super(user_id, book_id);
    }

    public SavedModel() {
    }

    public void setSavedTime(Timestamp savedTime) {
        this.savedTime = savedTime;
    }

    public Timestamp getSavedTime() {
        return savedTime;
    }
    
    public static void populateSavedModel(ResultSet rs, SavedModel saved) throws SQLException {
        saved.setUser_id(rs.getInt("user_id"));
        saved.setBook_id(rs.getInt("book_id"));
        saved.setSavedTime(rs.getTimestamp("saved_date"));
        System.out.println(saved.getSavedTime());
    }
}
