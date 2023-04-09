/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.util.ArrayList;
import models.BookModels;
import other.SetDataToList;
import views.panels.AllBookPanel;
import views.panels.MainPanel;
import views.MainView;
import views.panels.SearchPanel;

/**
 *
 * @author ADMIN
 */
public class MainPanelController {
    private MainPanel mainPanel;
    private MainView mainView;
    private ArrayList<BookModels> bookModels;

    public MainPanelController(MainPanel mainPanel, MainView mainView) {
        this.mainPanel = mainPanel;
        this.mainView = mainView;
        //this.bookModels = bookModels;
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setMainViewItemList(this.mainPanel.getListTopView());
        setData.setMainViewItemList(this.mainPanel.getListNewUpdate());
        
        this.mainPanel.onBtnMore(e -> {
            changeMorePanel();
        });
    }
    
    public void changeMorePanel(){
        this.mainView.remove(this.mainPanel);
        AllBookPanel allBookPanel = new AllBookPanel();
        new AllBookController(allBookPanel, mainView);
        this.mainView.setMainPanel(allBookPanel);
        this.mainView.revalidate();
        this.mainView.repaint();
    }
    
}
