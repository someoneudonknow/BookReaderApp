/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.entityPK.ReviewPK;

/**
 *
 * @author ADMIN
 */
public class ReviewModel extends ReviewPK {

    private String comment;
    private int rating;

    public ReviewModel(int user_id, int book_id, String comment, int rating) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
