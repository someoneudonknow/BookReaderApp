/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.entityPK.HaveCategoryPK;

/**
 *
 * @author ADMIN
 */
public class HaveCategoryModel extends HaveCategoryPK {

    public HaveCategoryModel(int category_id, int book_id) {
        super(category_id, book_id);
    }

    public boolean contains(HaveCategoryModel obj) {
        int idb_obj = obj.getBook_id();
        int idc_obj = obj.getCategory_id();
        return (idb_obj == this.getBook_id() && idc_obj == this.getCategory_id()) ? true : false;
    }
}
