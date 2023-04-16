/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.view;

import java.util.ArrayList;
import models.BookModel;
import models.CategoryModel;
import models.HaveCategoryModel;
import views.ChangeCategory;
import views.MainView;
import views.items.CategoryItem;

/**
 *
 * @author ADMIN
 */
public class ChangeCategoryController {
    ChangeCategory changeCategory;
    MainView mainView;
    BookModel bookModel;

    public ChangeCategoryController(ChangeCategory changeCategory, MainView mainView, BookModel bookModel) {
        this.changeCategory = changeCategory;
        this.mainView = mainView;
        this.bookModel = bookModel;
        
        setCategoryItemList();
        
        this.changeCategory.onBtnConfirm(e -> {
            this.changeCategory.setVisible(false);
            this.changeCategory.dispose();
        });
        
        this.changeCategory.onBtnCancel(e -> {
            this.changeCategory.setVisible(false);
            this.changeCategory.dispose();
        });

        this.changeCategory.start();
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
        this.changeCategory.setListCategory(model);
    }
}
