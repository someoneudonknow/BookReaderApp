/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import models.entityPK.ReviewPK;

/**
 *
 * @author ADMIN
 */
public class ReviewModel extends ReviewPK {

    private String comment;
    private int rating;
    private Date reviewDate;
    //Timestamp lấy ngày và giờ, loại bọ giờ khi hiện thị trong ứng dụng
    public ReviewModel() {
        
    }

    public ReviewModel(String comment, int rating, Date reviewDate) {
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    public static void populateReviewModel(ResultSet rs, ReviewModel review) throws SQLException {
        review.setUser_id(rs.getInt("user_id"));
        review.setBook_id(rs.getInt("book_id"));
        review.setComment(rs.getString("user_comment"));
        review.setRating(rs.getInt("user_rating"));
        review.setReviewDate(rs.getDate("review_date"));
        System.out.println(rs.getDate("review_date"));
    }

}
