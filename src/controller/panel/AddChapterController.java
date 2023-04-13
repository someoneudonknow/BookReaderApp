/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import controller.view.AddInforChapterController;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import models.BookModel;
import other.SetDataToList;
import views.AddInforChapter;
import views.MainView;
import views.panels.AddChapterPanel;

/**
 *
 * @author admin
 */
public class AddChapterController {
    AddChapterPanel chapterPanel;
    MainView mainView;
    BookModel bookModel;

    public AddChapterController(AddChapterPanel chapterPanel, MainView mainView, BookModel bookModel) throws SQLException {
        this.chapterPanel = chapterPanel;
        this.mainView = mainView;
        this.bookModel = bookModel;
        
        SetDataToList setData = new SetDataToList(mainView);
        int currentID = this.bookModel.getId();
        setData.setChapterList(this.chapterPanel, this.chapterPanel.getListChapter(), currentID);

        //Set lại size cho panel chứa list
        JPanel panel = this.chapterPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));
        
        this.chapterPanel.onBtnAdd(e -> {
            startAddFrame();
        });
    }
    
    public void startAddFrame() {
        AddInforChapter inforChapter = new AddInforChapter();
        new AddInforChapterController(inforChapter, this.bookModel.getId());
    }
}
