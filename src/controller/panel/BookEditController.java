/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import controller.view.ChangeCategoryController;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.BookModel;
import models.CategoryModel;
import models.DAO.BookDAO;
import models.DAO.HaveCategoryDAO;
import models.DAO.UserDAO;
import other.Converter;
import other.SetDataToList;
import views.ChangeCategory;
import views.panels.AddChapterPanel;
import views.panels.BookEditPanel;
import views.MainView;
import views.panels.BooksPanel;

/**
 *
 * @author ADMIN
 */
public class BookEditController {
    private BookEditPanel bookEditPanel;
    private MainView mainView;
    private BookModel currentBook;

    private List<CategoryModel> currentBookCategories = new ArrayList<>();
    private List<CategoryModel> oldBookCategories = new ArrayList<>();
    private boolean isCoverChanged = false;
    private boolean isCategoriesChanged = false;

    public BookEditController(BookEditPanel bookEditPanel, MainView mainView, BookModel book) throws SQLException, ParseException {
        this.bookEditPanel = bookEditPanel;
        this.mainView = mainView;
        this.currentBook = book;
        List<CategoryModel> cateListFromCurrentBook = BookDAO.getInstance().getCurrentBookCategories(this.currentBook.getId());
        this.currentBookCategories.addAll(cateListFromCurrentBook);
        this.oldBookCategories.addAll(cateListFromCurrentBook);
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

        this.bookEditPanel.onBtnChangedCover(e -> {
            handleChangedCover();
        });

        this.bookEditPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
            
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
        String ratingFormated = "";

        if (!rating.isBlank()) {
            ratingFormated = rating.strip().replace(" ", " ( ") + ")";
        } else {
            ratingFormated = "No rating yet!";
        }

        cateString.setCharAt(cateString.lastIndexOf(","), ' ');

        this.bookEditPanel.getTxtName().setText(this.currentBook.getName());
        this.bookEditPanel.getTxtAuthor().setText(this.currentBook.getAuthor());
        this.bookEditPanel.getTxtDiscription().setText(this.currentBook.getDescription());
        this.bookEditPanel.getTxtCategorys().setText(cateString.toString());
        this.bookEditPanel.getTxtRate().setText(ratingFormated);
        if (this.currentBook.getCover() != null) {
            this.bookEditPanel.getImgCover().setIcon(Converter.convertBlobToImageIcon(this.currentBook.getCover()));
        }

        JPanel panel = this.bookEditPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 50));

        JPanel panel1 = this.bookEditPanel.getListComment();
        panel1.setPreferredSize(new Dimension(0, panel1.getComponentCount() * 50));
    }

    public void AddChapter() {
        AddChapterPanel chapterPanel = new AddChapterPanel();
        try {
            new AddChapterController(chapterPanel, mainView, currentBook, this.bookEditPanel);
        } catch (SQLException ex) {
            Logger.getLogger(BookEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mainView.setMainPanel(chapterPanel);
    }

    private void Save() {
        int managerId = UserDAO.getInstance().getManagerInfo().getId();
        String bookName = this.bookEditPanel.getTxtName().getText();
        String bookAuthor = this.bookEditPanel.getTxtAuthor().getText();
        String bookDesc = this.bookEditPanel.getTxtDiscription().getText();
        Blob bookCover = Converter.convertImageToBlob((ImageIcon) this.bookEditPanel.getImgCover().getIcon());
        BookDAO bookDAO = new BookDAO();
        BookModel editedBook = new BookModel(this.currentBook.getId(), bookName, bookAuthor, bookCover, bookDesc, managerId);

        try {
            bookDAO.update(this.currentBook.getId(), editedBook, this.getChangedField());
            if (this.isCategoriesChanged) {
                HaveCategoryDAO.getInstance().updateChangedCategoriesOfBook(this.currentBook.getId(), this.currentBookCategories);
                this.oldBookCategories = this.currentBookCategories;
            }
            
            JOptionPane.showMessageDialog(this.mainView, "Saved data");
            
            this.currentBook = editedBook;
            this.isCoverChanged = false;
            this.isCategoriesChanged = false;
        } catch (Exception ex) {
            if (ex.getMessage().equals("data_unchanged")) {
                JOptionPane.showMessageDialog(this.mainView, "The data hasn't changed, do you still wanna save it?");
            } else if (ex.getMessage().equals("book_name_exists")) {
                JOptionPane.showMessageDialog(this.mainView, "Book's name already exists!");
            }
        }
    }

    private void handleChangedCover() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("image", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this.bookEditPanel, "Save");

        if (x == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            String imagePath = selectedImage.getAbsolutePath();
            if (imagePath.endsWith("png") || imagePath.endsWith("jpg") || imagePath.endsWith("ipeg")) {
                ImageIcon userImage = new ImageIcon(imagePath);
                Image resizedImage = userImage.getImage().getScaledInstance(this.bookEditPanel.getImgCover().getWidth(), this.bookEditPanel.getImgCover().getHeight(), Image.SCALE_SMOOTH);
                this.bookEditPanel.getImgCover().setIcon(new ImageIcon(resizedImage));
                this.isCoverChanged = true;
            } else {
                JOptionPane.showMessageDialog(this.bookEditPanel, "Wrong image format!");
            }
        }
    }

    private List<String> getChangedField() {
        List<String> changedFields = new LinkedList<>();

        String bookName = this.bookEditPanel.getTxtName().getText();
        String author = this.bookEditPanel.getTxtAuthor().getText();
        String desc = this.bookEditPanel.getTxtDiscription().getText();

        boolean isBookNameChanged = !this.currentBook.getName().contentEquals(bookName);
        boolean isAuthorChanged = !this.currentBook.getAuthor().contentEquals(author);
        boolean isDescChanged = !this.currentBook.getDescription().contentEquals(desc);

        if (isBookNameChanged) {
            changedFields.add("book_name");
        }

        if (isAuthorChanged) {
            changedFields.add("book_author");
        }

        if (isDescChanged) {
            changedFields.add("book_description");
        }

        if (this.isCoverChanged) {
            changedFields.add("book_cover");
        }

        if (this.isCategoriesChanged) {
            changedFields.add("book_categories");
        }

        return changedFields;
    }

    public void ChangeCategory() {
        ChangeCategory changeCategory = new ChangeCategory();
        ChangeCategoryController ctController = new ChangeCategoryController(changeCategory, mainView, currentBook, this.currentBookCategories);

        changeCategory.onBtnConfirm(e -> {
            ArrayList<CategoryModel> result = ctController.resultData();
            boolean isValid = false;

            if (result.size() <= 0) {
                changeCategory.getCateErrorMessage().setText("You have to choose at least one categories!");
                isValid = false;
            } else {
                isValid = true;
                changeCategory.getCateErrorMessage().setText("");
                boolean isCategoriesChanged = !(result.containsAll(this.oldBookCategories) && this.oldBookCategories.containsAll(result));
                this.isCategoriesChanged = isCategoriesChanged;
            }

            if (isValid) {
                StringBuilder cateStringUpdated = new StringBuilder();
                for (CategoryModel i : result) {
                    cateStringUpdated.append(i.getName()).append(",");
                }
                cateStringUpdated.setCharAt(cateStringUpdated.lastIndexOf(","), ' ');
                this.bookEditPanel.getTxtCategorys().setText(cateStringUpdated.toString().strip());
                this.currentBookCategories = result;
                changeCategory.setVisible(false);
                changeCategory.dispose();
            }
        });

        changeCategory.onResetBtnClicked(e -> {
            List<CategoryModel> cateListFromCurrentBook = BookDAO.getInstance().getCurrentBookCategories(this.currentBook.getId());
            ctController.setCategoryItemList(cateListFromCurrentBook);
            this.currentBookCategories = cateListFromCurrentBook;
        });
    }

    public void backToPrevious() {
        BooksPanel previousPanel = new BooksPanel();
        new BooksController(previousPanel, mainView);
        this.mainView.setMainPanel(previousPanel);
    }
}
