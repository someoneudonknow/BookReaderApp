/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ReadingModel;
import models.entityPK.ReadingPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class ReadingDAO extends ResultSetQuery implements DAOInterface<ReadingModel, ReadingPK> {

    public static ReadingDAO getInstance() {
        return new ReadingDAO();
    }

    public void readingEvent(ReadingPK pk) {
        try {
            ResultSet checkRs = this.checkPK(pk);
            if (checkRs.next()) {
                this.updateByPK(pk);
            } else {
                this.insertByPK(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet checkPK(ReadingPK pk) throws SQLException {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT * FROM project1.bookreading WHERE user_id = ? AND chapter_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getChapter_id());
        rs = this.executeQuery(query, queryField);
        return rs;
    }

    public void insertByPK(ReadingPK pk) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "INSERT INTO bookReading (user_id, chapter_id)  VALUES (?, ?)";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getChapter_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(ReadingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateByPK(ReadingPK pk) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "UPDATE  project1.bookReading SET last_read = DEFAULT WHERE user_id = ? AND chapter_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getChapter_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(ReadingModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReadingModel get(ReadingPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ReadingModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ReadingPK pk, ReadingModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ReadingPK pk) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "DELETE FROM project1.booksaved WHERE user_id = ? AND chapter_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getChapter_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ReadingModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
