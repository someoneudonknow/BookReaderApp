/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.entityPK.SavedPK;

/**
 *
 * @author ADMIN
 */
public class SavedModel extends SavedPK {

    private int user_id;
    private int book_id;

    public SavedModel(int user_id, int book_id) {
        super(user_id, book_id);
    }

}
