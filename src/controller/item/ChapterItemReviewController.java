/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.ReadingController;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.ChapterModel;
import models.DAO.ChapterDAO;
import views.MainView;
import views.items.ChapterItem;
import views.panels.AddChapterPanel;
import views.panels.BookEditPanel;
import views.panels.ReadingPanel;

public class ChapterItemReviewController {
    
    private ChapterItem chapterItem;
    private MainView mainView;

    public ChapterItemReviewController(JPanel parent, ChapterItem chapterItem, MainView mainView) {
        this.chapterItem = chapterItem;
        this.mainView = mainView;
        this.chapterItem.setPreferredSize(this.chapterItem.getPreferredSize());

        if (!(parent instanceof AddChapterPanel)) {
            this.chapterItem.getBtnDelete().setVisible(false);
        }
    }
    
    public ChapterItem getChapterItem() {
        return chapterItem;
    }

    public void setChapterItem(ChapterItem chapterItem) {
        this.chapterItem = chapterItem;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
