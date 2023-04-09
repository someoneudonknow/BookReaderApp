/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import other.SetDataToList;
import views.panels.AllBookPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class AllBookController {
    AllBookPanel allBookPanel;
    MainView mainView;

    public AllBookController(AllBookPanel allBookPanel, MainView mainView) {
        this.allBookPanel = allBookPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(allBookPanel.getListAllBook());
    }
    
    
}
