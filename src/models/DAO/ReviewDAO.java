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
        while (rs.next()) {
            ReviewModel review = new ReviewModel();
            ReviewModel.populateReviewModel(rs, review);
            reviewList.add(review);
        }
        if (rs != null) {
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

    @Override
    public ArrayList<ReviewModel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
