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
import models.UserModel;
import models.interfaces.DAOInterface;

/**
 *
 * @author trang
 */
public class BookDAO implements DAOInterface<BookModel, Integer> {

    public static BookDAO getInstance() {
        return new BookDAO();
    }

    public ArrayList<BookModel> getTop5() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        DB mydb = new DB();
        try {
            conn = mydb.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM project1.bookinfo LIMIT 5 ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                BookModel book = new BookModel();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("book_name"));
                book.setAuthor(rs.getString("book_author"));
                book.setDescription(rs.getString("book_description"));
                Blob bookCover = (Blob) rs.getBlob("book_cover");
                if (bookCover != null) {
                    book.setCover(bookCover);
                } else {
                    book.setCover(null);
                }
                book.setManager_id(rs.getInt("manager_id"));
                books.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mydb.closeConnection(conn);
        }
        return books;
    }

    public ArrayList<String> getCategoryList(Integer pk) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> categoryList = new ArrayList<>();
        DB mydb = new DB();
        try {
            conn = mydb.getConnection();
            stmt = conn.prepareStatement("SELECT cl.category_name FROM havecategory as hc JOIN categorylist as cl \n"
                    + "ON hc.category_id = cl.category_id where book_id = ? ");
            stmt.setInt(1, pk);
            rs = stmt.executeQuery();
            while (rs.next()) {
                categoryList.add(rs.getString("category_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mydb.closeConnection(conn);
        }
        return categoryList;
    }

    public String getRatingAverage(Integer pk) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
//        int ratingCount = 0;
        String result = "";
        DB mydb = new DB();
        try {
            conn = mydb.getConnection();
            stmt = conn.prepareStatement("SELECT AVG(user_rating) as average_rating,COUNT(user_rating) as numRate  FROM bookreview GROUP BY book_id \n"
                    + "HAVING book_id = ?");
            stmt.setInt(1, pk);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result = decimalFormat.format(Math.round(rs.getDouble("average_rating") * 10.0) / 10.0);
                result += " " + rs.getInt("numRate");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mydb.closeConnection(conn);
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
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        DB mydb = new DB();
        try {
            conn = mydb.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM project1.bookinfo ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                BookModel book = new BookModel();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("book_name"));
                book.setAuthor(rs.getString("book_author"));
                book.setDescription(rs.getString("book_description"));
                Blob bookCover = (Blob) rs.getBlob("book_cover");
                if (bookCover != null) {
                    book.setCover(bookCover);
                } else {
                    book.setCover(null);
                }
                book.setManager_id(rs.getInt("manager_id"));
                books.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mydb.closeConnection(conn);
        }
        return books;
    }

    @Override
    public void update(Integer pk) {
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
