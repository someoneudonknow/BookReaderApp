/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SavedModel;
import models.entityPK.SavedPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class SavedDAO extends ResultSetQuery implements DAOInterface<SavedModel, SavedPK> {

    public static SavedDAO getInstance() {
        return new SavedDAO();
    }

    public void addOrDeleteSaved(SavedModel data) {
        try {
            ResultSet checkRs = this.checkPK(new SavedPK(data.getUser_id(), data.getBook_id()));
            if (checkRs.next()) {
                this.delete(data);
                System.out.println("Đã xóa khỏi danh sách xem sau");
            } else {
                this.insert(data);
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

    @Override
    public void insert(SavedModel data) {

        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "INSERT INTO bookSaved (user_id,book_id) VALUES (?,?)";
        queryField.add(data.getUser_id());
        queryField.add(data.getBook_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Đã thêm vào danh sách xem sau");

    }

    @Override
    public SavedModel get(SavedPK pk) {
        SavedModel saved = new SavedModel();
        ResultSet rs = null;
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT * FROM project1.booksaved WHERE user_id = ? AND book_id = ?";
        queryField.add(pk.getUser_id(), pk.getBook_id());
        try {
            rs = this.executeQuery(query, queryField);
            while (rs.next()) {
                SavedModel.populateChapterModel(rs, saved);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saved;
    }

    @Override
    public ArrayList<SavedModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SavedPK pk, SavedModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
