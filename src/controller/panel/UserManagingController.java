/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.DAO.UserDAO;
import models.UserModel;
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
        this.initData();
        this.userPanel.onBtnSearch(e ->{
            userManagingSearch();
        });
        this.userPanel.getTxtKeyWords().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                userManagingSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userManagingSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                userManagingSearch();
            }

            protected void updateFieldState() {
                userManagingSearch();
            }
        }
    );
}

    private void initData() {
        UserDAO userDAO = new UserDAO();
        ArrayList<UserModel> userList = userDAO.getAll();
        SetDataToList setData = new SetDataToList(mainView);
        setData.setUserItemList(userPanel.getListUser(), userList);

        this.userPanel.getListUser().setPreferredSize(new Dimension(0, this.userPanel.getListUser().getComponentCount() * 66));
    }
    
    public void userManagingSearch(){
        this.userPanel.getListUser().removeAll();
        String keyword = this.userPanel.getTxtKeyWords().getText();
        ArrayList<UserModel> result = UserDAO.getInstance().search(keyword);
        SetDataToList setData = new SetDataToList(mainView);
        setData.setUserItemList(userPanel.getListUser(), result);

        this.userPanel.getListUser().setPreferredSize(new Dimension(0, this.userPanel.getListUser().getComponentCount() * 66));
    }
}
