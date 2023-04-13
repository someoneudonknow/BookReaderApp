/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.BookInforController;
import views.items.BookItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import models.BookModel;
import other.Converter;
import views.panels.BookInforPanel;
import views.panels.ReadingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookItemController {
    private BookItem bookItem;
    private MainView mainView;

    public BookItemController(BookItem bookItem, MainView mainView) {
        this.bookItem = bookItem;
        this.mainView = mainView;
        BookModel currentBook = bookItem.getBookModels();
        bookItem.getjLabel2().setText(currentBook.getName());
        bookItem.getjLabel1().setIcon(Converter.convertBlobToImageIcon(currentBook.getCover()));
        
        this.bookItem.onItemClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    BookModel book = bookItem.getBookModels();
                    BookInforPanel bookInforPanel = new BookInforPanel();
                    new BookInforController(bookInforPanel, mainView, book);
                    getMainView().setMainPanel(bookInforPanel);
                } catch (Exception es) {
                    System.out.println("Khong co mainView");
                }
            }
            
        });
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
