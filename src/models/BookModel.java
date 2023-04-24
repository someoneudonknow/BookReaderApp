/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Blob;
import java.sql.SQLException;
import models.DAO.BookDAO;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class BookModel {

    private int id;
    private String name;
    private String author;
    private Blob cover;
    private String description;
    private int manager_id;

    public BookModel(int id, String name, String author, Blob cover, String description, int manager_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.description = description;
        this.manager_id = manager_id;
    }

    public BookModel(String name, String author, Blob cover, String description, int manager_id) {
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.description = description;
        this.manager_id = manager_id;
    }

    public BookModel() {
        this.id = 0;
        this.name = "";
        this.author = "";
        this.cover = null;
        this.description = "";
        this.manager_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Blob getCover() {
        return cover;
    }

    public void setCover(Blob cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getRatingAverage(int id) throws SQLException {
        return BookDAO.getInstance().getRatingAverage(id);
    }

    public int getView(int id) throws SQLException {
        return BookDAO.getInstance().getView(id);
    }

    public static void populateBookModel(ResultSet rs, BookModel book) throws SQLException {
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("book_name"));
        book.setAuthor(rs.getString("book_author"));
        book.setDescription(rs.getString("book_description"));
        Blob bookCover = (Blob) rs.getBlob("book_cover");
        if (bookCover != null) {
            book.setCover(bookCover);
        } else {
            book.setCover(null);
        }
        book.setManager_id(rs.getInt("manager_id"));
    }

    @Override
    public String toString() {
        return "BookModel{" + "id=" + id + ", name=" + name + ", author=" + author + ", cover=" + cover + ", description=" + description + ", manager_id=" + manager_id + '}';
    }
    
    
}
