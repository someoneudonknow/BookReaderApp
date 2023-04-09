/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import javax.swing.JPanel;
import other.SetDataToList;
import panels.HistoryPanel;
import panels.LibraryPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class LibraryController {
    LibraryPanel libraryPanel;
    MainView mainView;

    public LibraryController(LibraryPanel libraryPanel, MainView mainView) {
        this.libraryPanel = libraryPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(this.mainView);
        setData.setBookItemList(libraryPanel.getListLibrary());
        this.libraryPanel.getListLibrary().setPreferredSize(new Dimension(0,setHeightPreferrer()));
        this.libraryPanel.getListLibrary().repaint();
    }
    
    public int setHeightPreferrer() {
        int height = this.libraryPanel.getListLibrary().getComponentCount();
        if (height % 5 == 0)
            height /= 5;
        else height = height/5 + 1;
        return height * 255;
    }
}