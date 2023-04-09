/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.cj.jdbc.Blob;

/**
 *
 * @author ADMIN
 */
public class BookModels {
    private int id;
    private String name;
    private String author;
    private Blob cover;
    private String description;
    private int manager_id;

    public BookModels(int id, String name, String author, Blob cover, String description, int manager_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.description = description;
        this.manager_id = manager_id;
    }

    public BookModels() {
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
    
    
    
}
