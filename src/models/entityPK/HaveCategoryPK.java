/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entityPK;

/**
 *
 * @author trang
 */
public class HaveCategoryPK {
    private int category_id;
    private int book_id;

    public HaveCategoryPK(int category_id, int book_id) {
        this.category_id = category_id;
        this.book_id = book_id;
    }
    
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    
}
