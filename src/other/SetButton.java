/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package other;

import controller.panel.BooksController;
import controller.panel.HistoryController;
import controller.panel.InforController;
import controller.panel.LibraryController;
import controller.panel.MainPanelController;
import controller.panel.SearchController;
import controller.panel.AccountsController;
import controller.view.LoginController;
import controller.view.MainViewController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import views.LoginForm;
import views.MainView;
import views.items.ButtonItem;
import views.panels.BooksPanel;
import views.panels.HistoryPanel;
import views.panels.InforPanel;
import views.panels.LibraryPanel;
import views.panels.MainPanel;
import views.panels.SearchPanel;
import views.panels.AccountsPanel;

public class SetButton {
    
    public static class SetLabelEffect extends MouseAdapter{
        JLabel btnBack;
        
        public SetLabelEffect(JLabel btnBack) {
            this.btnBack = btnBack;
            this.btnBack.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(146,154,171));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(102,102,102));
        }
    }
    
    public static class SetLabelEffectB extends MouseAdapter{
        JLabel btnBack;
        
        public SetLabelEffectB(JLabel btnBack) {
            this.btnBack = btnBack;
            this.btnBack.setOpaque(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(255,255,255));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.btnBack.setBackground(new java.awt.Color(242,242,242));
        }
    }
        
    public static class SetBtnMain extends MouseAdapter{
        ButtonItem btnMain;
        MainView mainView;
        String typePanel;
        boolean isActive;

        public SetBtnMain(ButtonItem btnMain, MainView mainView, String typePanel) {
            this.btnMain = btnMain;
            this.mainView = mainView;
            this.typePanel = typePanel;
            if(typePanel.equals("HOME") || typePanel.equals("BOOKS")){
                btnMain.getButton().setBackground(new java.awt.Color(240,173,78));
                btnMain.getButton().setForeground(new java.awt.Color(255,255,255));
                isActive = true;
            }
            else isActive = false;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!isActive){
                this.btnMain.getButton().setBackground(new java.awt.Color(0,0,0));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!isActive){
                this.btnMain.getButton().setBackground(new java.awt.Color(102,102,102));
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(!isActive){
                if(!(typePanel.equals("LOG OUT"))){
                    btnMain.getButton().setBackground(new java.awt.Color(240,173,78));
                    btnMain.getButton().setForeground(new java.awt.Color(255,255,255));
                    isActive = true;
                    setEnabledBtn();
                }
                changePanel();
            }

        }
        
        public void setEnabledBtn(){
            for (int i = 0; i < this.mainView.getGroupBtn().getComponentCount(); i++){
                ButtonItem btn = (ButtonItem) mainView.getGroupBtn().getComponent(i);
                if(btn != btnMain) {
                    btn.getButton().setForeground(new java.awt.Color(255,255,255));
                    btn.getButton().setBackground(new java.awt.Color(0,0,0));
                    SetBtnMain events = (SetBtnMain) btn.getEvents();
                    events.setIsActive(false);
                }
            }
        }
        public void changePanel() {
            switch (typePanel) {
                
                case "HOME":
                    MainPanel panel1 = new MainPanel();
                    new MainPanelController(panel1, this.mainView);
                    this.mainView.setMainPanel(panel1);
                    break;
                    
                case "INFORMATION":
                    InforPanel currentUserInfo = new InforPanel();
                    new InforController(currentUserInfo, mainView.getUserModels(), mainView);
                    this.mainView.setMainPanel(currentUserInfo);
                    break;
                    
                case "LIBRARY":
                    LibraryPanel libraryPanel = new LibraryPanel();
                    try {
                        new LibraryController(libraryPanel, mainView);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.mainView.setMainPanel(libraryPanel);
                    break;
                    
                case "HISTORY":
                    HistoryPanel historyPanel = new HistoryPanel();
                    try {
                        new HistoryController(historyPanel, mainView);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.mainView.setMainPanel(historyPanel);
                    break;
                    
                case "ADVANCED SEARCH":
                    SearchPanel searchPanel = new SearchPanel();
                    new SearchController(searchPanel, mainView);
                    this.mainView.setMainPanel(searchPanel);
                    break;
                    
                case "BOOKS":
                    BooksPanel bookManagingPanel = new BooksPanel();
                    new BooksController(bookManagingPanel, this.mainView);
                    this.mainView.setMainPanel(bookManagingPanel);
                    break;
                    
                case "ACCOUNTS":
                    AccountsPanel userPanel = new AccountsPanel();
                    new AccountsController(userPanel, this.mainView);
                    this.mainView.setMainPanel(userPanel);
                    break;
                    
                case "LOG OUT":
                    int choice = JOptionPane.showConfirmDialog(this.mainView, "Are you sure you want to sign out?", "Sign out", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        this.mainView.dispose();
                        LoginForm loginForm = new LoginForm();
                        new LoginController(loginForm);
                    }
                    break;
                    
                default:
                    throw new AssertionError();
            }
            
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }
 
    }
}
