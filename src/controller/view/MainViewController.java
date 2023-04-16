package controller.view;

import controller.panel.BookManagingController;
import controller.panel.MainPanelController;
import controller.panel.SearchPanelController;
import controller.panel.HistoryController;
import controller.panel.InforPanelController;
import controller.panel.LibraryController;
import controller.panel.UserManagingController;
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

public class MainViewController {

    private MainView mainView;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;
        this.mainView.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MainPanel panel = new MainPanel();
        new MainPanelController(panel, this.mainView);
        if (this.mainView.getUserModels().isIsManager() == true) {
            this.mainView.getBtnHistory().setVisible(false);
            this.mainView.getBtnLibrary().setVisible(false);
            this.mainView.getBtnMain().setVisible(false);
            this.mainView.getBtnSearch().setVisible(false);

            BookManagingPanel panel1 = new BookManagingPanel();
            new BookManagingController(panel1, this.mainView);
            changePanel(panel1);
        } else {
            this.mainView.getBtnBookManager().setVisible(false);
            this.mainView.getBtnUserManager().setVisible(false);

            MainPanel panel1 = new MainPanel();
            new MainPanelController(panel1, this.mainView);
            changePanel(panel1);
        }

        this.mainView.onBtnInfor(e -> {
            InforPanel currentUserInfo = new InforPanel();
            new InforPanelController(currentUserInfo, this.mainView.getUserModels(), this.mainView);
            changePanel(currentUserInfo);
        });

        this.mainView.onBtnMain(e -> {
            MainPanel panel1 = new MainPanel();
            new MainPanelController(panel1, this.mainView);
            changePanel(panel1);
        });

        this.mainView.onBtnLibrary(e -> {
            LibraryPanel libraryPanel = new LibraryPanel();
            try {
                new LibraryController(libraryPanel, mainView);
            } catch (SQLException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            changePanel(libraryPanel);
        });

        this.mainView.onBtnHistory(e -> {
            HistoryPanel historyPanel = new HistoryPanel();
            try {
                new HistoryController(historyPanel, mainView);
            } catch (SQLException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?");
            if (choice == 0) {
                this.mainView.dispose();
                LoginForm loginForm = new LoginForm();
                new LoginController(loginForm);
            }
        });
        this.initUI();
        this.mainView.start();
    }

    public void changePanel(JPanel panel) {
        this.mainView.setMainPanel(panel);
    }

    private void initUI() {
        UserModel currentUser = this.mainView.getUserModels();

        if(currentUser.getAvatar() != null) {
            this.mainView.getLbAvatar().setIcon(Converter.convertBlobToImageIcon(currentUser.getAvatar()));
        }
        
        this.mainView.getLbUsername().setText(currentUser.getUserName());
    }
}
