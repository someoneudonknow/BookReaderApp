package controller.panel;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
            try {
                this.setReadingDoc(this.currentChapter);
            } catch (IOException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void handleReadFile() {
        File file = new File("C:\\Users\\ADMIN\\Desktop\\BookReaderApp\\src\\other\\text.txt");
        BufferedReader br = null;
        FileReader fr = null;
        String re = "";
        HTMLDocument doc = new HTMLDocument();

        if (!file.exists()) {

            boolean temp;
            try {
                temp = file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.readingPanel.getjEditorPane1().putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            this.readingPanel.getjComboBox1().setSelectedItem("Times New Roman");
            this.readingPanel.getjTextField2().setText("15");
            this.readingPanel.getjTextField3().setText("20");
            Insets insets = new Insets(0, 20, 0, 20);
            this.readingPanel.getjEditorPane1().setMargin(insets);
            this.readingPanel.getjEditorPane1().setFont(new Font((String) this.readingPanel.getjComboBox1().getSelectedItem(), Font.PLAIN, Integer.parseInt(this.readingPanel.getjTextField2().getText())));
            MutableAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setAlignment(set, StyleConstants.ALIGN_JUSTIFIED);
            doc.setParagraphAttributes(0, doc.getLength(), set, false);
            this.readingPanel.getjEditorPane1().setDocument(doc);
        } else {

            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                re = br.readLine();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadingController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.readingPanel.getjEditorPane1().putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            String[] result = re.split(",");
            int paddingNum = Integer.parseInt(result[0]);
            int sizeNum = Integer.parseInt(result[1]);
            String fontStyle = result[2];
            this.readingPanel.getjComboBox1().setSelectedItem(fontStyle);
            this.readingPanel.getjTextField2().setText("" + sizeNum);
            this.readingPanel.getjTextField3().setText("" + paddingNum);
            Insets insets = new Insets(0, paddingNum, 0, paddingNum);
            this.readingPanel.getjEditorPane1().setMargin(insets);
            this.readingPanel.getjEditorPane1().setFont(new Font(fontStyle, Font.PLAIN, sizeNum));
            MutableAttributeSet set = new SimpleAttributeSet();
            doc.setParagraphAttributes(0, doc.getLength(), set, false);
            this.readingPanel.getjEditorPane1().setDocument(doc);
        }
    }

    public void setChapterDetails(ChapterModel chapter) throws SQLException, BadLocationException {
        handleReadFile();
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
        } catch (SQLException | BadLocationException ex) {
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

    public void setReadingDoc(ChapterModel chapter) throws IOException {
        this.readingPanel.getjEditorPane1().putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        int paddingNum = Integer.parseInt(this.readingPanel.getjTextField3().getText());
        int sizeNum = Integer.parseInt(this.readingPanel.getjTextField2().getText());
        Insets insets = new Insets(0, paddingNum, 0, paddingNum);
        this.readingPanel.getjEditorPane1().setMargin(insets);
//        this.readingPanel.getjTextField2().setText("" + sizeNum);
//        this.readingPanel.getjTextField3().setText("" + paddingNum);

        String fontStyle = (String) this.readingPanel.getjComboBox1().getSelectedItem();
        this.readingPanel.getjEditorPane1().setFont(new Font(fontStyle, Font.PLAIN, sizeNum));
        HTMLDocument doc = (HTMLDocument) this.readingPanel.getjEditorPane1().getDocument();
        doc = this.setDefaultView(doc);
        this.readingPanel.getjEditorPane1().setDocument(doc);


        File file = new File("src/other/text.txt");

        BufferedWriter bw = null;
        FileWriter fw = null;

        String input = (String) this.readingPanel.getjComboBox1().getSelectedItem();
        String fontSize = this.readingPanel.getjTextField2().getText();
        String padding = this.readingPanel.getjTextField3().getText();
//        String fontSize = this.readingPanel.getFontsizeSpinner().getValue() +"";
//        String padding = this.readingPanel.getPaddingSpinner().getValue() + "";
        String result = padding + "," + fontSize + "," + input;
        fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(result);

        if (bw != null) {
            bw.close();
        }
        if (fw != null) {
            fw.close();
        }
    }

    public HTMLDocument setDefaultView(HTMLDocument doc) {
        MutableAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), set, false);
        return doc;
    }

    public void backToPrevious() throws SQLException {
        previousPanel.repaint();
        if (previousPanel instanceof BookInforPanel) {
            BookInforPanel inforPanel = (BookInforPanel) previousPanel;
            inforPanel.getTxtView().setText("" + BookDAO.getInstance().getView(currentChapter.getBook_id()));
        }
        this.mainView.setMainPanel(previousPanel);
    }
}
