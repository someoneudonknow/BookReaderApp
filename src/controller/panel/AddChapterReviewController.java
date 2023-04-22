/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.panel;

import controller.item.ChapterItemController;
import controller.item.ChapterItemReviewController;
import controller.view.AddInforChapterController;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.BookModel;
import models.CategoryModel;
import models.ChapterModel;
import models.DAO.BookDAO;
import models.DAO.ChapterDAO;
import other.SetDataToList;
import views.AddInforChapter;
import views.MainView;
import views.items.ChapterItem;
import views.panels.AddChapterPanel;
import views.panels.BookManagingPanel;

public class AddChapterReviewController {

    private AddChapterPanel chapterPanel;
    private MainView mainView;
    private BookModel bookModel;
    private JComponent previousPanel;
    private ArrayList<CategoryModel> bookCategories;
    private LinkedList<ChapterModel> newChapterList = new LinkedList<>();

    public AddChapterReviewController(AddChapterPanel chapterPanel, MainView mainView, BookModel bookModel, ArrayList<CategoryModel> categories, JComponent previousPanel) throws SQLException {
        this.chapterPanel = chapterPanel;
        this.mainView = mainView;
        this.bookModel = bookModel;
        this.previousPanel = previousPanel;

        this.chapterPanel.onBtnAdd(e -> {
            startAddFrame();
        });

        this.chapterPanel.onBtnBack(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToPrevious();
            }
        });

        this.chapterPanel.onConfirmBtnClicked(e -> {
            this.handleConfirmBtnClicked();
        });

        this.chapterPanel.onBtnCancel(e -> {
            backToPrevious();
        });
    }

    public void setCurrentBook(BookModel currentBook) {
        this.bookModel = currentBook;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.bookCategories = categories;
    }

    private void handleConfirmBtnClicked() {
        try {
            BookDAO.getInstance().insert(this.bookModel, this.bookCategories, this.newChapterList);
            JOptionPane.showMessageDialog(this.mainView, "Add book successfully!");
            BookManagingPanel panel1 = new BookManagingPanel();
            new BookManagingController(panel1, this.mainView);
            this.mainView.setMainPanel(panel1);
        } catch (Exception ex) {
            if (ex.getMessage().equals("book_name_exists")) {
                JOptionPane.showMessageDialog(this.mainView, "Book name exixts");
            }
        }
    }

    private void startAddFrame() {
        AddInforChapter inforChapter = new AddInforChapter();
        AddInforChapterController addChapController = new AddInforChapterController(inforChapter, this.bookModel.getId());
        int lastestChap;
        if (this.newChapterList.size() > 0) {
            lastestChap = this.newChapterList.getLast().getSerial();
        } else {
            lastestChap = 0;
        }

        inforChapter.onBtnConfirm(e -> {
            if (addChapController.isValid(lastestChap)) {
                ChapterModel newChap = addChapController.getData();
                ChapterModel updatedChapter = this.updateChapSerial(newChap, inforChapter);
                updateChapSerialWhenInsert(updatedChapter.getSerial());
                this.newChapterList.add(updatedChapter.getSerial() - 1, updatedChapter);
                this.setChapterList(this.newChapterList);
                inforChapter.dispose();
            }
        });
    }

    private ChapterModel updateChapSerial(ChapterModel currentChap, AddInforChapter inforChapter) {
        int chapSerial;

        if (inforChapter.getAutoUpdateBtn().isSelected()) {
            if (this.newChapterList.size() == 0) {
                chapSerial = 1;
            } else {
                int lastestChap = this.newChapterList.getLast().getSerial();
                chapSerial = lastestChap + 1;
            }
        } else {
            chapSerial = Integer.parseInt(inforChapter.getTxtSerial().getText());
        }

        currentChap.setSerial(chapSerial);

        return currentChap;
    }

    private void updateChapSerialWhenInsert(int chapSerial) {
        for (ChapterModel i : this.newChapterList) {
            if (i.getSerial() >= chapSerial) {
                i.setSerial(i.getSerial() + 1);
            }
        }
    }

    private void updateChapSerialWhenDeleted(int chapSerial) {
        for (ChapterModel i : this.newChapterList) {
            if (i.getSerial() > chapSerial) {
                i.setSerial(i.getSerial() - 1);
            }
        }
    }

    public void backToPrevious() {
        this.mainView.setMainPanel((JPanel) previousPanel);
    }

    private void setChapterList(List<ChapterModel> chapters) {
        ArrayList<ChapterItem> items = new ArrayList<>();
        this.chapterPanel.getListChapter().removeAll();

        if (chapters.size() == 0 || chapters == null) {
            this.chapterPanel.getListChapter().revalidate();
            this.chapterPanel.getListChapter().repaint();
            return;
        }

        for (ChapterModel chapter : chapters) {
            ChapterItem a = null;
            try {
                a = new ChapterItem(chapter);
            } catch (ParseException ex) {
                Logger.getLogger(SetDataToList.class.getName()).log(Level.SEVERE, null, ex);
            }

            final ChapterItem currentChapItem = a;
            ChapterItemReviewController ctrc = new ChapterItemReviewController(this.chapterPanel, a, mainView);

            a.onBtnDeleteClick(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleDeleteChapter(currentChapItem, chapter);
                }
            });
            items.add(a);
        }

        for (ChapterItem i : items) {
            this.chapterPanel.getListChapter().add(i);
        }

        this.chapterPanel.getListChapter().revalidate();
        this.chapterPanel.getListChapter().repaint();
    }

    private void handleDeleteChapter(ChapterItem chapterItem, ChapterModel chapter) {
        int x = JOptionPane.showConfirmDialog(mainView, "Are you sure want to delete this chapter ?", "Delete chapter", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            this.updateChapSerialWhenDeleted(chapter.getSerial());
            this.newChapterList.remove(chapter);
            this.setChapterList(this.newChapterList);
        }
    }
}
