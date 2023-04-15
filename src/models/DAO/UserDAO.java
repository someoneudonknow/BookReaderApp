/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.Blob;
import java.sql.ResultSet;
import database.DB;
import models.interfaces.DAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserModel;
import java.sql.Statement;

public class UserDAO implements DAOInterface<UserModel, Integer> {

    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public void insert(UserModel data) {
        String query = "INSERT INTO userinfo (user_name, user_avatar, user_password, user_phoneNumber, is_manager, manager_id) VALUES (?, ?, ?, ?, ?, ?)";
        UserModel manager = this.getManagerInfo();
        DB db = new DB();
        Connection userCon = db.getConnection();

        try {
            PreparedStatement pst = userCon.prepareStatement(query);

            pst.setString(1, data.getUserName().strip());
            pst.setBlob(2, data.getAvatar());
            pst.setString(3, data.getPassword().strip());
            pst.setString(4, data.getPhoneNumber().strip());
            pst.setBoolean(5, false);
            pst.setInt(6, manager.getId());

            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(userCon);
        }
    }

    public boolean insertNewUser(UserModel data) {
        if (this.isPhoneNumberExists(data.getPhoneNumber()) || this.isUserNameExists(data.getUserName())) {
            return false;
        }

        this.insert(data);
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
                System.out.print(currentUser.getBlob("user_avatar"));
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
        } finally {
            db.closeConnection(con);
        }

        return null;
    }

    @Override
    public UserModel get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<UserModel> getAll() {
        ArrayList<UserModel> listResult = new ArrayList<>();
        String query = "SELECT * FROM userinfo";
        DB db = new DB();
        Connection con = db.getConnection();
        Statement st = null;

        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String userPass = rs.getString("user_password");
                String userPhoneNumber = rs.getString("user_phoneNumber");
                Blob userAvatar = (Blob) rs.getBlob("user_avatar");
                boolean isManager = rs.getBoolean("is_manager");
                int managerId = rs.getInt("manager_id");

                UserModel user = new UserModel(id, username, userPass, userPhoneNumber, userAvatar, isManager, managerId);
                listResult.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listResult;
    }

    public UserModel getManagerInfo() {
        String query = "SELECT * FROM userinfo WHERE userinfo.is_manager = 1 LIMIT 1";

        DB db = new DB();
        Connection con = db.getConnection();
        Statement statement = null;

        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String userPass = rs.getString("user_password");
                String userPhoneNumber = rs.getString("user_phoneNumber");
                Blob userAvatar = (Blob) rs.getBlob("user_avatar");
                boolean isManager = rs.getBoolean("is_manager");
                int managerId = rs.getInt("manager_id");

                return new UserModel(id, username, userPass, userPhoneNumber, userAvatar, isManager, managerId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }

        return null;
    }

    @Override
    public void update(Integer id, UserModel data) {
        String query = "UPDATE userinfo SET user_name = ?, "
                + "user_password = ?, "
                + "user_avatar = ?, "
                + "user_phoneNumber = ? "
                + "WHERE user_id = ?";

        DB db = new DB();
        Connection con = db.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, data.getUserName().strip());
            pst.setString(2, data.getPassword().strip());
            pst.setBlob(3, data.getAvatar());
            pst.setString(4, data.getPhoneNumber().strip());
            pst.setInt(5, data.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    public void update(Integer id, UserModel data, List<String> changedField) throws Exception {
        if (changedField.size() == 0) {
            throw new Exception("data_unchanged");
        }
        StringBuilder query = new StringBuilder("UPDATE userinfo SET ");

        for (int i = 0; i < changedField.size(); i++) {
            if (changedField.get(i).equals("user_name")) {
                query.append("user_name = ").append("\"").append(data.getUserName().strip()).append("\"");
            } else if (changedField.get(i).equals("user_password")) {
                query.append("user_password = ").append("\"").append(data.getPassword().strip()).append("\"");
            } else if (changedField.get(i).equals("user_avatar")) {
                query.append("user_avatar = ?");
            } else if (changedField.get(i).equals("user_phoneNumber")) {
                query.append("user_phoneNumber = ").append("\"").append(data.getPhoneNumber().strip()).append("\"");
            } else {
                continue;
            }

            if (i >= 0 && i < changedField.size() - 1) {
                query.append(" , ");
            }
        }

        if (changedField.contains("user_name")) {
            if (this.isUserNameExists(data.getUserName())) {
                throw new Exception("user_name_exists");
            }
        }

        if (changedField.contains("user_phoneNumber")) {
            if (this.isPhoneNumberExists(data.getPhoneNumber())) {
                throw new Exception("user_phoneNumber_exists");
            }
        }

        query.append(" WHERE user_id = \"" + data.getId() + "\"");
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(query.toString());

            if (changedField.contains("user_avatar")) {
                pst.setBlob(1, data.getAvatar());
            }

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }

        System.out.println(query.toString());
    }

    public boolean updateUser(int id, UserModel data, boolean isPhoneNumChanged, boolean isUserNameChanged) {
        if (isPhoneNumChanged && isPhoneNumberExists(data.getPhoneNumber())) {
            return false;
        }
        if (isUserNameChanged && isUserNameExists(data.getUserName())) {
            return false;
        }
        this.update(id, data);
        return true;
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        String phoneNumberQuery = "SELECT userinfo.* FROM userinfo WHERE userinfo.user_phoneNumber LIKE" + "\"" + phoneNumber + "\"";
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(phoneNumberQuery);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeConnection(con);

        return false;
    }

    public boolean isUserNameExists(String userName) {
        String userNameQuery = "SELECT userinfo.* FROM userinfo WHERE userinfo.user_name LIKE" + "\"" + userName + "\"";
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(userNameQuery);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeConnection(con);

        return false;
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
