/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import models.UserModel;
import other.Converter;
import views.panels.UserMInforPanel;

public class UserInformationController {
    private UserMInforPanel infoPanel;
    private UserModel currentUser;
    
    public UserInformationController(UserMInforPanel infoPanel, UserModel user) {
        this.infoPanel = infoPanel;
        this.currentUser = user;
        this.initUI();
    }
    
    private void initUI() {
        this.infoPanel.getUserNameInput().setText(this.currentUser.getUserName());
        this.infoPanel.getPhoneNumberInput().setText(this.currentUser.getPhoneNumber());
        this.infoPanel.getPasswordInput().setText(this.currentUser.getPassword());
        this.infoPanel.getImageHolder().setIcon(Converter.convertBlobToImageIcon(this.currentUser.getAvatar()));
    }
}
