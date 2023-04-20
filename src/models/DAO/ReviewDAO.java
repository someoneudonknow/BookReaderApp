/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.Connection;
import database.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.ReviewModel;
import models.entityPK.ReviewPK;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

public class ReviewDAO extends ResultSetQuery implements DAOInterface<ReviewModel, ReviewPK> {

    public static ReviewDAO getInstance() {
        return new ReviewDAO();
    }

    public void updateByModel(ReviewModel updateReview) {
        ArrayList<ReviewModel> reviewList = new ArrayList<>();
        String query = "UPDATE  project1.bookreview SET user_comment = ?, user_rating = ?  WHERE user_id = ? AND book_id = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(updateReview.getComment());
        queryField.add(updateReview.getRating());
        queryField.add(updateReview.getUser_id());
        queryField.add(updateReview.getBook_id());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet checkPK(ReviewPK pk) throws SQLException {
        ResultSet rs = null;
        String query = "SELECT * FROM project1.bookreview WHERE book_id = ? AND user_id = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(pk.getBook_id());
        queryField.add(pk.getUser_id());
        rs = this.executeQuery(query, queryField);
        return rs;
    }

    public String getUserNameFromReviewPK(ReviewPK pk) throws SQLException {
        ResultSet rs = null;
        String username = "";
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT bi.user_name FROM project1.bookreview br \n"
                + "JOIN project1.userinfo bi ON br.user_id = bi.user_id\n"
                + "WHERE br.user_id = ? AND br.book_id = ?";
        queryField.add(pk.getUser_id());
        queryField.add(pk.getBook_id());
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            username = rs.getString("user_name");
        }
        return username;
    }

    public ArrayList<ReviewModel> getAllReviewFromBook(int book_id) throws SQLException {
        ResultSet rs = null;
        ArrayList<ReviewModel> reviewList = new ArrayList<>();
        String query = "SELECT * FROM project1.bookreview WHERE book_id = ? ORDER BY review_date DESC ";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(book_id);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ReviewModel review = new ReviewModel();
            ReviewModel.populateReviewModel(rs, review);
            reviewList.add(review);
        }
        return reviewList;
    }

    @Override
    public void insert(ReviewModel data) {
        ArrayList<ReviewModel> reviewList = new ArrayList<>();
        String query = "INSERT INTO project1.bookreview (user_id,book_id, user_comment, user_rating)  VALUES (?, ?, ?, ?)";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(data.getUser_id());
        queryField.add(data.getBook_id());
        queryField.add(data.getComment());
        queryField.add(data.getRating());
        try {
            this.executeNonQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void update(ReviewPK pk, ReviewModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ReviewPK pk) {
        String query = "DELETE FROM project1.bookreview br WHERE br.book_id = ? AND br.user_id = ?";
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pts = con.prepareStatement(query);
            pts.setInt(1, pk.getBook_id());
            pts.setInt(2, pk.getUser_id());

            pts.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }
    
    public void deleteByBookId(int bookId) {
        String query = "DELETE FROM bookReview WHERE bookReview.book_id = " + bookId;
        
        DB db = new DB();
        Connection con = db.getConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            db.closeConnection(con);
        }
    }

    @Override
    public ArrayList<ReviewModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
