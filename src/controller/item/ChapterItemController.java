/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.item;

import controller.panel.ReadingController;
import views.items.ChapterItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.ChapterModel;
import models.DAO.ChapterDAO;
import other.SetDataToList;
import views.panels.BookEditPanel;
import views.panels.ReadingPanel;
import views.MainView;

public class ChapterItemController {

    private ChapterItem chapterItem;
    private MainView mainView;
    private BookEditPanel parent;

    public ChapterItemController(JPanel parent, ChapterItem chapterItem, MainView mainView) {
        this.chapterItem = chapterItem;
        this.mainView = mainView;
        this.chapterItem.setPreferredSize(this.chapterItem.getPreferredSize());

        if (!(parent instanceof BookEditPanel)) {
            this.chapterItem.getBtnDelete().setVisible(false);
        } else {
            this.parent = (BookEditPanel) parent;
        }

        this.chapterItem.onBtnDeleteClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleteThisChapter();
            }
        });
        this.chapterItem.onLbChapterClick(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeToReadingPanel(parent);
            }
        });
    }

    public void DeleteThisChapter() {
        int x = JOptionPane.showConfirmDialog(mainView, "Are you sure want to delete this chapter ?", "Delete chapter", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            ChapterDAO ctDAO = new ChapterDAO();
            ChapterModel currentChapter = this.chapterItem.getChapterModels();
            ctDAO.deleteChapterAndUpdateSerials(currentChapter.getId(), currentChapter.getBook_id(), currentChapter.getSerial());
            SetDataToList setData = new SetDataToList(mainView);
            try {
                this.parent.getListChapter().removeAll();
                setData.setChapterList(this.parent, this.parent.getListChapter(), currentChapter.getBook_id());
            } catch (SQLException ex) {
                Logger.getLogger(ChapterItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void changeToReadingPanel(JPanel parent) {
        try {
            ReadingPanel chapterDetails = new ReadingPanel();
            ChapterModel currentChapter = chapterItem.getChapterModels();
            ReadingController reading = new ReadingController(chapterDetails, mainView, currentChapter, parent);
            reading.setChapterDetails(currentChapter);
            getMainView().setMainPanel(chapterDetails);

        } catch (Exception es) {
//            System.out.println("Khong co mainView");
        }
    }
}
