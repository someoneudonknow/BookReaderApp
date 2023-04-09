/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import javax.swing.JPanel;
import other.SetDataToList;
import views.panels.AddChapterPanel;
import views.panels.BookEditPanel;
import views.panels.BookInforPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookEditController {
    private BookEditPanel bookEditPanel;
    private MainView mainView;

    public BookEditController(BookEditPanel bookEditPanel, MainView mainView) {
        this.bookEditPanel = bookEditPanel;
        this.mainView = mainView;
        SetDataToList setData = new SetDataToList(mainView);
        setData.setChapterList(this.bookEditPanel, this.bookEditPanel.getListChapter(), 0);
        setData.setCommentList(this.bookEditPanel, this.bookEditPanel.getListComment(), 0);
        
        JPanel panel = this.bookEditPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0,panel.getComponentCount()*40));
        
        this.bookEditPanel.onBtnAddChapter(e -> {
            AddChapter();
        });
        
        this.bookEditPanel.onBtnSave(e -> {
            Save();
        });
    }
    
    public void AddChapter() {
        this.mainView.setMainPanel(new AddChapterPanel());
    }
    
    public void Save() {
        
    }
}
