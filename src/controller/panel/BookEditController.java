/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import controller.view.ChangeCategoryController;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import models.BookModel;
import models.DAO.BookDAO;
import other.SetDataToList;
import views.ChangeCategory;
import views.panels.AddChapterPanel;
import views.panels.BookEditPanel;
import views.panels.BookInforPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookEditController {

    private BookEditPanel bookEditPanel;
    private MainView mainView;
    private BookModel currentBook;

    public BookEditController(BookEditPanel bookEditPanel, MainView mainView, BookModel book) throws SQLException, ParseException {
        this.bookEditPanel = bookEditPanel;
        this.mainView = mainView;
        this.currentBook = book;

        initUI();

        this.bookEditPanel.onBtnAddChapter(e -> {
            AddChapter();
        });

        this.bookEditPanel.onBtnSave(e -> {
            Save();
        });
        
        this.bookEditPanel.onBtnChangeCategory(e -> {
            ChangeCategory();
        });
    }

    private void initUI() throws ParseException {
        BookDAO bookDAO = new BookDAO();
        List<String> categories = null;
        SetDataToList setData = new SetDataToList(mainView);
        StringBuilder cateString = new StringBuilder();
        String rating = "";

        try {
            categories = bookDAO.getCategoryList(this.currentBook.getId());
            rating = bookDAO.getRatingAverage(this.currentBook.getId());
            setData.setChapterList(this.bookEditPanel, this.bookEditPanel.getListChapter(), this.currentBook.getId());
            setData.setCommentList(this.bookEditPanel, this.bookEditPanel.getListComment(), this.currentBook.getId());
        } catch (SQLException ex) {
        }

        for (String s : categories) {
            cateString.append(s).append(", ");
        }
        
        cateString.setCharAt(cateString.lastIndexOf(","), ' ');
        
        this.bookEditPanel.getTxtName().setText(this.currentBook.getName());
        this.bookEditPanel.getTxtAuthor().setText(this.currentBook.getAuthor());
        this.bookEditPanel.getTxtDiscription().setText(this.currentBook.getDescription());
        this.bookEditPanel.getTxtCategorys().setText(cateString.toString());
        this.bookEditPanel.getTxtRate().setText(rating);
        
        JPanel panel = this.bookEditPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));
        
        JPanel panel1 = this.bookEditPanel.getListComment();
        panel1.setPreferredSize(new Dimension(0, panel1.getComponentCount() * 40));
        
        this.bookEditPanel.onBtnChangeCategory(e -> {
            ChangeCategory();
        });
        
    }

    public void AddChapter() {
        AddChapterPanel chapterPanel = new AddChapterPanel();
        this.mainView.setMainPanel(chapterPanel);
    }

    public void Save() {

    }
    
    public void ChangeCategory() {
        ChangeCategory changeCategory = new ChangeCategory();
        new ChangeCategoryController(changeCategory, mainView, currentBook);
    }
}
