/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.panels;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import models.UserModel;

public class InforPanel extends javax.swing.JPanel {
    public InforPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        passwordInput = new javax.swing.JPasswordField();
        phoneNumberInput = new javax.swing.JTextField();
        userNameInput = new javax.swing.JTextField();
        photo = new javax.swing.JLabel();
        passwordErrorMessage = new javax.swing.JLabel();
        imageHolder = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        phoneNumberErrorMessage = new javax.swing.JLabel();
        userNameErrorMessage = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        photoErrorMessage = new javax.swing.JLabel();
        showPasswordBtn = new javax.swing.JCheckBox();
        passwordConfirmWrapper = new javax.swing.JPanel();
        showPasswordConfirmBtn = new javax.swing.JCheckBox();
        passwordConfirmErrorMessage = new javax.swing.JLabel();
        passwordConfirm = new javax.swing.JLabel();
        passwordConfirmInput = new javax.swing.JPasswordField();
        chooseFileBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(new java.awt.Dimension(1272, 800));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMATION");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(1270, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        passwordInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordInput.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        phoneNumberInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumberInput.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        userNameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userNameInput.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        userNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameInputActionPerformed(evt);
            }
        });

        photo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setText("Avatar");

        passwordErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        imageHolder.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        imageHolder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageHolder.setText("photo");
        imageHolder.setAlignmentY(0.0F);
        imageHolder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password.setText("Password");

        phoneNumberErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        userNameErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        phoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumber.setText("Phone number");

        userName.setBackground(new java.awt.Color(204, 0, 51));
        userName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userName.setText("Username");

        photoErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        showPasswordBtn.setText("Show password");
        showPasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordBtnActionPerformed(evt);
            }
        });

        passwordConfirmWrapper.setBackground(new java.awt.Color(255, 255, 255));

        showPasswordConfirmBtn.setText("Show password");

        passwordConfirmErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        passwordConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordConfirm.setText("Confirm password");

        passwordConfirmInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout passwordConfirmWrapperLayout = new javax.swing.GroupLayout(passwordConfirmWrapper);
        passwordConfirmWrapper.setLayout(passwordConfirmWrapperLayout);
        passwordConfirmWrapperLayout.setHorizontalGroup(
            passwordConfirmWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordConfirmWrapperLayout.createSequentialGroup()
                .addGroup(passwordConfirmWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showPasswordConfirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordConfirmInput)
                    .addComponent(passwordConfirmErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        passwordConfirmWrapperLayout.setVerticalGroup(
            passwordConfirmWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordConfirmWrapperLayout.createSequentialGroup()
                .addComponent(passwordConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordConfirmInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordConfirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordConfirmErrorMessage)
                .addGap(18, 18, 18))
        );

        chooseFileBtn.setBackground(new java.awt.Color(240, 173, 78));
        chooseFileBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseFileBtn.setForeground(new java.awt.Color(255, 255, 255));
        chooseFileBtn.setText("Choose file");
        chooseFileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout1);

        btnEdit.setBackground(new java.awt.Color(240, 173, 78));
        btnEdit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        jPanel2.add(btnEdit);

        btnUndo.setBackground(new java.awt.Color(240, 173, 78));
        btnUndo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUndo.setForeground(new java.awt.Color(255, 255, 255));
        btnUndo.setText("Cancel");
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });
        jPanel2.add(btnUndo);

        btnSave.setBackground(new java.awt.Color(240, 173, 78));
        btnSave.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        jPanel2.add(btnSave);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(userNameInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                        .addComponent(userName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(phoneNumberInput, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(phoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addComponent(userNameErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(phoneNumberErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordConfirmWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photoErrorMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName)
                    .addComponent(photo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(photoErrorMessage))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameErrorMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneNumber)
                        .addGap(4, 4, 4)
                        .addComponent(phoneNumberInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneNumberErrorMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPasswordBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordErrorMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordConfirmWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 237, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameInputActionPerformed

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUndoActionPerformed

    private void showPasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showPasswordBtnActionPerformed
    
    public JButton getChooseFileBtn() {
        return chooseFileBtn;
    }

    public JLabel getImageHolder() {
        return imageHolder;
    }

    public JLabel getPasswordConfirm() {
        return passwordConfirm;
    }

    public JLabel getPasswordConfirmErrorMessage() {
        return passwordConfirmErrorMessage;
    }

    public JLabel getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public JLabel getPhoneNumberErrorMessage() {
        return phoneNumberErrorMessage;
    }

    public JLabel getPhoto() {
        return photo;
    }

    public JLabel getPhotoErrorMessage() {
        return photoErrorMessage;
    }

    public JCheckBox getShowPasswordBtn() {
        return showPasswordBtn;
    }

    public JCheckBox getShowPasswordConfirmBtn() {
        return showPasswordConfirmBtn;
    }

    public JLabel getUserNameErrorMessage() {
        return userNameErrorMessage;
    }

    public JPasswordField getPasswordConfirmInput() {
        return passwordConfirmInput;
    }

    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    public JTextField getPhoneNumberInput() {
        return phoneNumberInput;
    }

    public JTextField getUserNameInput() {
        return userNameInput;
    }
    
    public void onChooseFile(ActionListener action) {
        this.chooseFileBtn.addActionListener(action);
    }
    
    public void onShowPassword(ActionListener action) {
        this.showPasswordBtn.addActionListener(action);
    }
        
    public void onShowPasswordConfirm(ActionListener action) {
        this.showPasswordConfirmBtn.addActionListener(action);
    }
    
    public void onEditBtnClick(ActionListener action){
        this.btnEdit.addActionListener(action);
    }
    
    public void onSaveBtnClick(ActionListener action){
        this.btnSave.addActionListener(action);
    }
    
    public void onCancelBtnClick(ActionListener action) {
        this.btnUndo.addActionListener(action);
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnUndo() {
        return btnUndo;
    }
    
    public JPanel getPasswordConfirmWrapper() {
        return this.passwordConfirmWrapper;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUndo;
    private javax.swing.JButton chooseFileBtn;
    private javax.swing.JLabel imageHolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel password;
    private javax.swing.JLabel passwordConfirm;
    private javax.swing.JLabel passwordConfirmErrorMessage;
    private javax.swing.JPasswordField passwordConfirmInput;
    private javax.swing.JPanel passwordConfirmWrapper;
    private javax.swing.JLabel passwordErrorMessage;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel phoneNumber;
    private javax.swing.JLabel phoneNumberErrorMessage;
    private javax.swing.JTextField phoneNumberInput;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel photoErrorMessage;
    private javax.swing.JCheckBox showPasswordBtn;
    private javax.swing.JCheckBox showPasswordConfirmBtn;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userNameErrorMessage;
    private javax.swing.JTextField userNameInput;
    // End of variables declaration//GEN-END:variables
}
