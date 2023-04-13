/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.cj.jdbc.Blob;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

    public BookModel() {
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

    public String getAverageRating(int id) throws SQLException {
        return BookDAO.getInstance().getRatingAverage(id);
    }

//    public ImageIcon getImageFromBlob(int id) throws SQLException, IOException {
//
//        Blob coverBlob = this.getCover();
//        byte[] coverData = coverBlob.getBytes(1, (int) coverBlob.length());
//        InputStream in = new ByteArrayInputStream(coverData);
//        BufferedImage originalImage = ImageIO.read(in);
//        in.close();
//
//        Image scaledImage = originalImage.getScaledInstance(210, 230, Image.SCALE_SMOOTH);
//        BufferedImage scaledBufferedImage = new BufferedImage(210, 230, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = scaledBufferedImage.createGraphics();
//        g.drawImage(scaledImage, 0, 0, null);
//        g.dispose();
//
//        return new ImageIcon(scaledBufferedImage);
//
//    }

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

}
