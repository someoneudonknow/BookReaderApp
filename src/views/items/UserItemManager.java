/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.items;

import java.awt.event.ActionListener;
import models.BookModel;
import models.UserModel;

/**
 *
 * @author ADMIN
 */
public class UserItemManager extends javax.swing.JPanel{

    /**
     * Creates new form BookItemManager
     */
    private UserModel userItem;
    public UserItemManager(UserModel userItem) {
        initComponents();
        this.userItem = userItem;
        this.lbID.setText("" + this.userItem.getId());
        this.lbUsername.setText(this.userItem.getUserName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInfor = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnInfor.setBackground(new java.awt.Color(204, 0, 51));
        btnInfor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInfor.setForeground(new java.awt.Color(255, 255, 255));
        btnInfor.setText("Xem thông tin");

        labelName.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        labelName.setText("Username");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setText("ID");

        lbID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbID.setText("jLabel2");

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUsername.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addComponent(lbUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(labelName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbID)
                            .addComponent(lbUsername))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void onBtnInfor(ActionListener action) {
        this.btnInfor.addActionListener(action);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbUsername;
    // End of variables declaration//GEN-END:variables

}
