/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        this.bookPanel.onBtnSearch(e -> {
            handleSearchEvent();
        });
        this.bookPanel.getTxtKeyWords().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                handleSearchEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleSearchEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleSearchEvent();
            }

            protected void updateFieldState() {
                handleSearchEvent();
            }
        }
        );
    }

    private void initUI() {
        SetDataToList setData = new SetDataToList(mainView);
        ArrayList<BookModel> books = BookDAO.getInstance().getAll();
        setData.setBookManagerList(bookPanel.getListBook(), books, true, null);

        this.bookPanel.getListBook().setPreferredSize(new Dimension(0, this.bookPanel.getListBook().getComponentCount() * 66));
    }

    public void AddBook() {
        AddBookPanel addBookPanel = new AddBookPanel();
        new AddBookController(addBookPanel, mainView, bookPanel);
        this.mainView.setMainPanel(addBookPanel);
    }

    public void handleSearchEvent() {
        this.bookPanel.getListBook().removeAll();
        String data = this.bookPanel.getTxtKeyWords().getText().toLowerCase();
        ArrayList<BookModel> bookList = new ArrayList<>();
        SetDataToList setData = new SetDataToList(mainView);
        setData.setBookManagerList(bookPanel.getListBook(), bookList, false, data);
        this.bookPanel.getListBook().setPreferredSize(new Dimension(0, this.bookPanel.getListBook().getComponentCount() * 66));
    }
}
