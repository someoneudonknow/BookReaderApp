package views.items;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JLabel;
import models.ChapterModel;
import static utils.formatDate.formatDate;

public class ChapterItem extends javax.swing.JPanel {

    private ChapterModel chapterModels;
    public ChapterItem(ChapterModel chapterModels) throws ParseException {
        initComponents();
        this.chapterModels = chapterModels;
        this.lbChapter.setText("Chương " + chapterModels.getSerial() + " : " + chapterModels.getTitle());
        String formatDate = formatDate("" + chapterModels.getUpdateTime());
        this.txtDate.setText(formatDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbChapter = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(32767, 40));
        setPreferredSize(new java.awt.Dimension(980, 50));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lbChapter.setBackground(new java.awt.Color(255, 255, 255));
        lbChapter.setText("jLabel1");
        lbChapter.setAlignmentY(0.0F);
        lbChapter.setPreferredSize(new java.awt.Dimension(800, 50));
        add(lbChapter);

        txtDate.setText("jLabel1");
        txtDate.setPreferredSize(new java.awt.Dimension(90, 16));
        add(txtDate);

        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_icon.png"))); // NOI18N
        btnDelete.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnDelete.setPreferredSize(new java.awt.Dimension(50, 48));
        add(btnDelete);
    }// </editor-fold>//GEN-END:initComponents
    
    public ChapterModel getChapterModels() {
        return chapterModels;
    }

    public void setChapterModels(ChapterModel chapterModels) {
        this.chapterModels = chapterModels;
    }

    public JLabel getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JLabel btnDelete) {
        this.btnDelete = btnDelete;
    }

    public void onBtnDeleteClick(MouseAdapter action) {
        this.btnDelete.addMouseListener(action);
    }

    public JLabel getLbChapter() {
        return lbChapter;
    }

    public void setLbChapter(JLabel lbChapter) {
        this.lbChapter = lbChapter;
    }
    
    public void onLbChapterClick(MouseAdapter action) {
        this.lbChapter.addMouseListener(action);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel lbChapter;
    private javax.swing.JLabel txtDate;
    // End of variables declaration//GEN-END:variables
}
