/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.Statement;
import java.sql.PreparedStatement;
import database.DB;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Blob;
import java.text.DecimalFormat;
import javax.sql.rowset.serial.SerialBlob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BookModel;
import models.ChapterModel;
import models.UserModel;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class BookDAO extends ResultSetQuery implements DAOInterface<BookModel, Integer> {

    public BookDAO() {

    }

    public static BookDAO getInstance() {
        return new BookDAO();
    }

    public ChapterModel getFirstLastChapter(Integer book_id, String option) throws SQLException {
        ResultSet rs = null;
        ChapterModel chapter = new ChapterModel();
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? AND chapter_serial = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(book_id);
        if (option.equals("first")) {
            queryField.add(1);
        } else if (option.equals("last")){
            query = "SELECT * FROM project1.bookchapter WHERE book_id = ? ORDER BY chapter_serial DESC LIMIT 1";
        }

        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel.populateChapterModel(rs, chapter);
        }
        return chapter;
    }

    public ArrayList<BookModel> getTop5() throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        String query = "SELECT bi.*,IFNULL(COUNT(bs.user_id), 0) as savedNum FROM project1.booksaved as bs RIGHT JOIN bookinfo as bi\n"
                + "ON bs.book_id = bi.book_id GROUP BY book_id ORDER BY COUNT(bi.book_id) DESC LIMIT 5;";
        ArrayList<Object> queryField = new ArrayList<>();
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }
        if (rs != null) {
            rs.close();
        }
        return books;
    }

    public ArrayList<String> getCategoryList(Integer pk) throws SQLException {

        ResultSet rs = null;
        ArrayList<String> categoryList = new ArrayList<>();
        String query = "SELECT cl.category_name FROM havecategory as hc JOIN categorylist as cl \n"
                + "ON hc.category_id = cl.category_id where book_id = ? ";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(pk);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            categoryList.add(rs.getString("category_name"));
        }
        if (rs != null) {
            rs.close();
        }
        return categoryList;
    }

    public String getRatingAverage(Integer pk) throws SQLException {
        ResultSet rs = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String query = "SELECT AVG(user_rating) as average_rating,COUNT(user_rating) as numRate  FROM bookreview GROUP BY book_id \n"
                + "HAVING book_id = ?";
        String result = "";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(pk);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            result = decimalFormat.format(Math.round(rs.getDouble("average_rating") * 10.0) / 10.0);
            result += " " + rs.getInt("numRate");
        }
        if (rs != null) {
            rs.close();
        }
        return result;
    }

    @Override
    public void insert(BookModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BookModel get(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BookModel> getAll() {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM project1.bookinfo";
        ArrayList<Object> queryField = new ArrayList<>();
        rs = this.executeQuery(query, queryField);
        try {
            while (rs.next()) {
                BookModel book = new BookModel();
                BookModel.populateBookModel(rs, book);
                books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return books;
    }

    @Override
    public void update(Integer pk, BookModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BookModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
