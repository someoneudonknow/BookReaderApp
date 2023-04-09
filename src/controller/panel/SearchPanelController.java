/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import views.items.BookItem;
import views.items.CategoryItem;
import java.util.ArrayList;
import models.BookModels;
import models.CategoryModels;
import models.HaveCategoryModels;
import other.SetDataToList;
import views.panels.SearchPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class SearchPanelController {
    SearchPanel searchPanel;
    MainView mainView;
    ArrayList<CategoryModels> categoryList;
    ArrayList<HaveCategoryModels> haveCategoryList;
    ArrayList<BookModels> bookList;

    public SearchPanelController(SearchPanel searchPanel, MainView mainView) {
        this.searchPanel = searchPanel;
        this.mainView = mainView;
        this.setCategoryItemList();
        this.searchPanel.onBtnSearch(e ->{
            searchResult();
        });
    }

    public SearchPanelController(SearchPanel searchPanel, MainView mainView, ArrayList<CategoryModels> categoryList, ArrayList<HaveCategoryModels> haveCategoryList, ArrayList<BookModels> bookList) {
        this.searchPanel = searchPanel;
        this.mainView = mainView;
        this.categoryList = categoryList;
        this.haveCategoryList = haveCategoryList;
        this.bookList = bookList;
        this.searchPanel.onBtnSearch(e ->{
            searchResult();
        });
    }
    
    public void searchResult() {
        String keyword = this.searchPanel.getTxtKeyword().getText();
        int type = this.searchPanel.getBoxType().getSelectedIndex();
        ArrayList<CategoryModels> categoryList = new ArrayList<>();
        for (int i = 0; i < this.searchPanel.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.searchPanel.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected())
                categoryList.add(item.getCategoryModels());
        }
        System.out.println(keyword + "\t" + type);
        for (CategoryModels i:categoryList)
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
        model.add(new CategoryItem(new CategoryModels(0, "Trinh thám")));
        model.add(new CategoryItem(new CategoryModels(1, "Lãng mạn")));
        model.add(new CategoryItem(new CategoryModels(2, "Hài hước")));
        model.add(new CategoryItem(new CategoryModels(3, "Học đường")));
        model.add(new CategoryItem(new CategoryModels(4, "Tiểu thuyết")));
        model.add(new CategoryItem(new CategoryModels(5, "Viễn tưởng")));
        model.add(new CategoryItem(new CategoryModels(6, "Cổ tích")));
        model.add(new CategoryItem(new CategoryModels(7, "Sử thi")));
        model.add(new CategoryItem(new CategoryModels(8, "Hành động")));
        model.add(new CategoryItem(new CategoryModels(9, "Đời thường")));
        model.add(new CategoryItem(new CategoryModels(10, "Truck-kun")));
        model.add(new CategoryItem(new CategoryModels(0, "Trinh thám")));
        model.add(new CategoryItem(new CategoryModels(1, "Lãng mạn")));
        model.add(new CategoryItem(new CategoryModels(2, "Hài hước")));
        model.add(new CategoryItem(new CategoryModels(3, "Học đường")));
        model.add(new CategoryItem(new CategoryModels(4, "Tiểu thuyết")));
        model.add(new CategoryItem(new CategoryModels(5, "Viễn tưởng")));
        model.add(new CategoryItem(new CategoryModels(6, "Cổ tích")));
        model.add(new CategoryItem(new CategoryModels(7, "Sử thi")));
        model.add(new CategoryItem(new CategoryModels(8, "Hành động")));
        model.add(new CategoryItem(new CategoryModels(9, "Đời thường")));
        model.add(new CategoryItem(new CategoryModels(10, "Truck-kun")));

        //set dữ liệu
        this.searchPanel.setListCategory(model);
    }
    
    public void getBookItemListResult(String keyword, int type, ArrayList<CategoryModels> categoryList) {
        ArrayList<BookItem> listResult = new ArrayList<>();
        
        try {
            for (BookModels i:bookList) {
            if (checkKeyword(keyword, type)){
                if (checkCategory(i.getId(), categoryList))
                    listResult.add(new BookItem(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Danh sach trong");
        }
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(searchPanel);
    }
    
    public boolean checkKeyword(String keyword, int type) {
        try {
            switch (type) {
            case 0:
                for (BookModels i:bookList) {
                    if (i.getName().contains(keyword))
                        return true;
                }
                break;
            case 1:
                for (BookModels i:bookList) {
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
    
    public boolean checkCategory(int id, ArrayList<CategoryModels> list) {
        int total = list.size();
        for (CategoryModels i:list) {
            for (HaveCategoryModels j:haveCategoryList) {
                if (id == j.getBook_id() && i.getId() == j.getCategory_id())
                    total--;
            }
        }
        return (total == 0)?true:false;
    }
}
