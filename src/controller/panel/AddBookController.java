/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.BookModel;
import models.CategoryModel;
import models.DAO.CategoryDAO;
import models.UserModel;
import other.Converter;
import views.MainView;
import views.items.CategoryItem;
import views.panels.AddBookPanel;
import views.panels.AddChapterPanel;
import views.panels.BookManagingPanel;

/**
 *
 * @author admin
 */
public class AddBookController {

    private AddBookPanel bookPanel;
    private MainView mainView;
    private BookManagingPanel previousPanel;

    public AddBookController(AddBookPanel bookPanel, MainView mainView, BookManagingPanel previousPanel) {
        this.bookPanel = bookPanel;
        this.mainView = mainView;
        this.setCategoryItemList();

        this.bookPanel.onBtnNext(e -> {
            if (categoryResult().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Chưa chọn thể loại");
            } else {
                //BookModel result = getResult();
                BookModel result = new BookModel();
                AddChapterPanel addChapterPanel = new AddChapterPanel();
                try {
                    new AddChapterController(addChapterPanel, mainView, result, this.bookPanel);
                } catch (SQLException ex) {
                    Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.mainView.setMainPanel(addChapterPanel);
            }

        });

        this.bookPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
        });
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
