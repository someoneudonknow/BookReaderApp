/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import models.BookModel;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.SavedDAO;
import models.SavedModel;
import models.entityPK.SavedPK;
import other.Converter;
import other.SetDataToList;
import views.panels.BookInforPanel;
import views.panels.ReadingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookInforController {

    private BookInforPanel bookInforPanel;
    private MainView mainView;
    private BookModel currentBook;

    public BookInforController(BookInforPanel bookInforPanel, MainView mainView, BookModel currentBook) throws SQLException, IOException {
        this.bookInforPanel = bookInforPanel;
        this.mainView = mainView;
        this.currentBook = currentBook;

        this.setBook(currentBook);
        ResultSet checkSaved = SavedDAO.getInstance().checkPK(new SavedPK(this.mainView.getUserModels().getId(), this.currentBook.getId()));
        if (checkSaved.next()) {
            this.bookInforPanel.getBtnSave().setText("Xóa khỏi thư viện");
        }
        SetDataToList setData = new SetDataToList(mainView);
        int currentID = this.currentBook.getId();
        setData.setChapterList(this.bookInforPanel, bookInforPanel.getListChapter(), currentID);
        setData.setCommentList(this.bookInforPanel, this.bookInforPanel.getListComment(), currentID);

        //Set lại size cho panel chứa list
        JPanel panel = this.bookInforPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));

        this.bookInforPanel.onBtnFirst(e -> {
            try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(currentID, "first"));
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        this.bookInforPanel.onBtnLast(e -> {
            try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(currentID, "last"));
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.bookInforPanel.onBtnSave(e -> {
            SavedModel saved = new SavedModel(mainView.getUserModels().getId(), currentBook.getId());
            SavedDAO.getInstance().savedEvent(saved);
            if (this.bookInforPanel.getBtnSave().getText().equals("Xóa khỏi thư viện")) {
                this.bookInforPanel.getBtnSave().setText("Thêm vào thư viện");
            } else {
                this.bookInforPanel.getBtnSave().setText("Xóa khỏi thư viện");

            }
            this.mainView.repaint();

        });
    }

    public void changeToChapter(ChapterModel changedChapter) throws SQLException {

        ReadingPanel newReadingPanel = new ReadingPanel();
        ReadingController reading = new ReadingController(newReadingPanel, mainView, changedChapter);
        reading.setChapterDetails(changedChapter);
        this.mainView.setMainPanel(newReadingPanel);
    }

    public void setBook(BookModel book) throws IOException, SQLException {
        this.currentBook = book;
        this.bookInforPanel.getTxtName().setText(book.getName());
        this.bookInforPanel.getTxtAuthor().setText(book.getAuthor());
        this.bookInforPanel.getTxtDiscription().setText(book.getDescription());
        ArrayList<String> categoryList = BookDAO.getInstance().getCategoryList(book.getId());
        String currentCategoryList = "";
        for (String category : categoryList) {
            currentCategoryList += category + " ";
        }
        this.bookInforPanel.getTxtCategorys().setText(currentCategoryList);
        this.bookInforPanel.getTxtView().setText(""+BookDAO.getInstance().getView(book.getId()));
        String[] rating = (book.getRatingAverage(book.getId())).split(" ");
        this.bookInforPanel.getTxtRate().setText("" + rating[0] + " sao" + " " + "(" + rating[1] + ")");
        this.bookInforPanel.getImgCover().setIcon(Converter.convertBlobToImageIcon(currentBook.getCover()));
    }
}
