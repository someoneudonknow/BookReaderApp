/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ChapterModel;
import models.DAO.ChapterDAO;
import views.panels.ReadingPanel;
import views.MainView;

/**
 *
 * @author ADMIN
 */
public class ReadingController {

    private ReadingPanel readingPanel;
    private MainView mainView;
    private ChapterModel currentChapter;

    public ReadingController(ReadingPanel readingPanel, MainView mainView, ChapterModel currentChapter) {
        this.readingPanel = readingPanel;
        this.mainView = mainView;
        this.currentChapter = currentChapter;

        this.readingPanel.onBtnPrevious(e -> {
            try {
                ChapterModel previousChapter = new ChapterModel();
                previousChapter = ChapterDAO.getInstance().getPreivousNextChapter(this.currentChapter, "previous");
                this.setChapterDetails(previousChapter);
                this.currentChapter = previousChapter;
            } catch (SQLException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        this.readingPanel.onBtnNext(e -> {
            try {
                ChapterModel nextChapter = new ChapterModel();
                nextChapter = ChapterDAO.getInstance().getPreivousNextChapter(this.currentChapter, "next");
                this.setChapterDetails(nextChapter);
                this.currentChapter = nextChapter;
            } catch (SQLException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        this.readingPanel.onBoxChapter(e -> {
            int selectedItem = this.readingPanel.getBoxChapter().getSelectedIndex();
            try {
                ChapterModel selectedChapter = ChapterDAO.getInstance().getSelectedChapter(this.currentChapter.getBook_id(), selectedItem + 1);
                this.setChapterDetails(selectedChapter);

            } catch (SQLException ex) {
                Logger.getLogger(ReadingPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setChapterDetails(ChapterModel chapter) throws SQLException {
        int currentBookID = chapter.getBook_id();
       
        this.readingPanel.getJtextArea1().setText(chapter.getDocument());
        ArrayList<ChapterModel> listChapter = ChapterDAO.getInstance().getAllChapterFromBook(currentBookID);
        ArrayList<String> listChapterName = new ArrayList<>();
        for (ChapterModel c : listChapter) {
            listChapterName.add("Chương " + c.getSerial() + ": " + c.getTitle());
        }
        this.readingPanel.getBoxChapter().setModel(new javax.swing.DefaultComboBoxModel<>(listChapterName.toArray(new String[0])));
        String currentChapterName = listChapterName.get(chapter.getSerial() - 1);
        this.readingPanel.getBoxChapter().setSelectedItem(currentChapterName);
        ChapterDAO.getInstance().increaseView(chapter);
        this.readingPanel.getBoxChapter().repaint();

    }

}
