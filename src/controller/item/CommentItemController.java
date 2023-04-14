/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import views.items.CommentItem;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.BookModel;
import models.DAO.ReviewDAO;
import models.ReviewModel;
import models.UserModel;
import models.entityPK.ReviewPK;
import views.panels.BookEditPanel;
import views.MainView;

public class CommentItemController {
    
    private CommentItem commentItem;
    private MainView mainView;
    private ReviewModel currentReviewModel;
    
    public CommentItemController(JPanel parent, CommentItem commentItem, MainView mainView, ReviewModel commentModel) {
        this.commentItem = commentItem;
        this.mainView = mainView;
        this.currentReviewModel = commentModel;
        this.commentItem.setPreferredSize(this.commentItem.getPreferredSize());

        if (!(parent instanceof BookEditPanel)) {
            this.commentItem.getBtnDelete().setVisible(false);
        }

        this.commentItem.onBtnDeleteClick(e -> {
            DeleteThisComment();
        });
    }

    public void DeleteThisComment() {
        int x = JOptionPane.showConfirmDialog(this.mainView, "Are you sure want to delete this comment?");
        if (x == 0) {
            int userId = this.currentReviewModel.getUser_id();
            int bookId = this.currentReviewModel.getBook_id();
            
            ReviewDAO rvDAO = new ReviewDAO();
            rvDAO.delete(new ReviewPK(userId, bookId));
            JPanel parent = (JPanel) this.commentItem.getParent();
            parent.remove(this.commentItem);
            parent.setPreferredSize(new Dimension(0, parent.getComponentCount() * 40));
            parent.revalidate();
            parent.repaint();
        }
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
