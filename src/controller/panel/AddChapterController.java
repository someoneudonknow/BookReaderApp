/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import controller.view.AddInforChapterController;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;
import models.BookModel;
import models.ChapterModel;
import models.DAO.ChapterDAO;
import other.SetDataToList;
import views.AddInforChapter;
import views.MainView;
import views.panels.AddBookPanel;
import views.panels.AddChapterPanel;

/**
 *
 * @author admin
 */
public class AddChapterController {

    AddChapterPanel chapterPanel;
    MainView mainView;
    BookModel bookModel;
    JComponent previousPanel;
    private List<ChapterModel> currentChapterList = null;
    private List<ChapterModel> newChapterList = new LinkedList<>();

    public AddChapterController(AddChapterPanel chapterPanel, MainView mainView, BookModel bookModel, JComponent previousPanel) throws SQLException {
        this.chapterPanel = chapterPanel;
        this.mainView = mainView;
        this.bookModel = bookModel;
        this.previousPanel = previousPanel;
        
        initUI();

        this.chapterPanel.onBtnAdd(e -> {
            startAddFrame();
        });

        this.chapterPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
        });
        
        this.chapterPanel.onBtnCancel(e -> {
            backToPrevious();
        });
    }

    private void initUI() {
//        
//                SetDataToList setData = new SetDataToList(mainView);
//        int currentID = this.bookModel.getId();
//        setData.setChapterList(this.chapterPanel, this.chapterPanel.getListChapter(), currentID);
//
//        //Set lại size cho panel chứa list
//        JPanel panel = this.chapterPanel.getListChapter();
//        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));
        
        SetDataToList setData = new SetDataToList(mainView);
        int currentID = this.bookModel.getId();
        ArrayList<ChapterModel> chapterList = new ArrayList<>();

        try {
            chapterList = ChapterDAO.getInstance().getAllChapterFromBook(currentID);
            this.currentChapterList = chapterList;
            setData.setChapterList(this.chapterPanel, this.chapterPanel.getListChapter(), currentID, chapterList);
        } catch (SQLException ex) {
            Logger.getLogger(AddChapterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel panel = this.chapterPanel.getListChapter();
        panel.setPreferredSize(new Dimension(0, panel.getComponentCount() * 40));
    }

     private void startAddFrame() {
        AddInforChapter inforChapter = new AddInforChapter();
        AddInforChapterController addChapController = new AddInforChapterController(inforChapter, this.bookModel.getId());

        inforChapter.onBtnConfirm(e -> {
            ChapterModel newChap = addChapController.getData();
            if (newChap != null) {
                this.newChapterList.add(newChap);
                this.currentChapterList.add(newChap);
                ChapterDAO ctd = new ChapterDAO();
                ctd.insert(newChap);
                SetDataToList setData = new SetDataToList(mainView);
                try {
                    this.chapterPanel.getListChapter().removeAll();
                    setData.setChapterList(this.chapterPanel, this.chapterPanel.getListChapter(), this.bookModel.getId());
                } catch (SQLException ex) {
                }
                inforChapter.dispose();
            }
        });
    }

    public void backToPrevious() {
        this.mainView.setMainPanel((JPanel) previousPanel);
    }
}
