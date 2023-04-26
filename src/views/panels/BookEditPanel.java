/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.panels;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import other.SetButton;

/**
 *
 * @author ADMIN
 */
public class BookEditPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookInforPanel
     */
    public BookEditPanel() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        imgCover = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiscription = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtCategorys = new javax.swing.JLabel();
        btnChangeCover = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        btnAddChapter = new javax.swing.JButton();
        btnChangerCate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listChapter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtComment = new javax.swing.JTextField();
        btnAddComment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listComment = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1272, 780));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        imgCover.setBackground(new java.awt.Color(255, 255, 255));
        imgCover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.jpg"))); // NOI18N
        imgCover.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(imgCover, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 51, -1, 242));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Author:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 51, 81, 34));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Rating");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 51, 76, 34));

        txtRate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRate.setText("jLabel6");
        jPanel1.add(txtRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 51, 366, 34));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Description:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 130, 116, 33));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        txtDiscription.setColumns(20);
        txtDiscription.setLineWrap(true);
        txtDiscription.setRows(5);
        txtDiscription.setText("discription");
        txtDiscription.setWrapStyleWord(true);
        txtDiscription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtDiscription);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 169, 778, 69));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Categories:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 91, 81, 33));

        txtCategorys.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCategorys.setText("jLabel10");
        jPanel1.add(txtCategorys, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 91, 468, 33));

        btnChangeCover.setBackground(new java.awt.Color(240, 173, 78));
        btnChangeCover.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangeCover.setForeground(new java.awt.Color(255, 255, 255));
        btnChangeCover.setText("Change cover");
        btnChangeCover.setPreferredSize(new java.awt.Dimension(160, 37));
        jPanel1.add(btnChangeCover, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 256, -1, -1));

        txtName.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtName.setText("bookname");
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 6, 778, 33));

        txtAuthor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAuthor.setText("author");
        jPanel1.add(txtAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 51, 225, 34));

        btnAddChapter.setBackground(new java.awt.Color(240, 173, 78));
        btnAddChapter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddChapter.setForeground(new java.awt.Color(255, 255, 255));
        btnAddChapter.setText("Add chapter");
        btnAddChapter.setPreferredSize(new java.awt.Dimension(160, 37));
        btnAddChapter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChapterActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddChapter, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 256, -1, -1));

        btnChangerCate.setBackground(new java.awt.Color(240, 173, 78));
        btnChangerCate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChangerCate.setForeground(new java.awt.Color(255, 255, 255));
        btnChangerCate.setText("Change categories");
        btnChangerCate.setPreferredSize(new java.awt.Dimension(160, 37));
        jPanel1.add(btnChangerCate, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 256, -1, -1));

        btnSave.setBackground(new java.awt.Color(240, 173, 78));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setPreferredSize(new java.awt.Dimension(160, 37));
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 256, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Chapter list");
        jLabel12.setOpaque(true);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(52, 2));

        listChapter.setBackground(new java.awt.Color(255, 255, 255));
        listChapter.setLayout(new javax.swing.BoxLayout(listChapter, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(listChapter);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Chapters", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Comments");
        jLabel11.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtComment.setText("jTextField1");

        btnAddComment.setBackground(new java.awt.Color(240, 173, 78));
        btnAddComment.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddComment.setForeground(new java.awt.Color(255, 255, 255));
        btnAddComment.setText("Gửi");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("Viết bình luận của bạn");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                    .addComponent(txtComment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddComment)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddComment)
                    .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        listComment.setBackground(new java.awt.Color(255, 255, 255));
        listComment.setPreferredSize(new java.awt.Dimension(300, 500));
        listComment.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        jScrollPane4.setViewportView(listComment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Comments", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddChapterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChapterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddChapterActionPerformed

    public JLabel getImgCover() {
        return imgCover;
    }

    public JTextField getTxtAuthor() {
        return txtAuthor;
    }

    public JLabel getTxtCategorys() {
        return txtCategorys;
    }

    public JTextArea getTxtDiscription() {
        return txtDiscription;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JLabel getTxtRate() {
        return txtRate;
    }
    
    public JPanel getListChapter() {
        return listChapter;
    }

    public JPanel getListComment() {
        return listComment;
    }

    public JButton getBtnAddChapter() {
        return btnAddChapter;
    }

    public JButton getBtnAddComment() {
        return btnAddComment;
    }

    public JLabel getBtnBack() {
        return btnBack;
    }

    
    public void onBtnSave(ActionListener action) {
        this.btnSave.addActionListener(action);
    }

    public void onBtnAddChapter(ActionListener action) {
        this.btnAddChapter.addActionListener(action);
    }
    
    public void onBtnChangeCategory(ActionListener action) {
        this.btnChangerCate.addActionListener(action);
    }
    
    public void onBtnBack(MouseAdapter action) {
        this.btnBack.addMouseListener(new SetButton.SetLabelEffectB(btnBack));
        this.btnBack.addMouseListener(action);
    }
    
    public void onBtnChangedCover(ActionListener action) {
        this.btnChangeCover.addActionListener(action);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddChapter;
    private javax.swing.JButton btnAddComment;
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnChangeCover;
    private javax.swing.JButton btnChangerCate;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel imgCover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel listChapter;
    private javax.swing.JPanel listComment;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JLabel txtCategorys;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextArea txtDiscription;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel txtRate;
    // End of variables declaration//GEN-END:variables
}
