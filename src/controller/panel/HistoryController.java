/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import other.SetDataToList;
import panels.HistoryPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class HistoryController {
    HistoryPanel historyPanel;
    MainView mainView;

    public HistoryController(HistoryPanel historyPanel, MainView mainView) {
        this.historyPanel = historyPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(historyPanel.getListHistory());
        setHeightPreferrer();
    }
    
    public void setHeightPreferrer() {
        int height = this.historyPanel.getListHistory().getComponentCount();
        if (height % 5 == 0)
            height /= 5;
        else height = height/5 + 1;
        this.historyPanel.getListHistory().setPreferredSize(new Dimension(0,height * 255));
        this.historyPanel.getListHistory().repaint();
    }
}