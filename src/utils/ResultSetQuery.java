/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author trang
 */
public class ResultSetQuery {

    private DB mydb;

    public ResultSetQuery() {
        mydb = new DB();
    }

    public PreparedStatement preparedStatement(String query, ArrayList<Object> queryField) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = mydb.getConnection();
        stmt = conn.prepareStatement(query);
        if (queryField.size() != 0) {
            int i = 1;
            for (Object o : queryField) {
                if (o instanceof String) {
                    stmt.setString(i, (String) o);
                } else if (o instanceof Integer) {
                    stmt.setInt(i, (Integer) o);
                }
                i++;
            }
        }
        return stmt;
    }

    public ResultSet executeQuery(String query, ArrayList<Object> queryField) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = this.preparedStatement(query, queryField);
        ResultSet rs = null;
        rs = stmt.executeQuery();

        return rs;
    }

    public void executeNonQuery(String query, ArrayList<Object> queryField) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = this.preparedStatement(query, queryField);
        stmt.executeUpdate();

    }
}
