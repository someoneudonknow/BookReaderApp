/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.HaveCategoryModel;
import models.entityPK.HaveCategoryPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class HaveCategoryDAO extends ResultSetQuery implements DAOInterface<HaveCategoryModel, HaveCategoryPK> {

    public static HaveCategoryDAO getInstance() {
        return new HaveCategoryDAO();
    }

    @Override
    public void insert(HaveCategoryModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HaveCategoryModel get(HaveCategoryPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HaveCategoryModel> getAll() {
        ResultSet rs = null;
        ArrayList<HaveCategoryModel> listHaveCategory = new ArrayList<>();
        String query = "SELECT * FROM project1.havecategory";
        ArrayList<Object> queryField = new ArrayList<>();
        try {
            rs = this.executeQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(HaveCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {   
            while (rs.next()) {
                HaveCategoryModel category = new HaveCategoryModel();
                HaveCategoryModel.populateHaveCategoryModel(rs, category);
                listHaveCategory.add(category);
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
        return listHaveCategory;
    }

    @Override
    public void update(HaveCategoryPK pk, HaveCategoryModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(HaveCategoryPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HaveCategoryModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    
    

}
