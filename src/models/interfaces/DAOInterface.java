/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.interfaces;

import java.util.List;

public interface DAOInterface <T>{
    public void insert(T data);
    public T get(int id);
    public List<T> getAll();
    public void update(int id);
    public void delete(int id);
    public List<T> search(String keyword);
}
