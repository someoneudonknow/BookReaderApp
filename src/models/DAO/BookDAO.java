/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BookModel;
import models.ChapterModel;
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

    public ChapterModel getFirstLastChapter(int book_id, String option) throws SQLException {
        ResultSet rs = null;
        ChapterModel chapter = new ChapterModel();
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? AND chapter_serial = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(book_id);
        if (option.equals("first")) {
            queryField.add(1);
        } else if (option.equals("last")) {
            query = "SELECT * FROM project1.bookchapter WHERE book_id = ? ORDER BY chapter_serial DESC LIMIT 1";
        }

        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel.populateChapterModel(rs, chapter);
        }
        return chapter;
    }

    public ArrayList<BookModel> getHistory(int currentUserID) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT bi.*,MAX(last_read) FROM project1.bookreading AS br \n"
                + "JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id \n"
                + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                + "GROUP BY br.user_id, bi.book_id HAVING user_id = ?  ORDER BY MAX(last_read) DESC ";
        queryField.add(currentUserID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }
        return books;
    }

    public ArrayList<BookModel> getSavedBook(int currentUserID) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT bi.* FROM project1.booksaved AS bs JOIN project1.bookInfo AS bi\n"
                + "ON bs.book_id = bi.book_id WHERE user_id = ? ORDER BY bs.saved_date DESC";
        queryField.add(currentUserID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }

        return books;
    }

    public ArrayList<BookModel> getOption(String option) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "";
        if (option.equals("view")) {
            query = "SELECT bi.*,SUM(bc.chapter_view) as book_view FROM project1.bookchapter AS bc\n"
                    + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                    + "GROUP BY bi.book_id ORDER BY SUM(bc.chapter_view) DESC LIMIT 5;";
        } else if (option.equals("recently")) {
            query = "SELECT bi.*,MAX(bc.chapter_update) as recently_update FROM project1.bookinfo \n"
                    + "AS bi JOIN project1.bookchapter as bc \n"
                    + "ON bi.book_id = bc.book_id GROUP BY book_id ORDER BY MAX(bc.chapter_update) DESC LIMIT 5";
        } else if (option.equals("full")) {
            query = "SELECT bi.*,MAX(bc.chapter_update) as recently_update FROM project1.bookinfo \n"
                    + "AS bi JOIN project1.bookchapter as bc \n"
                    + "ON bi.book_id = bc.book_id GROUP BY book_id ORDER BY MAX(bc.chapter_update) DESC ";
        } else {
            System.out.println("Khong ton tai");
            return books;
        }
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }
        return books;
    }

    public ArrayList<String> getCategoryList(int currentBookID) throws SQLException {

        ResultSet rs = null;
        ArrayList<String> categoryList = new ArrayList<>();
        String query = "SELECT cl.category_name FROM havecategory as hc JOIN categorylist as cl \n"
                + "ON hc.category_id = cl.category_id where book_id = ? ";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            categoryList.add(rs.getString("category_name"));
        }
        return categoryList;
    }

    public String getRatingAverage(int currentBookID) throws SQLException {
        ResultSet rs = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String query = "SELECT AVG(user_rating) as average_rating,COUNT(user_rating) as numRate  FROM bookreview GROUP BY book_id \n"
                + "HAVING book_id = ?";
        String result = "";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            result = decimalFormat.format(Math.round(rs.getDouble("average_rating") * 10.0) / 10.0);
            result += " " + rs.getInt("numRate");
        }
        return result;
    }

    public int getView(int currentBookID) throws SQLException {
        ResultSet rs = null;
        int view = 0;
        String query = "SELECT bi.*,SUM(bc.chapter_view) as book_view FROM project1.bookchapter AS bc\n"
                + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                + "GROUP BY bi.book_id HAVING bi.book_id = ?;";

        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            view = rs.getInt("book_view");
        }
        return view;
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
        try {
            rs = this.executeQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
