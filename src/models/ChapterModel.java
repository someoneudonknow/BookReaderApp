/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


/**
 *
 * @author ADMIN
 */
public class ChapterModel {
    private int id;
    private String title;
    private int serial;
    private String document;
    private int book_id;

    public ChapterModel() {
    }

    public ChapterModel(int id, String title, int serial, String document, int book_id) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.document = document;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    
    
}
