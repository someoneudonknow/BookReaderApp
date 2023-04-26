/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.AccountInforController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import views.items.AccountItem;
import models.UserModel;
import views.panels.AccountInforPanel;
import views.MainView;

public class AccountItemController {
    private AccountItem userItem;
    private MainView mainView;
    private UserModel userModels;

    public AccountItemController(AccountItem userItem, MainView mainView, UserModel currentUser) {
        this.userItem = userItem;
        this.mainView = mainView;
        this.userModels = currentUser;
        
        this.userItem.onBtnInfor(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeInforPanel();
            }
            
        });
    }
    
    public void changeInforPanel(){
        try {
            AccountInforPanel userInfor = new AccountInforPanel();
            new AccountInforController(userInfor, this.userModels, this.mainView);
            this.mainView.setMainPanel(userInfor);
        } catch (Exception es) {
            System.out.println("Khong co mainView");
        }
    }
}
