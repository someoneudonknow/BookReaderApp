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
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.ChapterDAO;
import models.DAO.ReadingDAO;
import models.entityPK.ReadingPK;
import views.panels.ReadingPanel;
import views.MainView;
import views.panels.BookInforPanel;

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

        this.readingPanel.onBtnPrevious(e -> {
            this.onBtnLeftRight("previous");

        });
        this.readingPanel.onBtnNext(e -> {
            this.onBtnLeftRight("next");
        });
        this.readingPanel.onBoxChapter(e -> {
            this.onSelectedBox();
        });
        this.readingPanel.onApply(e -> {
            this.setReadingDoc(this.currentChapter);
        });

        this.readingPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    backToPrevious();
                } catch (SQLException ex) {
                    Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        doc = this.setDefaultView(doc);
        this.readingPanel.getjEditorPane1().setDocument(doc);
        this.readingPanel.repaint();

        this.currentChapter = chapter;
        this.readingPanel.getBoxChapter().repaint();
    }
    public void setAddChapterDetails(ChapterModel chapter, ArrayList<ChapterModel> listChapter) throws SQLException, BadLocationException {
        int currentBookID = chapter.getBook_id();

        String content = chapter.getDocument();
        HTMLDocument doc = new HTMLDocument();
        try {
            doc.insertString(0, content, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

//        ArrayList<ChapterModel> listChapter = ChapterDAO.getInstance().getAllChapterFromBook(currentBookID);
//        ArrayList<String> listChapterName = new ArrayList<>();
//        for (ChapterModel c : listChapter) {
//            listChapterName.add("Chương " + c.getSerial() + ": " + c.getTitle());
//        }
//        this.readingPanel.getBoxChapter().setModel(new javax.swing.DefaultComboBoxModel<>(listChapter.toArray(new String[0])));
//        String currentChapterName = listChapter.get(chapter.getSerial() - 1);
//        this.readingPanel.getBoxChapter().setSelectedItem(currentChapterName);

        doc = this.setDefaultView(doc);
        this.readingPanel.getjEditorPane1().setDocument(doc);
        this.readingPanel.repaint();

        this.currentChapter = chapter;
        this.readingPanel.getBoxChapter().repaint();
    }

    public void onBtnLeftRight(String option) {

        try {
            ChapterModel chapter = new ChapterModel();
            if (option.equals("previous")) {
                chapter = ChapterDAO.getInstance().getPreivousNextChapter(this.currentChapter, option);

            } else if (option.equals("next")) {
                chapter = ChapterDAO.getInstance().getPreivousNextChapter(this.currentChapter, option);
            } else {
                System.out.println("Option isn't available");
            }
            this.setChapterDetails(chapter);
            this.currentChapter = chapter;
        } catch (SQLException ex) {
            Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadLocationException ex) {
            Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onSelectedBox() {
        int selectedItem = this.readingPanel.getBoxChapter().getSelectedIndex();
        try {
            ChapterModel selectedChapter = ChapterDAO.getInstance().getSelectedChapter(this.currentChapter.getBook_id(), selectedItem + 1);
            this.setChapterDetails(selectedChapter);

        } catch (SQLException ex) {
            Logger.getLogger(ReadingPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadLocationException ex) {
            Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setReadingDoc(ChapterModel chapter) {
        this.readingPanel.getjEditorPane1().putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
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
        HTMLDocument doc = (HTMLDocument) this.readingPanel.getjEditorPane1().getDocument();
        doc = this.setDefaultView(doc);
        this.readingPanel.getjEditorPane1().setDocument(doc);
    }

    public HTMLDocument setDefaultView(HTMLDocument doc) {
        MutableAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), set, false);
        return doc;
    }

    public void backToPrevious() throws SQLException {
        previousPanel.repaint();
        if (previousPanel instanceof BookInforPanel){
            BookInforPanel inforPanel = (BookInforPanel) previousPanel;
            inforPanel.getTxtView().setText("" + BookDAO.getInstance().getView(currentChapter.getBook_id()));
        }    
        this.mainView.setMainPanel(previousPanel);
    }
}
