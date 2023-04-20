/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.ChapterModel;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class ChapterDAO extends ResultSetQuery implements DAOInterface<ChapterModel, Integer> {

    public static ChapterDAO getInstance() {
        return new ChapterDAO();
    }

    public ChapterModel getSelectedChapter(int bookID, int selectedItem) throws SQLException {
        ChapterModel selectedChapter = new ChapterModel();
        ResultSet rs = null;
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? AND chapter_serial = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(bookID);
        queryField.add(selectedItem);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel.populateChapterModel(rs, selectedChapter);
        }
        return selectedChapter;

    }

    public ChapterModel getPreivousNextChapter(ChapterModel currentChapter, String option) throws SQLException {
        ChapterModel preivousNextChapter = new ChapterModel();
        ResultSet rs = null;
        int currentSerial = currentChapter.getSerial();
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? AND chapter_serial = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentChapter.getBook_id());
        if (option.equals("previous")) {
            if (currentSerial == 1) {
                JOptionPane.showMessageDialog(null, "This is already the first chapter");
                return currentChapter;
            } else {
                queryField.add(currentSerial - 1);
            }
        } else if (option.equals("next")) {
            if (currentSerial
                    == BookDAO.getInstance().getFirstLastChapter(currentChapter.getBook_id(), "last").getSerial()) {
                JOptionPane.showMessageDialog(null, "This is already the last chapter");
                return currentChapter;
            }
            queryField.add(currentSerial + 1);
        }
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel.populateChapterModel(rs, preivousNextChapter);
        }

        return preivousNextChapter;
    }

    public void increaseView(ChapterModel data) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "UPDATE project1.bookchapter SET chapter_view = ? WHERE chapter_id = ?";
        queryField.add(data.getChapter_view() + 1);
        queryField.add(data.getId());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ChapterModel> getAllChapterFromBook(Integer pk) throws SQLException {
        ResultSet rs = null;
        ArrayList<ChapterModel> chapterList = new ArrayList<>();
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? ORDER BY bookchapter.chapter_serial";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(pk);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel chapter = new ChapterModel();
            ChapterModel.populateChapterModel(rs, chapter);
            chapterList.add(chapter);
        }

        return chapterList;
    }
    @Override
    public void insert(ChapterModel data) {
        String query = "INSERT INTO bookChapter (chapter_title, chapter_serial, chapter_document, book_id) VALUES(?,?,?,?)";

        DB db = new DB();
        Connection con = db.getConnection();
        try {
            int lastestChap = this.getLastestChapterSerial(data.getBook_id());
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, data.getTitle());
            pst.setInt(2, data.getSerial());
            pst.setString(3, data.getDocument());
            pst.setInt(4, data.getBook_id());

            if (data.getSerial() <= lastestChap) {
                this.updateChaperSerialWhenInsert(data.getSerial(), data.getBook_id());
            }
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    public void insert(List<ChapterModel> chapters) {
        String query = "INSERT INTO bookChapter (chapter_title, chapter_serial, chapter_document, book_id) VALUES";

//        DB db = new DB();
//        Connection con = db.getConnection();
//        try {
//            int lastestChap = this.getLastestChapterSerial(data.getBook_id());
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, data.getTitle());
//            pst.setInt(2, data.getSerial());
//            pst.setString(3, data.getDocument());
//            pst.setInt(4, data.getBook_id());
//
//            if (data.getSerial() <= lastestChap) {
//                this.updateChaperSerialWhenInsert(data.getSerial(), data.getBook_id());
//            }
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            db.closeConnection(con);
//        }
    }
    
    public void updateChaperSerialWhenInsert(int baseOnSerial, int bookId) {
        String query = "UPDATE bookChapter SET bookChapter.chapter_serial = bookChapter.chapter_serial + 1 WHERE bookChapter.book_id = " + bookId + " AND bookChapter.chapter_serial >= " + baseOnSerial;
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(con);
        }
    }

    @Override
    public void delete(Integer id) {
        
    }
    
    public void deleteAllChapterFromOneBook(int bookId) {
        String query = "DELETE FROM bookChapter WHERE bookChapter.book_id = " + bookId;
        
        DB db = new DB();
        Connection con = db.getConnection();
        try {
            ArrayList<ChapterModel> chapters = this.getAllChapterFromBook(bookId);
            for(ChapterModel i: chapters) {
                ReadingDAO.getInstance().deleteByChapterId(i.getId());
            }
            
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            db.closeConnection(con);
        }
    }
    
    public void deleteChapterAndUpdateSerials(int chapId, int bookId, int chapSerial) {
        String query = "DELETE FROM project1.bookchapter WHERE bookchapter.chapter_id = " + chapId + " AND bookchapter.book_id = " + bookId;
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            this.updateChapterSerialWhenDelete(chapSerial, bookId);
            ReadingDAO rdDAO = new ReadingDAO();
            rdDAO.deleteByChapterId(chapId);
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    public void updateChapterSerialWhenDelete(int baseOnSerial, int bookId) {
        String query = "UPDATE bookchapter SET bookchapter.chapter_serial = bookchapter.chapter_serial - 1 WHERE bookchapter.book_id = "
                + bookId + " AND bookchapter.chapter_serial > "
                + baseOnSerial;
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(con);
        }
    }

    public int getLastestChapterSerial(int bookId) {
        String query = "SELECT MAX(chapter_serial) as lastest_chapter_serial FROM bookChapter WHERE bookChapter.book_id = " + bookId;
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int lastesChapterSerial = -1;
            System.out.println(query);
            while (rs.next()) {
                lastesChapterSerial = rs.getInt("lastest_chapter_serial");
            }
            return lastesChapterSerial;
        } catch (SQLException ex) {
            return -1;
        } finally {
            db.closeConnection(con);
        }
    }

    @Override
    public ChapterModel get(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChapterModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Integer pk, ChapterModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChapterModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
