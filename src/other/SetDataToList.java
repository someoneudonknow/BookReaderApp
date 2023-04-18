/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package other;

import controller.item.BookItemController;
import controller.item.BookItemManagerController;
import controller.item.ChapterItemController;
import controller.item.CommentItemController;
import controller.item.UserItemController;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import models.BookModel;
import models.CategoryModel;
import models.UserModel;
import views.items.BookItem;
import views.items.BookItemManager;
import views.items.CategoryItem;
import views.items.ChapterItem;
import views.items.CommentItem;
import views.items.UserItemManager;
import javax.swing.JPanel;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.ChapterDAO;
import models.DAO.ReviewDAO;
import models.DAO.UserDAO;
import models.ReviewModel;
import models.SavedModel;
import views.panels.UserManagingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class SetDataToList {

    private MainView mainView;
    private ArrayList<BookModel> bookList;
    private ArrayList<UserModel> userList;
    private ArrayList<CategoryModel> categoryList;
    private ArrayList<ChapterModel> chapterList;
    private ArrayList<ReviewModel> reviewList;
    private ArrayList<SavedModel> saveList;

    public SetDataToList(MainView mainView) {
        this.mainView = mainView;
    }

    public void setBookItemList(JPanel panel, String option, JPanel parent) throws SQLException {
        ArrayList<BookItem> items = new ArrayList<>();
        ArrayList<BookModel> books = new ArrayList<>();
        if (option.equals("full")) {
            books = BookDAO.getInstance().getOption(option);
        } else if (option.equals("savedBook")) {
            books = BookDAO.getInstance().getSavedBook(this.mainView.getUserModels().getId());
        } else if (option.equals("history")) {
            books = BookDAO.getInstance().getHistory(this.mainView.getUserModels().getId());
        }

        for (BookModel book : books) {
            BookItem a = new BookItem(book);
            new BookItemController(a, this.mainView,option, parent);
            items.add(a);
        }

        for (BookItem i : items) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }

//    public void setMainViewItemList(JPanel panel) {
//        ArrayList<BookItem> items = new ArrayList<>();
//        
//        for(int i=0;i<5;i++){
//            BookItem a = new BookItem(new BookModel(i, "a", "a", null, "a", 1));
//            new BookItemController(a,this.mainView);
//            items.add(a);
//            
//            
//        }
//        
//        for (BookItem i:items) {
//            panel.add(i);
//        }
//        panel.revalidate();
//        panel.repaint();
//    }
    public void setBookManagerList(JPanel panel, ArrayList<BookModel> books) {
        List<BookItemManager> itemManager = new ArrayList<>();
        books = BookDAO.getInstance().getAll();

        for (BookModel b : books) {
            BookItemManager a = new BookItemManager(b);
            new BookItemManagerController(a, this.mainView, b);
            itemManager.add(a);
        }

        for (BookItemManager i : itemManager) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void setUserItemList(JPanel panel, List<UserModel> users) {
        ArrayList<UserItemManager> items = new ArrayList<>();
        
        for(UserModel curr: users) {
            UserItemManager a = new UserItemManager(curr);
            new UserItemController(a, mainView, curr);
            items.add(a);
        }

        for (UserItemManager i : items) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }

    //
    public void setChapterList(JPanel parent, JPanel panel, int book_id) throws SQLException {
        ArrayList<ChapterItem> items = new ArrayList<>();
        ArrayList<ChapterModel> chapterList = new ArrayList<>();
        chapterList = ChapterDAO.getInstance().getAllChapterFromBook(book_id);

        for (ChapterModel chapter : chapterList) {
            ChapterItem a = new ChapterItem(chapter);
            new ChapterItemController(parent, a, mainView);
            items.add(a);
        }

        for (ChapterItem i : items) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void setCommentList(JPanel parent, JPanel panel, int book_id) throws SQLException, ParseException {
        panel.removeAll();
        ArrayList<CommentItem> items = new ArrayList<>();
        ArrayList<ReviewModel> reviewList = ReviewDAO.getInstance().getAllReviewFromBook(book_id);

        for (ReviewModel review : reviewList) {
            CommentItem a = new CommentItem(review);
            new CommentItemController(parent, a, mainView, review);
            items.add(a);
        }
        for (CommentItem i : items) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }
    //tạo list book cho mainView (truyền vào max 5 cuốn)
    public void setTop5View(JPanel panel, String option, JPanel parent) {
        try {
            ArrayList<BookItem> items = new ArrayList<>();
            ArrayList<BookModel> books = new ArrayList<>();
            books = BookDAO.getInstance().getOption(option);
            for (BookModel i : books) {
                BookItem item = new BookItem(i);
                new BookItemController(item, mainView, option, parent);
                items.add(item);
            }
            for (BookItem i : items) {
                panel.add(i);
            }
        } catch (Exception e) {
            panel.add(new JLabel("Danh sach trong"));
        }
        panel.revalidate();
        panel.repaint();
    }
//    
//    //tạo list thông tin book cho người quản lý
//    public void setBookManagerList(JPanel panel) {
//        try {
//            ArrayList<BookItemManager> items = new ArrayList<>();
//            for (BookModels i:this.bookList) {
//                BookItemManager item = new BookItemManager(i);
//                new BookItemManagerController(item, mainView);
//                items.add(item);
//            }
//            for (BookItemManager i:items) {
//                panel.add(i);
//            }
//        } catch (Exception e) {
//            panel.add(new JLabel("Danh sach trong"));
//        }
//        panel.revalidate();
//        panel.repaint();
//    }
//    
//    //tạo list thông tin user
//    public void setUserItemList(JPanel panel) {
//        try {
//            ArrayList<UserItemManager> items = new ArrayList<>();
//            for (UserModels i:this.userList) {
//                UserItemManager item = new UserItemManager(i);
//                new UserItemController(item,mainView);
//                items.add(item);
//            }
//            for (UserItemManager i:items) {
//                panel.add(i);
//            }
//        } catch (Exception e) {
//            panel.add(new JLabel("Danh sach trong"));
//        }
//        panel.revalidate();
//        panel.repaint();
//    }
//    
//    //tạo list chapter
//    public void setChapterList(JPanel panel, int book_id) {
//        try {
//            ArrayList<ChapterItem> items = new ArrayList<>();
//            for (ChapterModels i:this.chapterList) {
//                if(i.getBook_id() == book_id){
//                    ChapterItem item = new ChapterItem(i);
//                    new ChapterItemController(item, mainView);
//                    items.add(new ChapterItem(i));
//                }
//            }
//            for (ChapterItem i:items) {
//                panel.add(i);
//            }
//        } catch (Exception e) {
//            panel.add(new JLabel("Danh sach trong"));
//        }
//        panel.revalidate();
//        panel.repaint();
//    }
//
//
//    //tạo list comments của sách
//    public void setCommentList(JPanel panel, int book_id) {
//        ArrayList<CommentItem> items = new ArrayList<>();
//
//        for(ReviewModels i:reviewList){
//            if(i.getBook_id() == book_id){
//                CommentItem a = new CommentItem(i);
//                new CommentItemController(a, mainView);
//                items.add(a);
//            } 
//        }
//        
//        for (CommentItem i:items) {
//            panel.add(i);
//        }
//        
//        panel.revalidate();
//        panel.repaint();
//    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public ArrayList<BookModel> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<BookModel> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserModel> userList) {
        this.userList = userList;
    }

    public ArrayList<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<ChapterModel> getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList<ChapterModel> chapterList) {
        this.chapterList = chapterList;
    }

    public ArrayList<ReviewModel> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<ReviewModel> reviewList) {
        this.reviewList = reviewList;
    }

}
