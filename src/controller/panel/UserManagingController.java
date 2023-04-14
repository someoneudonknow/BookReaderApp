/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import other.SetDataToList;
import views.panels.UserManagingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class UserManagingController {
    private UserManagingPanel userPanel;
    private MainView mainView;

    public UserManagingController(UserManagingPanel userPanel, MainView mainView) {
        this.userPanel = userPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(mainView);
        setData.setUserItemList(userPanel.getListUser());
        
        this.userPanel.getListUser().setPreferredSize(new Dimension(0,this.userPanel.getListUser().getComponentCount()*78));
    }
    
}
