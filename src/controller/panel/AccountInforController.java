/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import models.UserModel;
import other.Converter;
import views.MainView;
import views.panels.AccountInforPanel;
import views.panels.AccountsPanel;

public class AccountInforController {
    private AccountInforPanel infoPanel;
    private UserModel currentUser;
    private MainView mainView;
    
    public AccountInforController(AccountInforPanel infoPanel, UserModel user, MainView mainView) {
        this.infoPanel = infoPanel;
        this.currentUser = user;
        this.mainView = mainView;
        this.initUI();
        
        this.infoPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
            
        });
    }
    
    private void initUI() {
        this.infoPanel.getUserNameInput().setText(this.currentUser.getUserName());
        this.infoPanel.getPhoneNumberInput().setText(this.currentUser.getPhoneNumber());
        this.infoPanel.getPasswordInput().setText(this.currentUser.getPassword());
        this.infoPanel.getImageHolder().setIcon(Converter.convertBlobToImageIcon(this.currentUser.getAvatar()));
    }
    
    public void backToPrevious() {
        AccountsPanel userPanel = new AccountsPanel();
            new AccountsController(userPanel, this.mainView);
            this.mainView.setMainPanel(userPanel);
    }
}
