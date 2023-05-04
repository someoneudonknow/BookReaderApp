/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SavedModel;
import models.entityPK.SavedPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author trang
 */
public class SavedDAO extends ResultSetQuery implements DAOInterface<SavedModel, SavedPK> {

    public static SavedDAO getInstance() {
        return new SavedDAO();
    }

    public void savedEvent(SavedPK pk) {
        try {
            ResultSet checkRs = this.checkPK(pk);
            if (checkRs.next()) {
                System.out.println(checkRs.getTimestamp("saved_date"));
                this.delete(pk);
                System.out.println("Đã xóa khỏi danh sách xem sau");
            } else {
                this.insertByPK(pk);
                System.out.println("Đã thêm vào danh sách xem sau");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet checkPK(SavedPK pk) throws SQLException {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT * FROM project1.booksaved WHERE user_id = ? AND book_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getBook_id());
        rs = this.executeQuery(query, queryField);
        return rs;
    }

    public void insertByPK(SavedPK pk) {

        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "INSERT INTO bookSaved (user_id,book_id) VALUES (?,?)";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getBook_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Đã thêm vào danh sách xem sau");

    }

    @Override
    public void insert(SavedModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SavedModel get(SavedPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SavedModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SavedPK pk, SavedModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteByBookId(int bookId) {
        String query = "DELETE FROM bookSaved WHERE book_id = " + bookId;
        
        DB db = new DB();
        Connection con = db.getConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            db.closeConnection(con);
        }
    }
    
    @Override
    public void delete(SavedPK pk) {
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "DELETE FROM project1.booksaved WHERE user_id = ? AND book_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getBook_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<SavedModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
