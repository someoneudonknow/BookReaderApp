/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.BookModel;
import models.DAO.BookDAO;
import other.SetDataToList;
import views.panels.AddBookPanel;
import views.panels.BooksPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BooksController {

    private BooksPanel bookPanel;
    private MainView mainView;
    private List<BookModel> allBooks = new ArrayList<>();
    private List<BookModel> searchedData = new ArrayList<>();
    private boolean isASC = false;

    public BooksController(BooksPanel bookPanel, MainView mainView) {
        this.bookPanel = bookPanel;
        this.mainView = mainView;
        this.allBooks.addAll(BookDAO.getInstance().getAll());
        this.searchedData.addAll(allBooks);
        initUI();

        this.bookPanel.onBtnAddBook(e -> {
            AddBook();
        });

        this.bookPanel.onSortBtnClicked(e -> {
            handleSortBooks();
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

    private void handleSortBooks() {
        if (!this.isASC) {
            this.bookPanel.getBtnSort().setText("Z->A");
            this.isASC = true;
            Collections.sort(this.searchedData, (b1, b2) -> {
                return b1.getName().compareTo(b2.getName());
            });
        } else {
            this.bookPanel.getBtnSort().setText("A->Z");
            this.isASC = false;
            Collections.sort(this.searchedData, (b1, b2) -> {
                return b2.getName().compareTo(b1.getName());
            });
        }
        reset(this.searchedData);
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
        if (this.allBooks.isEmpty()) {
            return;
        }
        String data = this.bookPanel.getTxtKeyWords().getText().toLowerCase();
        ArrayList<BookModel> bookList = new ArrayList<>();
        List<BookModel> result = this.allBooks.stream().filter(book -> book.getName().toLowerCase().contains(data.toLowerCase())).collect(Collectors.toList());
        this.searchedData.clear();
        this.searchedData.addAll(result);
        reset(result);
    }

    private void reset(List<BookModel> result) {
        this.bookPanel.getListBook().removeAll();
        SetDataToList setData = new SetDataToList(mainView);
        setData.setBookManagerList(bookPanel.getListBook(), (ArrayList<BookModel>) result);
        this.bookPanel.getListBook().setPreferredSize(new Dimension(0, this.bookPanel.getListBook().getComponentCount() * 66));
    }
}
