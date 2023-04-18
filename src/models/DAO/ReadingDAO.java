/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public void deleteReadHistory(int book_id, int user_id) throws SQLException {
        ArrayList<Integer> chapterReadByUser = this.getReadChapterOfBook(book_id, user_id);
        System.out.println(chapterReadByUser);
        for (int chapter : chapterReadByUser) {
            this.delete(new ReadingPK(user_id, chapter));
        }
    }
    public void deleteAllByUserID(int user_id) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "DELETE FROM project1.bookreading WHERE user_id = ?";
        queryField.add(user_id);
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Integer> getReadChapterOfBook(int book_id, int user_id) throws SQLException {
        ResultSet rs = null;
        ArrayList<Integer> chapterReadByUser = new ArrayList<>();
        String query = "SELECT br.chapter_id FROM project1.bookreading as br\n"
                + "JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id\n"
                + "WHERE br.user_id = ? AND bc.book_id = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(user_id);
        queryField.add(book_id);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            chapterReadByUser.add(rs.getInt("chapter_id"));
        }
        return chapterReadByUser;
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

     public void deleteByChapterId(int chapterId) {
        String query = "DELETE FROM project1.bookReading WHERE bookReading.chapter_id = " + chapterId;
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
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
        String query = "DELETE FROM project1.bookreading WHERE user_id = ? AND chapter_id = ?";
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
