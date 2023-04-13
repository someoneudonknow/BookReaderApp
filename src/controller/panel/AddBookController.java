/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import models.BookModel;
import models.CategoryModel;
import models.UserModel;
import other.Converter;
import views.MainView;
import views.items.CategoryItem;
import views.panels.AddBookPanel;
import views.panels.AddChapterPanel;

/**
 *
 * @author admin
 */
public class AddBookController {
    private AddBookPanel bookPanel;
    private MainView mainView;

    public AddBookController(AddBookPanel bookPanel, MainView mainView) {
        this.bookPanel = bookPanel;
        this.mainView = mainView;
        this.setCategoryItemList();
        
        this.bookPanel.onBtnNext(e -> {
            categoryResult();
            
//            BookModel result = getResult();
            BookModel result = new BookModel();
            AddChapterPanel addChapterPanel = new AddChapterPanel();
            try {
                //            try {
//                new AddChapterController(addChapterPanel, mainView, result);
//            } catch (SQLException ex) {
//                Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
//            }
new AddChapterController(addChapterPanel, mainView, result);
            } catch (SQLException ex) {
                Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.mainView.setMainPanel(addChapterPanel);
        });
    }
    
    public void setCategoryItemList() {
        ArrayList<CategoryItem> model = new ArrayList<>();
//        cái này để lấy dữ liệu
//        for (CategoryModels i:categoryList){
//            model.add(new CategoryItem(i));
//        }

        //đống này xóa
        model.add(new CategoryItem(new CategoryModel(0, "Trinh thám")));
        model.add(new CategoryItem(new CategoryModel(1, "Lãng mạn")));
        model.add(new CategoryItem(new CategoryModel(2, "Hài hước")));
        model.add(new CategoryItem(new CategoryModel(3, "Học đường")));
        model.add(new CategoryItem(new CategoryModel(4, "Tiểu thuyết")));
        model.add(new CategoryItem(new CategoryModel(5, "Viễn tưởng")));
        model.add(new CategoryItem(new CategoryModel(6, "Cổ tích")));
        model.add(new CategoryItem(new CategoryModel(7, "Sử thi")));
        model.add(new CategoryItem(new CategoryModel(8, "Hành động")));
        model.add(new CategoryItem(new CategoryModel(9, "Đời thường")));
        model.add(new CategoryItem(new CategoryModel(10, "Truck-kun")));
        model.add(new CategoryItem(new CategoryModel(0, "Trinh thám")));
        model.add(new CategoryItem(new CategoryModel(1, "Lãng mạn")));
        model.add(new CategoryItem(new CategoryModel(2, "Hài hước")));
        model.add(new CategoryItem(new CategoryModel(3, "Học đường")));
        model.add(new CategoryItem(new CategoryModel(4, "Tiểu thuyết")));
        model.add(new CategoryItem(new CategoryModel(5, "Viễn tưởng")));
        model.add(new CategoryItem(new CategoryModel(6, "Cổ tích")));
        model.add(new CategoryItem(new CategoryModel(7, "Sử thi")));
        model.add(new CategoryItem(new CategoryModel(8, "Hành động")));
        model.add(new CategoryItem(new CategoryModel(9, "Đời thường")));
        model.add(new CategoryItem(new CategoryModel(10, "Truck-kun")));

        //set dữ liệu
        this.bookPanel.setListCategory(model);
    }
    
    public void categoryResult() {
        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        for (int i = 0; i < this.bookPanel.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.bookPanel.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected())
                categoryList.add(item.getCategoryModels());
        }
        for (CategoryModel i:categoryList)
            System.out.println(i.getName());
    }
    
    public BookModel getResult() {
        String name = this.bookPanel.getTxtName().getText();
        String author = this.bookPanel.getTxtAuthor().getText();
        String description = this.bookPanel.getTxtDescription().getText();
        Blob cover = Converter.convertImageToBlob((ImageIcon)this.bookPanel.getImageHolder().getIcon());
        BookModel result = new BookModel(0, name, author, cover, description, 0);
        
        return result;
    }
}
