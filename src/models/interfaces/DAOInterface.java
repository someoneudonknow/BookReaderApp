package models.interfaces;

import java.util.List;

public interface DAOInterface <T>{
    void insert(T data);
    T get(int id);
    List<T> getAll();
    void update(int id);
    void delete(int id);
    List<T> search(String keyword);
}
