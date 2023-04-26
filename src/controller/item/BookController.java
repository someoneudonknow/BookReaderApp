/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.BookInforController;
import views.items.Book;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import models.BookModel;
import models.DAO.ReadingDAO;
import other.Converter;
import views.panels.BookInforPanel;
import views.panels.ReadingPanel;
import views.MainView;
import views.panels.HistoryPanel;

/**
 *
 * @author ADMIN
 */
public class BookController {

    private Book bookItem;
    private MainView mainView;
    private JPanel parentPanel;

    public BookController(Book bookItem, MainView mainView, String option, JPanel parentPanel) {
        this.bookItem = bookItem;
        this.mainView = mainView;
        this.parentPanel = parentPanel;

        setDefaultView(option);
        this.bookItem.onItemClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeToBookInfoPanel();
            }

        });

        this.bookItem.onBtnDelete(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    deleteThisItem();
                } catch (Exception es) {
                    es.printStackTrace();
                }
            }
        });
    }

    public Book getBookItem() {
        return bookItem;
    }

    public void setBookItem(Book bookItem) {
        this.bookItem = bookItem;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void setDefaultView(String option) {
        BookModel currentBook = bookItem.getBookModels();
        bookItem.getjLabel2().setText(currentBook.getName());
        bookItem.getjLabel1().setIcon(Converter.convertBlobToImageIcon(currentBook.getCover()));
        if (!(option.equals("history"))) {
            this.bookItem.getBtnDelete().setVisible(false);
        }
    }

    public void deleteThisItem() {
        JPanel parent = (JPanel) this.bookItem.getParent();
        try {
            ReadingDAO.getInstance().deleteReadHistory(bookItem.getBookModels().getId(), this.mainView.getUserModels().getId());
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        parent.remove(this.bookItem);
        parent.revalidate();
        parent.repaint();
    }

    public void changeToBookInfoPanel() {
        try {
            BookModel book = bookItem.getBookModels();
            BookInforPanel bookInforPanel = new BookInforPanel();
            new BookInforController(bookInforPanel, mainView, book, parentPanel);
            getMainView().setMainPanel(bookInforPanel);
        } catch (Exception es) {
            es.printStackTrace();
        }
    }
}
