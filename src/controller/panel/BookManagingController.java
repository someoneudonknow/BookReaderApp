/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import models.BookModel;
import models.DAO.BookDAO;
import other.SetDataToList;
import views.panels.AddBookPanel;
import views.panels.BookManagingPanel;
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
        initUI();
        this.bookPanel.onBtnAddBook(e -> {
            AddBook();
        });
    }

    private void initUI() {
        SetDataToList setData = new SetDataToList(mainView);
        ArrayList<BookModel> books = BookDAO.getInstance().getAll();
        setData.setBookManagerList(bookPanel.getListBook(), books);

        this.bookPanel.getListBook().setPreferredSize(new Dimension(0, this.bookPanel.getListBook().getComponentCount() * 66));
    }

    public void AddBook() {
        AddBookPanel addBookPanel = new AddBookPanel();
        new AddBookController(addBookPanel, mainView, bookPanel);
        this.mainView.setMainPanel(addBookPanel);
    }
}
