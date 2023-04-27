package controller.view;

import controller.item.ButtonViewController;
import controller.panel.BooksController;
import controller.panel.MainPanelController;
import controller.panel.SearchController;
import controller.panel.HistoryController;
import controller.panel.InforController;
import controller.panel.LibraryController;
import controller.panel.AccountsController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.UserModel;
import other.Converter;
import other.SetButton;
import views.panels.AllBookPanel;
import views.panels.BooksPanel;
import views.panels.HistoryPanel;
import views.panels.InforPanel;
import views.LoginForm;
import views.panels.MainPanel;
import views.MainView;
import views.items.ButtonItem;
import views.panels.SearchPanel;
import views.panels.LibraryPanel;
import views.panels.ReadingPanel;
import views.panels.AccountsPanel;
import views.panels.ParentPanel;

public class MainViewController {

    private MainView mainView;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;
        this.mainView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setButton();
        this.initUI();
        this.mainView.start();
    }

    public void changePanel(ParentPanel panel) {
        this.mainView.setMainPanel(panel);
    }

    private void initUI() {
        UserModel currentUser = this.mainView.getUserModels();

        if(currentUser.getAvatar() != null) {
            this.mainView.getLbAvatar().setIcon(Converter.convertBlobToImageIcon(currentUser.getAvatar()));
        }
        
        this.mainView.getLbUsername().setText(currentUser.getUserName());
    }
    
    public void setButton(){
        if(this.mainView.getUserModels().isIsManager()){
            ButtonItem btnInfor = new ButtonItem("INFORMATION", null);
            new ButtonViewController(btnInfor, mainView);
            
            BooksPanel bookManagingPanel = new BooksPanel();
            new BooksController(bookManagingPanel, this.mainView);
            ButtonItem btnBooks = new ButtonItem("BOOKS", bookManagingPanel);
            new ButtonViewController(btnBooks, mainView);

            ButtonItem btnAccounts = new ButtonItem("ACCOUNTS", null);
            new ButtonViewController(btnAccounts, mainView);
            
            this.mainView.getGroupBtn().add(btnInfor);
            this.mainView.getGroupBtn().add(btnBooks);
            this.mainView.getGroupBtn().add(btnAccounts);
        
            this.mainView.setMainPanel(bookManagingPanel);
        }
        else{
            MainPanel panel1 = new MainPanel();
            new MainPanelController(panel1, this.mainView);
            ButtonItem btnMain = new ButtonItem("HOME", panel1);
            new ButtonViewController(btnMain, mainView);

            ButtonItem btnInfor = new ButtonItem("INFORMATION", null);
            new ButtonViewController(btnInfor, mainView);

            ButtonItem btnLibrary = new ButtonItem("LIBRARY", null);
            new ButtonViewController(btnLibrary, mainView);

            ButtonItem btnHistory = new ButtonItem("HISTORY", null);
            new ButtonViewController(btnHistory, mainView);            

            ButtonItem btnSearch = new ButtonItem("ADVANCED SEARCH", null);
            new ButtonViewController(btnSearch, mainView);
            
            this.mainView.getGroupBtn().add(btnMain);
            this.mainView.getGroupBtn().add(btnInfor);
            this.mainView.getGroupBtn().add(btnLibrary);
            this.mainView.getGroupBtn().add(btnHistory);
            this.mainView.getGroupBtn().add(btnSearch);
            
            this.mainView.setMainPanel(panel1);
        }
        
        ButtonItem btnLogout = new ButtonItem("LOG OUT", null);
        new ButtonViewController(btnLogout, mainView);
                    
        this.mainView.getGroupBtn().add(btnLogout);
        
    }
}
