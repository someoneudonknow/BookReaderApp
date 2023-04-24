/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BookModel;
import models.CategoryModel;
import models.ChapterModel;
import models.interfaces.DAOInterface;
import utils.ResultSetQuery;
import views.items.BookItem;
import views.items.CategoryItem;

/**
 *
 * @author trang
 */
public class BookDAO extends ResultSetQuery implements DAOInterface<BookModel, Integer> {

    public BookDAO() {

    }

    public static BookDAO getInstance() {
        return new BookDAO();
    }

    public ChapterModel getFirstLastChapter(int book_id, String option) throws SQLException {
        ResultSet rs = null;
        ChapterModel chapter = new ChapterModel();
        String query = "SELECT * FROM project1.bookchapter WHERE book_id = ? AND chapter_serial = ?";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(book_id);
        if (option.equals("first")) {
            queryField.add(1);
        } else if (option.equals("last")) {
            query = "SELECT * FROM project1.bookchapter WHERE book_id = ? ORDER BY chapter_serial DESC LIMIT 1";
        }

        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            ChapterModel.populateChapterModel(rs, chapter);
        }
        return chapter;
    }

    public ArrayList<BookModel> getHistory(int currentUserID) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT bi.*,MAX(last_read) FROM project1.bookreading AS br \n"
                + "JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id \n"
                + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                + "GROUP BY br.user_id, bi.book_id HAVING user_id = ?  ORDER BY MAX(last_read) DESC ";
        queryField.add(currentUserID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }
        return books;
    }

    public String getReadRecently(int book_id, int user_id) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT MAX(bc.chapter_serial) AS read_recently FROM project1.bookreading AS br \n"
                + "JOIN project1.bookchapter AS bc ON br.chapter_id = bc.chapter_id\n"
                + "GROUP BY book_id,user_id\n"
                + "HAVING bc.book_id = ? AND br.user_id = ?";
        queryField.add(book_id);
        queryField.add(user_id);
        rs = this.executeQuery(query, queryField);
        String recentlyChapter = "";
        while (rs.next()) {
            recentlyChapter = rs.getString("read_recently");
        }

        return recentlyChapter;
    }

    public String getUpdateDate(int book_id) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT MAX(bc.chapter_update) as book_update FROM project1.bookinfo AS bi\n"
                + "JOIN project1.bookchapter AS bc ON bi.book_id = bc.book_id\n"
                + "GROUP BY bi.book_id\n"
                + "HAVING bi.book_id = ?";
        queryField.add(book_id);
        rs = this.executeQuery(query, queryField);
        String currentDateTime = "";
        while (rs.next()) {
            currentDateTime = "" + rs.getTimestamp("book_update");
        }
        String[] result = currentDateTime.split(" ");
        return result[0];
    }

    public ArrayList<BookModel> getSavedBook(int currentUserID) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "SELECT bi.* FROM project1.booksaved AS bs JOIN project1.bookInfo AS bi\n"
                + "ON bs.book_id = bi.book_id WHERE user_id = ? ORDER BY bs.saved_date DESC";
        queryField.add(currentUserID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }

        return books;
    }

    public ArrayList<BookModel> getOption(String option) throws SQLException {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        String query = "";
        if (option.equals("view")) {
            query = "SELECT bi.*,SUM(bc.chapter_view) as book_view FROM project1.bookchapter AS bc\n"
                    + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                    + "GROUP BY bi.book_id ORDER BY SUM(bc.chapter_view) DESC LIMIT 5;";
        } else if (option.equals("recently")) {
            query = "SELECT bi.*,MAX(bc.chapter_update) as recently_update FROM project1.bookinfo \n"
                    + "AS bi JOIN project1.bookchapter as bc \n"
                    + "ON bi.book_id = bc.book_id GROUP BY book_id ORDER BY MAX(bc.chapter_update) DESC LIMIT 5";
        } else if (option.equals("full")) {
            query = "SELECT bi.*,MAX(bc.chapter_update) as recently_update FROM project1.bookinfo \n"
                    + "AS bi JOIN project1.bookchapter as bc \n"
                    + "ON bi.book_id = bc.book_id GROUP BY book_id ORDER BY MAX(bc.chapter_update) DESC ";
        } else {
            System.out.println("Khong ton tai");
            return books;
        }
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            BookModel book = new BookModel();
            BookModel.populateBookModel(rs, book);
            books.add(book);
        }
        return books;
    }

    public ArrayList<String> getCategoryList(int currentBookID) throws SQLException {

        ResultSet rs = null;
        ArrayList<String> categoryList = new ArrayList<>();
        String query = "SELECT cl.category_name FROM havecategory as hc JOIN categorylist as cl \n"
                + "ON hc.category_id = cl.category_id where book_id = ? ";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            categoryList.add(rs.getString("category_name"));
        }
        return categoryList;
    }

    public List<CategoryModel> getCurrentBookCategories(int currentBookID) {
        String query = "SELECT cl.* FROM categorylist as cl INNER JOIN havecategory as hc ON cl.category_id = hc.category_id WHERE hc.book_id = " + currentBookID;
        List<CategoryModel> cateListResult = new LinkedList<>();
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int cateId = rs.getInt("category_id");
                String cateName = rs.getString("category_name");

                CategoryModel currentCate = new CategoryModel(cateId, cateName);
                cateListResult.add(currentCate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cateListResult;
    }

    public String getRatingAverage(int currentBookID) throws SQLException {
        ResultSet rs = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String query = "SELECT AVG(user_rating) as average_rating,COUNT(user_rating) as numRate  FROM bookreview GROUP BY book_id \n"
                + "HAVING book_id = ?";
        String result = "";
        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            result = decimalFormat.format(Math.round(rs.getDouble("average_rating") * 10.0) / 10.0);
            result += " " + rs.getInt("numRate");
        }
        return result;
    }

    public int getView(int currentBookID) throws SQLException {
        ResultSet rs = null;
        int view = 0;
        String query = "SELECT bi.*,SUM(bc.chapter_view) as book_view FROM project1.bookchapter AS bc\n"
                + "JOIN project1.bookinfo AS bi ON bc.book_id = bi.book_id\n"
                + "GROUP BY bi.book_id HAVING bi.book_id = ?;";

        ArrayList<Object> queryField = new ArrayList<>();
        queryField.add(currentBookID);
        rs = this.executeQuery(query, queryField);
        while (rs.next()) {
            view = rs.getInt("book_view");
        }
        return view;
    }

    @Override
    public void insert(BookModel data) {
        String query = "INSERT INTO bookInfo(book_name, book_author, book_cover, book_description, manager_id) VALUES (?, ?, ?, ?, ?)";
        DB db = new DB();
        Connection con = db.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, data.getName());
            pst.setString(2, data.getAuthor());
            pst.setBlob(3, data.getCover());
            pst.setBlob(4, data.getCover());
            pst.setInt(5, data.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    public void insert(BookModel data, List<CategoryModel> bookCategories, List<ChapterModel> chapters) {
        this.insert(data);
        HaveCategoryDAO.getInstance().insert(data.getId(), bookCategories);
        
    }

    @Override
    public BookModel get(Integer pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BookModel> getAll() {
        ResultSet rs = null;
        ArrayList<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM project1.bookinfo";
        ArrayList<Object> queryField = new ArrayList<>();
        try {
            rs = this.executeQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                BookModel book = new BookModel();
                BookModel.populateBookModel(rs, book);
                books.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return books;
    }

    public ArrayList<BookModel> getAllBook() {
        String query = "SELECT * FROM project1.bookinfo";
        ArrayList<BookModel> books = new ArrayList<>();

        DB db = new DB();
        Connection con = db.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                BookModel b = new BookModel();
                BookModel.populateBookModel(rs, b);
                books.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return books;
    }

    public void update(int id, BookModel data, List<String> fields) throws Exception {
        if (fields.size() == 0) {
            throw new Exception("data_unchanged");
        }
        if (fields.contains("book_name")) {
            if (this.isBookNameContains(data.getName())) {
                throw new Exception("book_name_exists");
            }
        }

        if (fields.size() == 1 && fields.contains("book_categories")) {
            System.out.println("return in Book DAO");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE bookInfo SET ");

        System.out.println(fields.size());

        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).equals("book_name")) {
                query.append("book_name = ").append("\"").append(data.getName().strip()).append("\", ");
            } else if (fields.get(i).equals("book_author")) {
                query.append("book_author = ").append("\"").append(data.getAuthor().strip()).append("\", ");
            } else if (fields.get(i).equals("book_cover")) {
                query.append("book_cover = ?, ");
            } else if (fields.get(i).equals("book_description")) {
                query.append("book_description = ").append("\"").append(data.getDescription().strip()).append("\", ");
            } else {
                continue;
            }
        }

        query.setCharAt(query.lastIndexOf(","), ' ');
        query.append("WHERE book_id = " + id);
        String finalQuery = query.toString().strip();
        DB db = new DB();
        Connection con = db.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(finalQuery);

            if (fields.contains("book_cover")) {
                pst.setBlob(1, data.getCover());
            }

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }

        System.out.println(finalQuery);
    }

    public boolean isBookNameContains(String bookName) {
        String query = "SELECT * FROM bookInfo WHERE bookInfo.book_name LIKE " + "\"" + bookName + "\"";
        DB db = new DB();
        Connection con = db.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
    }

    @Override
    public void update(Integer pk, BookModel data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer pk) {
        String query = "DELETE FROM bookInfo WHERE bookInfo.book_id = " + pk;

        DB db = new DB();
        Connection con = db.getConnection();
        try {
            Statement st = con.createStatement();
            ChapterDAO.getInstance().deleteAllChapterFromOneBook(pk);
            ReviewDAO.getInstance().deleteByBookId(pk);
            SavedDAO.getInstance().deleteByBookId(pk);
            HaveCategoryDAO.getInstance().deleteAllCategoriesOfOneBook(pk);
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(con);
        }
    }

    @Override
    public ArrayList<BookModel> search(String keyword) {
        ResultSet rs = null;
        String query = "SELECT * FROM project1.bookinfo WHERE bookinfo.book_name LIKE '%" + keyword + "%' ORDER BY bookinfo.book_name ASC";
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        try {
            rs = this.executeQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                BookModel temp = new BookModel();
                BookModel.populateBookModel(rs, temp);
                books.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public ArrayList<BookModel> getDataAvailable(String sort, String type, String name, ArrayList<CategoryItem> categoryItem) throws SQLException {
        ResultSet rs = null;
        String query = "";
        ArrayList<BookModel> result = new ArrayList<>();
        ArrayList<Object> queryField = new ArrayList<>();
        if(type.equals("Title")){
            query = "SELECT bi.* FROM project1.bookinfo AS bi INNER JOIN project1.havecategory ON bi.book_id = havecategory.book_id "
                + "WHERE bi.book_name LIKE '%" + name + "%'";
        }else if(type.equals("Author")){
            query = "SELECT bi.* FROM project1.bookinfo AS bi INNER JOIN project1.havecategory ON bi.book_id = havecategory.book_id "
                + "WHERE bi.book_author LIKE '%" + name + "%'";
        }
        
        int size = categoryItem.size();
        if(size != 0){
            for(int i = 0;i < size;i++){
                query = query + "AND havecategory.category_id = " + categoryItem.get(i).getCategoryModels().getId();
            }
        }
        query = query + " GROUP BY bi.book_id ";
        
        if(sort.equals("A->Z")){
            query = query + " ORDER BY bi.book_name ASC";
        }else if(sort.equals("Z->A")){
            query = query + " ORDER BY bi.book_name  DESC";
        }
        
        try {
            rs = this.executeQuery(query, queryField);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (rs.next()) {
            BookModel temp = new BookModel();
            BookModel.populateBookModel(rs, temp);
            result.add(temp);
        }

        return result;
    }

}
