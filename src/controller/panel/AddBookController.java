/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

//import java.awt.Color;
//import java.awt.Image;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.BookModel;
import models.CategoryModel;
import models.DAO.CategoryDAO;
import models.DAO.UserDAO;
import models.UserModel;
import other.Converter;
import other.Rules;
import other.Validate;
import views.MainView;
import views.items.CategoryItem;
import views.panels.AddBookPanel;
import views.panels.AddChapterPanel;
import views.panels.BookManagingPanel;

public class AddBookController {

    private AddBookPanel bookPanel;
    private MainView mainView;
    private BookManagingPanel previousPanel;

    public AddBookController(AddBookPanel bookPanel, MainView mainView, BookManagingPanel previousPanel) {
        this.bookPanel = bookPanel;
        this.mainView = mainView;
        this.previousPanel = previousPanel;
        this.setCategoryItemList();

        this.bookPanel.onBtnNext(e -> {
            this.handleNextBtnClicked();
        });

        this.bookPanel.onChooseFile(e -> {
            handleChooseFile();
        });

        this.bookPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bookPanel.getBtnBack().setBackground(Color.red);
                backToPrevious();
            }
        });
    }

    public void handleNextBtnClicked() {
        if (this.isValid()) {
            String bookName = this.bookPanel.getTxtName().getText();
            String author = this.bookPanel.getTxtAuthor().getText();
            String desc = this.bookPanel.getTxtDescription().getText();
            Blob cover = Converter.convertImageToBlob((ImageIcon) this.bookPanel.getImageHolder().getIcon());
            UserModel manager = UserDAO.getInstance().getManagerInfo();
            this.bookPanel.getCateErrMess().setText("");
            BookModel result = new BookModel(bookName, author, cover, desc, manager.getId());
            AddChapterPanel addChapterPanel = new AddChapterPanel();
            try {
                new AddChapterController(addChapterPanel, mainView, result, this.bookPanel);
            } catch (SQLException ex) {
                Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.mainView.setMainPanel(addChapterPanel);
        }
    }

    private boolean isValid() {
        String bookName = this.bookPanel.getTxtName().getText();
        String author = this.bookPanel.getTxtAuthor().getText();
        String desc = this.bookPanel.getTxtDescription().getText();

        Rules[] bookNameRules = {new Rules("Please enter books'name!", Rules.IS_REQUIRED)};
        Rules[] authorRules = {new Rules("Please enter authors'name!", Rules.IS_REQUIRED)};
        Rules[] descRules = {new Rules("Please enter description!", Rules.IS_REQUIRED)};

        Validate bookNameValidate = new Validate(bookName, bookNameRules);
        Validate authorValidate = new Validate(author, authorRules);
        Validate descValidate = new Validate(desc, descRules);

        boolean isBookNameValid = bookNameValidate.isValid();
        boolean isAuthorValid = authorValidate.isValid();
        boolean isDescValid = descValidate.isValid();
        boolean isCategoriesValid = !this.categoryResult().isEmpty();

        this.bookPanel.getBookNameErrorMess().setText(bookNameValidate.getErrorMessage());
        this.bookPanel.getAuthorErrMess().setText(authorValidate.getErrorMessage());
        this.bookPanel.getDescErrMess().setText(descValidate.getErrorMessage());

        if (!isCategoriesValid) {
            this.bookPanel.getCateErrMess().setText("Please choose at least one categories");
        } else {
            this.bookPanel.getCateErrMess().setText("");
        }

        if (isBookNameValid && isAuthorValid && isDescValid && isCategoriesValid) {
            return true;
        }

        return false;
    }

    private void handleChooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("image", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this.mainView, "Save");

        if (x == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            String imagePath = selectedImage.getAbsolutePath();
            if (imagePath.endsWith("png") || imagePath.endsWith("jpg") || imagePath.endsWith("ipeg")) {
                ImageIcon userImage = new ImageIcon(imagePath);
                Image resizedImage = userImage.getImage().getScaledInstance(this.bookPanel.getImageHolder().getWidth(), this.bookPanel.getImageHolder().getHeight(), Image.SCALE_SMOOTH);
                this.bookPanel.getImageHolder().setText("");
                this.bookPanel.getImageHolder().setIcon(new ImageIcon(resizedImage));
            } else {
                JOptionPane.showMessageDialog(this.mainView, "Sai định dạng ảnh!");
            }
        }
    }

    public void setCategoryItemList() {
        ArrayList<CategoryItem> model = new ArrayList<>();
        ArrayList<CategoryModel> cateList = CategoryDAO.getInstance().getAll();

        for (CategoryModel a : cateList) {
            model.add(new CategoryItem(a, false));
        }

        this.bookPanel.setListCategory(model);
    }

    public ArrayList<CategoryModel> categoryResult() {
        ArrayList<CategoryModel> categoryList = new ArrayList<>();

        for (int i = 0; i < this.bookPanel.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.bookPanel.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected()) {
                categoryList.add(item.getCategoryModels());
            }
        }

        return categoryList;
    }

    public BookModel getResult() {
        String name = this.bookPanel.getTxtName().getText();
        String author = this.bookPanel.getTxtAuthor().getText();
        String description = this.bookPanel.getTxtDescription().getText();
        Blob cover = Converter.convertImageToBlob((ImageIcon) this.bookPanel.getImageHolder().getIcon());
        BookModel result = new BookModel(0, name, author, cover, description, 0);

        return result;
    }

    public void backToPrevious() {
        this.mainView.setMainPanel(this.previousPanel);
    }
}
