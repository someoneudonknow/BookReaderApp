/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import models.interfaces.DAOInterface;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author admin
 */
public class UserDAO implements DAOInterface<User> {
    public void insert(User newUser) {
        
    }

    public User get(int id) {
        return null;
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        
        return result;
    }

    public void update(int id) {

    }

    public void delete(int id) {

    }
    
    public List<User> search(String keyword) {
        List<User> result = new ArrayList<>();
        
        return result;
    }
}
