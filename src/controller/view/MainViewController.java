/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.view;

import controller.panel.BookManagingController;
import controller.panel.MainPanelController;
import controller.panel.SearchPanelController;
import controller.panel.HistoryController;
import controller.panel.LibraryController;
import controller.panel.UserManagingController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import views.panels.AllBookPanel;
import views.panels.BookManagingPanel;
import views.panels.HistoryPanel;
import views.panels.InforPanel;
import views.LoginForm;
import views.panels.MainPanel;
import views.MainView;
import views.panels.SearchPanel;
import views.panels.LibraryPanel;
import views.panels.ReadingPanel;
import views.panels.UserManagingPanel;

/**
 *
 * @author ADMIN
 */
public class MainViewController {
    private MainView mainView;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;
        MainPanel panel = new MainPanel();
        new MainPanelController(panel, this.mainView);
        if (this.mainView.getUserModels().getUserName().equals("admin")){
            this.mainView.getBtnHistory().setVisible(false);
            this.mainView.getBtnInFor().setVisible(false);
            this.mainView.getBtnLibrary().setVisible(false);
            this.mainView.getBtnMain().setVisible(false);
            this.mainView.getBtnSearch().setVisible(false);
            
            BookManagingPanel panel1 = new BookManagingPanel();
            new BookManagingController(panel1, this.mainView);
            changePanel(panel1);
        }
        else {
            this.mainView.getBtnBookManager().setVisible(false);
            this.mainView.getBtnUserManager().setVisible(false);
            
            MainPanel panel1 = new MainPanel();
            new MainPanelController(panel1, this.mainView);
            changePanel(panel1);
        }

        this.mainView.onBtnInfor(e -> {
            //changePanel(new InforPanel());
            this.mainView.setMainPanel(new InforPanel());
        });
        
        this.mainView.onBtnMain(e -> {
            MainPanel panel1 = new MainPanel();
            new MainPanelController(panel1, this.mainView);
            changePanel(panel1);
        });
        
        this.mainView.onBtnLibrary(e -> {
            LibraryPanel libraryPanel = new LibraryPanel();
            new LibraryController(libraryPanel, mainView);
            changePanel(libraryPanel);
        });
        
        this.mainView.onBtnHistory(e -> {
            HistoryPanel historyPanel = new HistoryPanel();
            new HistoryController(historyPanel, mainView);
            changePanel(historyPanel);
        });
        
        this.mainView.onBtnSearch(e -> {
            SearchPanel searchPanel = new SearchPanel();
            new SearchPanelController(searchPanel, mainView);
            changePanel(searchPanel);
        });
        
        this.mainView.onBtnBookManager(e -> {
            BookManagingPanel panel1 = new BookManagingPanel();
            new BookManagingController(panel1, this.mainView);
            changePanel(panel1);
        });
        
        this.mainView.onBtnUserManager(e -> {
            UserManagingPanel userPanel = new UserManagingPanel();
            new UserManagingController(userPanel, this.mainView);
            changePanel(userPanel);
        });
        
        this.mainView.onBtnLogOut(e -> {
            this.mainView.dispose();
            LoginForm loginForm = new LoginForm();
            new LoginController(loginForm);
        });
        
        this.mainView.start();
    }
    
    public void changePanel(JPanel panel) {
        this.mainView.setMainPanel(panel);
    }
    
}
