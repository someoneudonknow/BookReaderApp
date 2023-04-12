/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import models.ChapterModel;
import models.DAO.BookDAO;
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

    public BookInforController(BookInforPanel bookInforPanel, MainView mainView) throws SQLException {
        this.bookInforPanel = bookInforPanel;
        this.mainView = mainView;

        SetDataToList setData = new SetDataToList(mainView);
        int currentID = this.bookInforPanel.getID();
        setData.setChapterList(this.bookInforPanel, bookInforPanel.getListChapter(), currentID);
        setData.setCommentList(this.bookInforPanel, this.bookInforPanel.getListComment(), currentID);

        //Set lại size cho panel chứa list
        JPanel panel = this.bookInforPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));

        this.bookInforPanel.onBtnFirst(e -> {
            try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(currentID,"first"));
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        this.bookInforPanel.onBtnLast(e -> {
             try {
                changeToChapter(BookDAO.getInstance().getFirstLastChapter(currentID,"last"));
            } catch (SQLException ex) {
                Logger.getLogger(BookInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void changeToChapter(ChapterModel changedChapter) {

       ReadingPanel newReadingPanel = new ReadingPanel();
       newReadingPanel.setChapterDetails(changedChapter);
        this.mainView.setMainPanel(newReadingPanel);
    }
}
