/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.BookEditController;
import views.items.BookItem;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.BookModel;
import models.DAO.BookDAO;
import other.SetDataToList;
import views.panels.BookEditPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookItemController {

    private BookItem bookItem;
    private MainView mainView;
    private BookModel currentBook;

    public BookItemController(BookItem bookItem, MainView mainView, BookModel book) {
        this.bookItem = bookItem;
        this.mainView = mainView;
        this.currentBook = book;

        SetDataToList setData = new SetDataToList(mainView);
        this.bookItem.onBtnEdit(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeInforPanel();
            }

        });

        this.bookItem.onBtnDelete(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteThisItem();
            }
        });
    }

    public void changeInforPanel() {
        try {
            BookEditPanel bookEditPanel = new BookEditPanel();
            new BookEditController(bookEditPanel, mainView, this.currentBook);
            getMainView().setMainPanel(bookEditPanel);
        } catch (Exception es) {
            System.out.println("BookItemManagerController");
        }
    }

    public void deleteThisItem() {
        int x = JOptionPane.showConfirmDialog(mainView, "Are you sure you want to delete this book, all data related to it will be deleted!", "Delete book", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (x == 0) {
            BookDAO.getInstance().delete(this.currentBook.getId());
            
            JPanel parent = (JPanel) this.bookItem.getParent();
            parent.remove(this.bookItem);
            parent.setPreferredSize(new Dimension(0, parent.getComponentCount() * 66));
            parent.revalidate();
            parent.repaint();
        }
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
