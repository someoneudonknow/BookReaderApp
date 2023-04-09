/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.BookInforController;
import items.BookItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import panels.BookInforPanel;
import panels.ReadingPanel;
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
        this.bookItem.onItemClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    BookInforPanel bookInforPanel = new BookInforPanel();
                    new BookInforController(bookInforPanel, mainView);
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
