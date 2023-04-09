/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import other.SetDataToList;
import panels.AddBookPanel;
import panels.BookManagingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookManagingController {
    private BookManagingPanel bookPanel;
    private MainView mainView;

    public BookManagingController(BookManagingPanel bookPanel, MainView mainView) {
        this.bookPanel = bookPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(mainView);
        setData.setBookManagerList(bookPanel.getListBook());
        
        this.bookPanel.getListBook().setPreferredSize(new Dimension(0,this.bookPanel.getListBook().getComponentCount()*78));
        
        this.bookPanel.onBtnAddBook(e -> {
            AddBook();
        });
    }
    
    public void AddBook() {
        this.mainView.setMainPanel(new AddBookPanel());
    }
}
