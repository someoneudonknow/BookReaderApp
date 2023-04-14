/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import views.items.CommentItem;
import java.awt.Dimension;
import javax.swing.JPanel;
import views.panels.BookEditPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class CommentItemController {
    private CommentItem commentItem;
    private MainView mainView;

    public CommentItemController(JPanel parent, CommentItem commentItem, MainView mainView) {
        this.commentItem = commentItem;
        this.mainView = mainView;
        this.commentItem.setPreferredSize(this.commentItem.getPreferredSize());
        if (!(parent instanceof BookEditPanel)) {
            this.commentItem.getBtnDelete().setVisible(false);
        }

        this.commentItem.onBtnDeleteClick(e -> {
            DeleteThisComment();
        });

    }
    
    public void DeleteThisComment(){
        JPanel parent = (JPanel) this.commentItem.getParent();
        parent.remove(this.commentItem);
        parent.setPreferredSize(new Dimension(0,parent.getComponentCount()*40));
        parent.revalidate();
        parent.repaint();
    }



    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
    
    
}
