/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import other.SetDataToList;
import views.panels.AllBookPanel;
import views.MainView;
import views.panels.MainPanel;

/**
 *
 * @author ADMIN
 */
public class AllBookController {
    AllBookPanel allBookPanel;
    MainView mainView;
    MainPanel previousPanel;

    public AllBookController(AllBookPanel allBookPanel, MainView mainView, MainPanel previousPanel) throws SQLException {
        this.allBookPanel = allBookPanel;
        this.mainView = mainView;
        this.previousPanel = previousPanel;
        
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(allBookPanel.getListAllBook(), "full", this.allBookPanel);
        
        this.allBookPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
            
        });
    }
    
    public void backToPrevious() {
        this.mainView.setMainPanel(this.previousPanel);
    }
}
