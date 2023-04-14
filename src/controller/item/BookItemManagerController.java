/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.BookEditController;
import views.items.BookItemManager;
import java.awt.Dimension;
import javax.swing.JPanel;
import models.BookModel;
import other.SetDataToList;
import views.panels.BookEditPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookItemManagerController {
    private BookItemManager bookItem;
    private MainView mainView;
    private BookModel currentBook;
    
    public BookItemManagerController(BookItemManager bookItem, MainView mainView, BookModel book) {
        this.bookItem = bookItem;
        this.mainView = mainView;
        this.currentBook = book;
        
        SetDataToList setData = new SetDataToList(mainView);
        this.bookItem.onBtnEdit(e -> {
            changeInforPanel();
        });
        
        this.bookItem.onBtnDelete(e -> {
            deleteThisItem();
        });
    }
    
    public void changeInforPanel(){
        try {
            BookEditPanel bookEditPanel = new BookEditPanel();
            new BookEditController(bookEditPanel, mainView, this.currentBook);
            getMainView().setMainPanel(bookEditPanel);
        } catch (Exception es) {
            System.out.println("Khong co mainView");
        }
    }

    public void deleteThisItem(){
        JPanel parent = (JPanel) this.bookItem.getParent();
        parent.remove(this.bookItem);
        parent.setPreferredSize(new Dimension(0,parent.getComponentCount()*78));
        parent.revalidate();
        parent.repaint();
    }
    public BookItemManager getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItemManager bookItem) {
        this.bookItem = bookItem;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
    
}
