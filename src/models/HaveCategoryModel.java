/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import models.entityPK.HaveCategoryPK;

/**
 *
 * @author ADMIN
 */
public class HaveCategoryModel extends HaveCategoryPK {

    public HaveCategoryModel(int category_id, int book_id) {
        super(category_id, book_id);
    }

    public HaveCategoryModel() {
    }

        
    public boolean contains(HaveCategoryModel obj) {
        int idb_obj = obj.getBook_id();
        int idc_obj = obj.getCategory_id();
        return (idb_obj == this.getBook_id() && idc_obj == this.getCategory_id()) ? true : false;
    }
    
    /**
     *
     * @param rs
     * @param category
     */
    public static void populateHaveCategoryModel(ResultSet rs, HaveCategoryModel category) throws SQLException{
        category.setBook_id(rs.getInt("book_id"));
        category.setCategory_id(rs.getInt("category_id"));
    }
}
