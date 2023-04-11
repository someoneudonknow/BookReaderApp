package models.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<T, PK> {

    public void insert(T data);

    public T get(PK pk);

    public ArrayList<T> getAll();

    public void update(PK pk);

    public void delete(PK pk);

    public ArrayList<T> search(String keyword);
}
