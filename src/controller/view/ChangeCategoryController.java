package controller.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import models.BookModel;
import models.CategoryModel;
import models.DAO.BookDAO;
import models.DAO.CategoryDAO;
import models.HaveCategoryModel;
import views.ChangeCategory;
import views.MainView;
import views.items.CategoryItem;

public class ChangeCategoryController {

    private ChangeCategory changeCategory;
    private MainView mainView;
    private BookModel bookModel;
    private List<CategoryModel> allCategories = new LinkedList<>();
    private List<CategoryModel> currentBookCateList = new ArrayList<>();

    public ChangeCategoryController(ChangeCategory changeCategory, MainView mainView, BookModel bookModel, List<CategoryModel> currentBookCateList) {
        this.changeCategory = changeCategory;
        this.mainView = mainView;
        this.bookModel = bookModel;
        this.currentBookCateList.addAll(currentBookCateList);
        
        this.setCategoryItemList();

        this.changeCategory.onBtnCancel(e -> {
            this.changeCategory.setVisible(false);
            this.changeCategory.dispose();
        });
        
        this.changeCategory.start();
    }

    public ArrayList<CategoryModel> resultData() {
        ArrayList<CategoryModel> categoryList = new ArrayList<>();

        for (int i = 0; i < this.changeCategory.getListCategory().getComponentCount(); i++) {
            CategoryItem item = (CategoryItem) this.changeCategory.getListCategory().getComponent(i);
            if (item.getjCheckBox1().isSelected()) {
                categoryList.add(item.getCategoryModels());
            }
        }

        return categoryList;
    }

    private void renderCategories(List<CategoryModel> checkedCategoriesList) {
        ArrayList<CategoryItem> model = new ArrayList<>();

        for (CategoryModel a : this.allCategories) {
            if (checkedCategoriesList.contains(a)) {
                model.add(new CategoryItem(a, true));
            } else {
                model.add(new CategoryItem(a, false));
            }
        }

        this.changeCategory.setListCategory(model);
    }

    private void setCategoryItemList() {
        ArrayList<CategoryModel> cateList = CategoryDAO.getInstance().getAll();
        this.allCategories.addAll(cateList);
        this.renderCategories(this.currentBookCateList);
    }
    
    public void setCategoryItemList(List<CategoryModel> checkedList) {
        this.renderCategories(checkedList);
    }
}
