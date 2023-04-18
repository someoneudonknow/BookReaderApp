/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import models.ChapterModel;
import models.DAO.ChapterDAO;
import models.DAO.ReadingDAO;
import models.entityPK.ReadingPK;
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
    private JPanel previousPanel;

    public ReadingController(ReadingPanel readingPanel, MainView mainView, ChapterModel currentChapter, JPanel previousPanel) {
        this.readingPanel = readingPanel;
        this.mainView = mainView;
        this.currentChapter = currentChapter;
        this.previousPanel = previousPanel;
        
        this.readingPanel.getjComboBox1().setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Arial", "Times New Roman"}));
        this.readingPanel.onBtnPrevious(e -> {
            try {
                ChapterModel previousChapter = new ChapterModel();
                previousChapter = ChapterDAO.getInstance().getPreivousNextChapter(this.currentChapter, "previous");
                this.setChapterDetails(previousChapter);
                this.currentChapter = previousChapter;
            } catch (SQLException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
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
            } catch (BadLocationException ex) {
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
            } catch (BadLocationException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.readingPanel.onApply(e -> {
            System.out.println("clicked");
            int paddingNum = 0;
            try {
                String input = this.readingPanel.getjTextField3().getText();
                paddingNum = Integer.parseInt(input);
            } catch (NumberFormatException e1) {
                paddingNum = 20;
            }
            int sizeNum = 0;
            try {
                String input = this.readingPanel.getjTextField2().getText();
                sizeNum = Integer.parseInt(input);
            } catch (NumberFormatException e1) {
                sizeNum = 16;
            }
            Insets insets = new Insets(0, paddingNum, 0, paddingNum);
            this.readingPanel.getjEditorPane1().setMargin(insets);
            this.readingPanel.getjTextField2().setText("" + sizeNum);
            this.readingPanel.getjTextField3().setText("" + paddingNum);

            String fontStyle = (String) this.readingPanel.getjComboBox1().getSelectedItem();
            this.readingPanel.getjEditorPane1().setFont(new Font(fontStyle, Font.PLAIN, sizeNum));
            System.out.println(this.readingPanel.getjEditorPane1());

            try {
                this.setChapterDetails(this.currentChapter);
            } catch (SQLException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.readingPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
            
        });
    }

    public void setChapterDetails(ChapterModel chapter) throws SQLException, BadLocationException {
        int currentBookID = chapter.getBook_id();

        String content = chapter.getDocument();
        HTMLDocument doc = new HTMLDocument();
        try {
            doc.insertString(0, content, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        MutableAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setAlignment(set, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), set, false);

        ArrayList<ChapterModel> listChapter = ChapterDAO.getInstance().getAllChapterFromBook(currentBookID);
        ArrayList<String> listChapterName = new ArrayList<>();
        for (ChapterModel c : listChapter) {
            listChapterName.add("Chương " + c.getSerial() + ": " + c.getTitle());
        }
        this.readingPanel.getBoxChapter().setModel(new javax.swing.DefaultComboBoxModel<>(listChapterName.toArray(new String[0])));
        String currentChapterName = listChapterName.get(chapter.getSerial() - 1);
        this.readingPanel.getBoxChapter().setSelectedItem(currentChapterName);
        ChapterDAO.getInstance().increaseView(chapter);
        ReadingDAO.getInstance().readingEvent(new ReadingPK(mainView.getUserModels().getId(), currentChapter.getId()));
        
        this.readingPanel.getjEditorPane1().setDocument(doc);
        this.readingPanel.repaint();

        this.currentChapter = chapter;
        this.readingPanel.getBoxChapter().repaint();

    }
    
    public void backToPrevious() {
        this.mainView.setMainPanel(previousPanel);
    }
}
