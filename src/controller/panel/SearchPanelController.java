/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.sql.SQLException;
import views.items.BookItem;
import views.items.CategoryItem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BookModel;
import models.CategoryModel;
import models.HaveCategoryModel;
import other.SetDataToList;
import views.panels.SearchPanel;
import views.MainView;

public class SearchPanelController {
    SearchPanel searchPanel;
    MainView mainView;
    ArrayList<CategoryModel> categoryList;
    ArrayList<HaveCategoryModel> haveCategoryList;
    ArrayList<BookModel> bookList;

    public SearchPanelController(SearchPanel searchPanel, MainView mainView) {
        this.searchPanel = searchPanel;
        this.mainView = mainView;
        this.setCategoryItemList();
        this.searchPanel.onBtnSearch(e ->{
            try {
                searchResult();
            } catch (SQLException ex) {
                Logger.getLogger(SearchPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public SearchPanelController(SearchPanel searchPanel, MainView mainView, ArrayList<CategoryModel> categoryList, ArrayList<HaveCategoryModel> haveCategoryList, ArrayList<BookModel> bookList) {
        this.searchPanel = searchPanel;
        this.mainView = mainView;
        this.categoryList = categoryList;
        this.haveCategoryList = haveCategoryList;
        this.bookList = bookList;
        this.searchPanel.onBtnSearch(e ->{
            try {
                searchResult();
            } catch (SQLException ex) {
                Logger.getLogger(SearchPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void searchResult() throws SQLException {
        String keyword = this.searchPanel.getTxtKeyword().getText();
        int type = this.searchPanel.getBoxType().getSelectedIndex();
        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        for (int i = 0; i < this.searchPanel.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.searchPanel.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected())
                categoryList.add(item.getCategoryModels());
        }
        System.out.println(keyword + "\t" + type);
        for (CategoryModel i:categoryList)
            System.out.println(i.getName());
        getBookItemListResult(keyword, type, categoryList);
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
        this.searchPanel.setListCategory(model);
    }
    
    public void getBookItemListResult(String keyword, int type, ArrayList<CategoryModel> categoryList) throws SQLException {
        ArrayList<BookItem> listResult = new ArrayList<>();
        
        try {
            for (BookModel i:bookList) {
            if (checkKeyword(keyword, type)){
                if (checkCategory(i.getId(), categoryList))
                    listResult.add(new BookItem(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Danh sach trong");
        }
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(searchPanel, null);
    }
    
    public boolean checkKeyword(String keyword, int type) {
        try {
            switch (type) {
            case 0:
                for (BookModel i:bookList) {
                    if (i.getName().contains(keyword))
                        return true;
                }
                break;
            case 1:
                for (BookModel i:bookList) {
                    if (i.getAuthor().contains(keyword))
                        return true;
                }
                break;
            };
        } catch (Exception e) {
            System.out.println("Danh sach trong");
        }
        return false;
    }
    
    public boolean checkCategory(int id, ArrayList<CategoryModel> list) {
        int total = list.size();
        for (CategoryModel i:list) {
            for (HaveCategoryModel j:haveCategoryList) {
                if (id == j.getBook_id() && i.getId() == j.getCategory_id())
                    total--;
            }
        }
        return (total == 0)?true:false;
    }
}
