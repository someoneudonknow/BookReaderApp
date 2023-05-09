/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import models.BookModel;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.ReviewDAO;
import models.DAO.SavedDAO;
import models.ReviewModel;
import models.SavedModel;
import models.entityPK.ReviewPK;
import models.entityPK.SavedPK;
import other.Converter;
import other.SetDataToList;
import static utils.formatDate.formatDate;
import views.panels.BookInforPanel;
import views.panels.ReadingPanel;
import views.MainView;
import views.panels.ParentPanel;

/**
 *
 * @author ADMIN
 */
public class BookInforController {

    private BookInforPanel bookInforPanel;
    private MainView mainView;
    private BookModel currentBook;
    private boolean havingReview;
    private JPanel previousPanel;

    public BookInforController(BookInforPanel bookInforPanel, MainView mainView, BookModel currentBook, JPanel previousPanel) throws SQLException, IOException, ParseException {
        this.bookInforPanel = bookInforPanel;
        this.mainView = mainView;
        this.currentBook = currentBook;
        this.previousPanel = previousPanel;

        this.setBookDetails();
        this.bookInforPanel.onBtnFirst(e -> {
            try {
                this.setFirstLastChapterBtn("first");
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.bookInforPanel.onBtnLast(e -> {
            try {
                this.setFirstLastChapterBtn("last");
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        this.bookInforPanel.onBtnSave(e -> {
            this.handleSavedBook();
        });
        this.bookInforPanel.onBtnAddComment(e -> {
            try {
                this.handleComment();
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.bookInforPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
        });
    }

    public boolean checkReview(ReviewPK pk) throws SQLException {
        ResultSet isExisted = ReviewDAO.getInstance().checkPK(new ReviewPK(pk.getUser_id(), pk.getBook_id()));
        if (isExisted.next()) {
            this.bookInforPanel.getBtnAddComment().setText("Edit");
            this.bookInforPanel.getjLabel1().setText("Already rated this book, do you want to edit ?");
            this.bookInforPanel.getTxtComment().setText(isExisted.getString("user_comment"));
            this.bookInforPanel.getjComboBox1().setSelectedIndex(isExisted.getInt("user_rating") - 1);
            this.mainView.repaint();
            return true;
        }
        return false;
    }

    public void addOrUpdateNewReview(ReviewModel newReview, String option) throws ParseException, SQLException, IOException {
        newReview.setBook_id(currentBook.getId());
        newReview.setUser_id(mainView.getUserModels().getId());
        String comment = this.bookInforPanel.getTxtComment().getText();
        if (comment.length() > 50) {
            JOptionPane.showMessageDialog(null, "Comment must be less than or equal to 50 characters");
        } else {
            newReview.setComment(comment);
            int rating = this.bookInforPanel.getjComboBox1().getSelectedIndex();
            newReview.setRating(rating + 1);
            if (option.equals("insert")) {
                ReviewDAO.getInstance().insert(newReview);
                JOptionPane.showMessageDialog(null, "Evaluated successfully");
                setBook(currentBook);
            } else if (option.equals("update")) {
                ReviewDAO.getInstance().updateByModel(newReview);
                JOptionPane.showMessageDialog(null, "Edited successfully");
                setBook(currentBook);
            } else {
                System.out.println("Your option ins't available");
            }
            SetDataToList reloadCommentList = new SetDataToList(this.mainView);
            try {
                reloadCommentList.setCommentList(this.bookInforPanel, this.bookInforPanel.getListComment(), currentBook.getId());
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.mainView.repaint();
        }
    }

    public void changeToChapter(ChapterModel changedChapter) throws SQLException, BadLocationException, IOException {
        try {
            setBook(currentBook);
        } catch (ParseException ex) {
            Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReadingPanel newReadingPanel = new ReadingPanel();
        ReadingController reading = new ReadingController(newReadingPanel, mainView, changedChapter, this.bookInforPanel);
        reading.setChapterDetails(changedChapter);
        this.mainView.setMainPanel(newReadingPanel);
    }

    public void setBook(BookModel book) throws SQLException, ParseException {
        this.currentBook = book;
        int currentID = book.getId();
        this.bookInforPanel.getTxtName().setText(book.getName());
        this.bookInforPanel.getTxtAuthor().setText(book.getAuthor());
        this.bookInforPanel.getTxtDiscription().setText(book.getDescription());
        ArrayList<String> categoryList = BookDAO.getInstance().getCategoryList(currentID);
        String currentCategoryList = "";
        for (String category : categoryList) {
            currentCategoryList += category + " ";
        }
        String dateUpdate = formatDate(BookDAO.getInstance().getUpdateDate(currentID));
        this.bookInforPanel.getTxtUpdate().setText(dateUpdate);
        this.bookInforPanel.getTxtCategorys().setText(currentCategoryList);
        this.bookInforPanel.getTxtView().setText("" + BookDAO.getInstance().getView(currentID));
        String[] rating = (book.getRatingAverage(book.getId())).split(" ");
        System.out.println(book.getRatingAverage(book.getId()));
        if (rating[0].equals("0") && rating[1].equals("0")) {
            this.bookInforPanel.getTxtRate().setText("No rating");
        } else {
            this.bookInforPanel.getTxtRate().setText("" + rating[0] + " star" + " " + "(" + rating[1] + ")");
        }
        this.bookInforPanel.getImgCover().setIcon(Converter.convertBlobToImageIcon(currentBook.getCover()));
        String recentlyRead = BookDAO.getInstance().getReadRecently(currentID,
                this.mainView.getUserModels().getId());
        if (!recentlyRead.equals("")) {
            this.bookInforPanel.getTxtReadRecently().setText("Chapter " + recentlyRead);

        }
    }

    public void setBookDetails() throws SQLException, ParseException {
        this.bookInforPanel.repaint();
        this.setBook(this.currentBook);
        ResultSet checkSaved = SavedDAO.getInstance().checkPK(new SavedPK(this.mainView.getUserModels().getId(), this.currentBook.getId()));
        if (checkSaved.next()) {
            this.bookInforPanel.getBtnSave().setText("Remove from library");
        }
        SetDataToList setData = new SetDataToList(mainView);
        int currentID = this.currentBook.getId();
        setData.setChapterList(this.bookInforPanel, bookInforPanel.getListChapter(), currentID);
        setData.setCommentList(this.bookInforPanel, this.bookInforPanel.getListComment(), currentID);

        boolean havingReview = this.checkReview(new ReviewPK(this.mainView.getUserModels().getId(), currentBook.getId()));
        this.havingReview = havingReview;
        //Set lại size cho panel chứa list
        JPanel panel = this.bookInforPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 50));

        JPanel panel1 = this.bookInforPanel.getListComment();
        panel1.setPreferredSize(new Dimension(0, panel1.getComponentCount() * 50));
    }

    public void setFirstLastChapterBtn(String option) throws SQLException, BadLocationException {
        if (option.equals("first")) {
            try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(this.currentBook.getId(), "first"));
            } catch (IOException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (option.equals("last")) {
            try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(this.currentBook.getId(), "last"));
            } catch (IOException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.setBook(currentBook);
        } catch (ParseException ex) {
            Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleSavedBook() {
        SavedModel saved = new SavedModel(mainView.getUserModels().getId(), currentBook.getId());
        SavedDAO.getInstance().savedEvent(saved);
        if (this.bookInforPanel.getBtnSave().getText().equals("Remove from library")) {
            this.bookInforPanel.getBtnSave().setText("Add to library");
        } else {
            this.bookInforPanel.getBtnSave().setText("Remove from library");

        }
        this.mainView.repaint();
    }

    public void handleComment() throws SQLException, IOException {
        ReviewModel newReview = new ReviewModel();
        if (this.havingReview) {
            try {
                this.addOrUpdateNewReview(newReview, "update");
            } catch (ParseException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.addOrUpdateNewReview(newReview, "insert");
            } catch (ParseException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.havingReview = checkReview(new ReviewPK(newReview.getUser_id(), newReview.getBook_id()));
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.checkReview(new ReviewPK(this.mainView.getUserModels().getId(), currentBook.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bookInforPanel.repaint();

    }

    public void backToPrevious() {
        this.mainView.setMainPanel((ParentPanel) previousPanel);
    }

    public BookInforPanel getBookInforPanel() {
        return bookInforPanel;
    }

}
