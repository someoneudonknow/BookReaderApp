/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import items.UserItemManager;
import models.UserModels;
import panels.UserMInforPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class UserItemController {
    UserItemManager userItem;
    MainView mainView;
    UserModels userModels;

    public UserItemController(UserItemManager userItem, MainView mainView) {
        this.userItem = userItem;
        this.mainView = mainView;
        
        this.userItem.onBtnInfor(e -> {
            changeInforPanel();
        });
    }
    
    public void changeInforPanel(){
        try {
            UserMInforPanel userInfor = new UserMInforPanel();
            //new BookInforController(bookInforPanel, mainView);
            this.mainView.setMainPanel(userInfor);
        } catch (Exception es) {
            System.out.println("Khong co mainView");
        }
    }

}
