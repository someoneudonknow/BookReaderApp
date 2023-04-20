/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.util.ArrayList;
import java.util.List;
import models.CategoryModel;
import models.interfaces.DAOInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO implements DAOInterface<CategoryModel, Integer>   {

     public static CategoryDAO getInstance() {
        return new CategoryDAO();
    }

    @Override
    public void insert(CategoryModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CategoryModel get(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CategoryModel> getAll() {
        String query = "SELECT * FROM categorylist ORDER BY category_name DESC";
        ArrayList<CategoryModel> cateList = new ArrayList<>();
        
        DB db = new DB();
        Connection con = db.getConnection();
        
         try {
             Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()) {
                int cateId = rs.getInt("category_id");
                String catenName = rs.getString("category_name");
                CategoryModel currentCategory = new CategoryModel(cateId, catenName);
                cateList.add(currentCategory);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
         }finally {
             db.closeConnection(con);
         }
         
         return cateList;
    }

    @Override
    public void update(Integer pk, CategoryModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CategoryModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
