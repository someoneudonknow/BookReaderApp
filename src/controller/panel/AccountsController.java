/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.BookModel;
import models.DAO.UserDAO;
import models.UserModel;
import other.SetDataToList;
import views.panels.AccountsPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class AccountsController {

    private AccountsPanel userPanel;
    private MainView mainView;
    private boolean isASC = true;
    private List<UserModel> allUsers = new ArrayList<>();
    private List<UserModel> searchedData = new ArrayList<>();

    public AccountsController(AccountsPanel userPanel, MainView mainView) {
        this.userPanel = userPanel;
        this.mainView = mainView;
        this.allUsers.addAll(UserDAO.getInstance().getAll());
        this.searchedData.addAll(this.allUsers);
        this.initData();
        
        this.userPanel.onSortBtnClicked(e -> {
            handleSortUsers();
        });

        this.userPanel.getTxtKeyWords().getDocument().addDocumentListener(new DocumentListener() {
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

    private void handleSortUsers() {
        if (this.isASC) {
            this.userPanel.getBtnSort().setText("Z->A");
            this.isASC = false;
            Collections.sort(this.searchedData, (b1, b2) -> {
                return b1.getUserName().compareTo(b2.getUserName());
            });
        } else {
            this.userPanel.getBtnSort().setText("A->Z");
            this.isASC = true;
            Collections.sort(this.searchedData, (b1, b2) -> {
                return b2.getUserName().compareTo(b1.getUserName());
            });
        }
        reset(this.searchedData);
    }

    private void initData() {
        reset(this.allUsers);
    }

    public void userManagingSearch() {
        String keyword = this.userPanel.getTxtKeyWords().getText();
        List<UserModel> result = this.allUsers.stream().filter(user -> user.getUserName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        this.searchedData.clear();
        this.searchedData.addAll(result);
        reset(result);
    }

    private void reset(List<UserModel> users) {
        this.userPanel.getListUser().removeAll();
        SetDataToList setData = new SetDataToList(mainView);
        setData.setUserItemList(userPanel.getListUser(), users);
        this.userPanel.getListUser().setPreferredSize(new Dimension(0, this.userPanel.getListUser().getComponentCount() * 66));
    }
}
