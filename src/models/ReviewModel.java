/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import models.entityPK.ReviewPK;

/**
 *
 * @author ADMIN
 */
public class ReviewModel extends ReviewPK {

    private String comment;
    private int rating;
    
    public ReviewModel() {
        
    }
    public ReviewModel(String comment, int rating, int user_id, int book_id) {
        super(user_id, book_id);
        this.comment = comment;
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public static void populateReviewModel(ResultSet rs, ReviewModel review) throws SQLException {
        review.setUser_id(rs.getInt("user_id"));
        review.setBook_id(rs.getInt("book_id"));
        review.setComment(rs.getString("user_comment"));
        review.setRating(rs.getInt("user_rating"));
    }

}
