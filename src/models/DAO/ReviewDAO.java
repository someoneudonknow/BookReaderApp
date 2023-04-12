/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.ReviewModel;
import models.entityPK.ReviewPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;

/**
 *
 * @author trang
 */
public class ReviewDAO extends ResultSetQuery implements DAOInterface<ReviewModel, ReviewPK> {

    public static ReviewDAO getInstance() {
        return new ReviewDAO();
    }

    public ArrayList<ReviewModel> getAllReviewFromBook(int book_id) throws SQLException {
        ResultSet rs = null;
        ArrayList<ReviewModel> reviewList = new ArrayList<>();
        String query = "SELECT * FROM project1.bookreview WHERE book_id = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(book_id);
        rs = this.executeQuery(query, queryField);
        while(rs.next()) {
            ReviewModel review = new ReviewModel();
            ReviewModel.populateChapterModel(rs, review);
            reviewList.add(review);
        }
        if(rs != null) {
            rs.close();
        }
        return reviewList;
    }

    @Override
    public void insert(ReviewModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReviewModel get(ReviewPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ReviewModel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ReviewPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ReviewPK pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ReviewModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
