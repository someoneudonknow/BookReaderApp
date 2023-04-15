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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                System.out.println("This is already the first chapter");
                return currentChapter;
            } else {
                queryField.add(currentSerial - 1);
            }
        } else if (option.equals("next")) {
            if (currentSerial
                    == BookDAO.getInstance().getFirstLastChapter(currentChapter.getBook_id(), "last").getSerial()) {
                System.out.println("This is already the last chapter");
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
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ?";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void delete(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChapterModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
