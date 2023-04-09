/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import javax.swing.JPanel;
import other.SetDataToList;
import views.panels.BookInforPanel;
import views.panels.ReadingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class BookInforController {
    private BookInforPanel bookInforPanel;
    private MainView mainView;

    public BookInforController(BookInforPanel bookInforPanel, MainView mainView) {
        this.bookInforPanel = bookInforPanel;
        this.mainView = mainView;
        
        SetDataToList setData = new SetDataToList(mainView);
        setData.setChapterList(this.bookInforPanel, bookInforPanel.getListChapter(), 0);
        setData.setCommentList(this.bookInforPanel, this.bookInforPanel.getListComment(), 0);
        
        //Set lại size cho panel chứa list
        JPanel panel = this.bookInforPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0,panel.getComponentCount()*40));
        
        this.bookInforPanel.onBtnFirst(e -> {
            changeToChapter(0);
        });
        
        this.bookInforPanel.onBtnLast(e -> {
            changeToChapter(0);
        });
    }
    
    public void changeToChapter(int id_chapter) {
        this.mainView.setMainPanel(new ReadingPanel());
    }
}
