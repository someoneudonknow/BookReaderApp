/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.ResultSet;
import database.DB;
import models.interfaces.DAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserModel;

public class UserDAO implements DAOInterface<UserModel, Integer> {

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public void insert(UserModel data) {
        String query = "INSERT INTO userinfo (user_name, user_avatar, user_password, user_phoneNumber, is_manager, manager_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection userCon = new DB().getConnection();
            PreparedStatement pst = userCon.prepareStatement(query);

            pst.setString(1, data.getUserName());
            pst.setBlob(2, data.getAvatar());
            pst.setString(3, data.getPassword());
            pst.setString(4, data.getPhoneNumber());
            pst.setBoolean(5, false);
            pst.setInt(6, 1);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertNewUser(UserModel data) {
        String phoneNumberQuery = "SELECT userinfo.* FROM userinfo WHERE userinfo.user_phoneNumber LIKE" + "\"" + data.getPhoneNumber() + "\"";
        String userNameQuery = "SELECT userinfo.* FROM userinfo WHERE userinfo.user_name LIKE" + "\"" + data.getUserName() + "\"";
        DB db = new DB();
        Connection phoneNumConnection = db.getConnection();
        Connection userNameConnection = db.getConnection();
        try {
            PreparedStatement pst = phoneNumConnection.prepareStatement(phoneNumberQuery);
            PreparedStatement ust = userNameConnection.prepareStatement(userNameQuery);

            ResultSet prs = pst.executeQuery();
            ResultSet urs = ust.executeQuery();

            while (urs.next() || prs.next()) {
                return false;
            }

            this.insert(data);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(phoneNumConnection);
            db.closeConnection(userNameConnection);
        }

        return true;
    }

    public UserModel login(String userName, String password) {
        String query = "SELECT userinfo.* FROM userinfo WHERE userinfo.user_name LIKE"
                + "\""
                + userName
                + "\""
                + " AND "
                + "userinfo.user_password LIKE "
                + "\""
                + password
                + "\"";
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet currentUser = pst.executeQuery();

            while (currentUser.next()) {
                return new UserModel(currentUser.getInt("user_id"),
                        currentUser.getString("user_name"),
                        currentUser.getString("user_password"),
                        currentUser.getString("user_phoneNumber"),
                        currentUser.getBlob("user_avatar"),
                        currentUser.getBoolean("is_manager"),
                        currentUser.getInt("manager_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public UserModel get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<UserModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<UserModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
