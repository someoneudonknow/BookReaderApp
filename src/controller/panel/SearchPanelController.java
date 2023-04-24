/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.sql.SQLException;
import java.time.Clock;
import views.items.BookItem;
import views.items.CategoryItem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.BookModel;
import models.CategoryModel;
import models.DAO.BookDAO;
import models.DAO.CategoryDAO;
import models.DAO.HaveCategoryDAO;
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
                //                searchResult();
                getDataAndFind();
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
        this.searchPanel.removeAll();
        ArrayList<CategoryModel> model = CategoryDAO.getInstance().getAll();
          ArrayList<CategoryItem> categoryList = new ArrayList<CategoryItem>() ;
          for(int i = 0;i < model.size();i++){
              categoryList.add(0, new CategoryItem(model.get(i)));
          }
          //set dữ liệu
        this.searchPanel.setListCategory(categoryList);
//        ArrayList<CategoryItem> model = new ArrayList<>();

    }
    
    public void getDataAndFind() throws SQLException{
        this.searchPanel.getListResult().removeAll();
        String type = (String)this.searchPanel.getBoxType().getSelectedItem();
        String sort = (String)this.searchPanel.getjComboBox1().getSelectedItem();
        String name = this.searchPanel.getTxtKeyword().getText().toLowerCase();
        ArrayList<CategoryItem> categoryList = new ArrayList<>();
//       //get available data
       for (int i = 0; i < this.searchPanel.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.searchPanel.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected())
                categoryList.add(item);
        }
       for(CategoryItem i: categoryList)
            System.out.println(i.getCategoryModels().getName());
       ArrayList<BookModel> temp = BookDAO.getInstance().getDataAvailable(sort, type, name, categoryList);
       
       //convert from BookModel to BookItem
       ArrayList<BookItem> result = new ArrayList<BookItem>();
       for(int i = 0;i < temp.size(); i++){
           result.add(i, new BookItem(temp.get(i)));
       }
  
       
       //Render data
//       this.searchPanel.setListResult(result);
       
        SetDataToList setData = new SetDataToList(this.mainView);
        //            setData.setBookItemList(searchPanel.getListResult(), "full", searchPanel);
        setData.SetSearchDataToList(searchPanel.getListResult(), this.searchPanel, name, type, sort, categoryList);
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
        setData.setBookItemList(searchPanel.getListResult(), null, searchPanel);
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
