/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package other;

import controller.item.BookController;
import controller.item.BookItemController;
import controller.item.ChapterItemController;
import controller.item.CommentItemController;
import controller.item.AccountItemController;
import java.awt.Label;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.BookModel;
import models.CategoryModel;
import models.UserModel;
import views.items.Book;
import views.items.BookItem;
import views.items.CategoryItem;
import views.items.ChapterItem;
import views.items.CommentItem;
import views.items.AccountItem;
import javax.swing.JPanel;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.ChapterDAO;
import models.DAO.ReviewDAO;
import models.DAO.UserDAO;
import models.ReviewModel;
import models.SavedModel;
import views.panels.AccountsPanel;
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

        ArrayList<Book> items = new ArrayList<>();
        ArrayList<BookModel> books = new ArrayList<>();
        if (option.equals("full")) {
            books = BookDAO.getInstance().getOption(option);
        } else if (option.equals("savedBook")) {
            books = BookDAO.getInstance().getSavedBook(this.mainView.getUserModels().getId());
        } else if (option.equals("history")) {
            books = BookDAO.getInstance().getHistory(this.mainView.getUserModels().getId());
        }

        for (BookModel book : books) {
            Book a = new Book(book);
            new BookController(a, this.mainView, option, parent);
            items.add(a);
        }

        for (Book i : items) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void SetSearchDataToList(JPanel panel, JPanel parent, String name, String type, String sort, ArrayList<CategoryItem> categoryItem) {
        ArrayList<BookModel> books = new ArrayList<>();
        ArrayList<Book> items = new ArrayList<>();
        try {
            books = BookDAO.getInstance().getDataAvailable(sort, type, name, categoryItem);
        } catch (SQLException ex) {
            Logger.getLogger(SetDataToList.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (books.size() > 0) {
            for (BookModel i : books) {
                Book a = new Book(i);
                new BookController(a, this.mainView, "search", parent);
                System.out.println(a.getBookModels().getName());
                items.add(a);
            }

            for (Book i : items) {
                panel.add(i);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can not find book with input search");
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
    public void setBookManagerList(JPanel panel, ArrayList<BookModel> books, boolean option, String keyword) {
        List<BookItem> itemManager = new ArrayList<>();
        if(option)
            books = BookDAO.getInstance().getAll();
        else 
            books = BookDAO.getInstance().search(keyword);

        for (BookModel b : books) {
            BookItem a = new BookItem(b);
            new BookItemController(a, this.mainView, b);
            itemManager.add(a);
        }

        for (BookItem i : itemManager) {
            panel.add(i);
        }
        panel.revalidate();
        panel.repaint();
    }
    

//    public void setAndResetBookManagerList(JPanel panel) {
//        List<BookItemManager> itemManager = new ArrayList<>();
//        List<BookModel> books = BookDAO.getInstance().getAll();
//        panel.removeAll();
//        for (BookModel b : books) {
//            BookItemManager a = new BookItemManager(b);
//            new BookItemManagerController(a, this.mainView, b);
//            itemManager.add(a);
//        }
//
//        for (BookItemManager i : itemManager) {
//            panel.add(i);
//        }
//        panel.revalidate();
//        panel.repaint();
//    }
    public void setUserItemList(JPanel panel, List<UserModel> users) {
        ArrayList<AccountItem> items = new ArrayList<>();

        for (UserModel curr : users) {
            AccountItem a = new AccountItem(curr);
            new AccountItemController(a, mainView, curr);
            items.add(a);
        }

        for (AccountItem i : items) {
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

        if (chapterList.size() == 0) {
            panel.add(new Label("This book hasn't had any chapter"));
            panel.revalidate();
            panel.repaint();
            return;
        }

        for (ChapterModel chapter : chapterList) {
            ChapterItem a = null;
            try {
                a = new ChapterItem(chapter);
            } catch (ParseException ex) {
                Logger.getLogger(SetDataToList.class.getName()).log(Level.SEVERE, null, ex);
            }
            new ChapterItemController(parent, a, mainView);
            items.add(a);
        }

        for (ChapterItem i : items) {
            panel.add(i);
        }

        panel.revalidate();
        panel.repaint();
    }

    public void setChapterList(JPanel parent, JPanel panel, List<ChapterModel> chapters) throws SQLException {
        ArrayList<ChapterItem> items = new ArrayList<>();
        panel.removeAll();

        if (chapters.size() == 0 || chapters == null) {
            panel.add(new Label("This book hasn't had any chapter"));
            panel.revalidate();
            panel.repaint();
            return;
        }

        for (ChapterModel chapter : chapters) {
            ChapterItem a = null;
            try {
                a = new ChapterItem(chapter);
            } catch (ParseException ex) {
                Logger.getLogger(SetDataToList.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        if (reviewList.size() == 0) {
            panel.add(new JLabel("No comments"));
            panel.revalidate();
            panel.repaint();
        }

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
            ArrayList<Book> items = new ArrayList<>();
            ArrayList<BookModel> books = new ArrayList<>();
            books = BookDAO.getInstance().getOption(option);
            for (BookModel i : books) {
                Book item = new Book(i);
                new BookController(item, mainView, option, parent);
                items.add(item);
            }
            for (Book i : items) {
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
