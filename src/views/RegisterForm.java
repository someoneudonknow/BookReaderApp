package views;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterForm extends javax.swing.JFrame {
    public RegisterForm() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        passwordConfirm = new javax.swing.JLabel();
        passwordConfirmErrorMessage = new javax.swing.JLabel();
        loginLink = new javax.swing.JLabel();
        genderErrorMessage = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        phoneNumberInput = new javax.swing.JTextField();
        userNameInput = new javax.swing.JTextField();
        photo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        maleRadioBtn = new javax.swing.JRadioButton();
        passwordErrorMessage = new javax.swing.JLabel();
        passwordConfirmInput = new javax.swing.JPasswordField();
        imageHolder = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        femaleRadioBtn = new javax.swing.JRadioButton();
        phoneNumberErrorMessage = new javax.swing.JLabel();
        genders = new javax.swing.JLabel();
        userNameErrorMessage = new javax.swing.JLabel();
        chooseFileBtn = new javax.swing.JButton();
        phoneNumber = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        photoErrorMessage = new javax.swing.JLabel();
        showPasswordBtn = new javax.swing.JCheckBox();
        showPasswordConfirmBtn = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign up");

        registerBtn.setBackground(new java.awt.Color(204, 0, 51));
        registerBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        passwordConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordConfirm.setText("Password Confirm");

        passwordConfirmErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        loginLink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginLink.setForeground(new java.awt.Color(0, 0, 255));
        loginLink.setText("Login");
        loginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        genderErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        passwordInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phoneNumberInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        userNameInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        photo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        photo.setText("Avatar");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Already have an account?");

        genderGroup.add(maleRadioBtn);
        maleRadioBtn.setText("Male");

        passwordErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        passwordConfirmInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        imageHolder.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        imageHolder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageHolder.setText("photo");
        imageHolder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password.setLabelFor(passwordInput);
        password.setText("Password");

        genderGroup.add(femaleRadioBtn);
        femaleRadioBtn.setText("Female");

        phoneNumberErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        genders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        genders.setLabelFor(genders);
        genders.setText("Gender");

        userNameErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        chooseFileBtn.setText("Brown File");
        chooseFileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        phoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumber.setLabelFor(phoneNumberInput);
        phoneNumber.setText("Phone number");

        userName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userName.setLabelFor(userNameInput);
        userName.setText("User name");

        photoErrorMessage.setForeground(new java.awt.Color(255, 0, 51));

        showPasswordBtn.setText("Show password");

        showPasswordConfirmBtn.setText("Show password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(showPasswordBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(passwordErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(passwordConfirmErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showPasswordConfirmBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(maleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(femaleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(loginLink, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(passwordConfirmInput, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(userNameInput, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(passwordConfirm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                                .addComponent(userName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(phoneNumberInput, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(phoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addComponent(userNameErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(genders, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addComponent(phoneNumberErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                        .addComponent(genderErrorMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imageHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(photoErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                        .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(genders)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(femaleRadioBtn)
                            .addComponent(maleRadioBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderErrorMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordConfirmInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordConfirmBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordConfirmErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(loginLink))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerBtnActionPerformed

    public JButton getChooseFileBtn() {
        return chooseFileBtn;
    }

    public JLabel getGenderErrorMessage() {
        return genderErrorMessage;
    }

    public ButtonGroup getGenderGroup() {
        return genderGroup;
    }

    public JLabel getImageHolder() {
        return imageHolder;
    }

    public JLabel getLoginLink() {
        return loginLink;
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

    public JButton getRegisterBtn() {
        return registerBtn;
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
    
    public void onLoginLinkClicked(MouseAdapter action) {
        loginLink.addMouseListener(action);
    }
    
    public void onSignUp(ActionListener action) {
        this.registerBtn.addActionListener(action);
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
    
    public void start() {
        java.awt.EventQueue.invokeLater(() -> {
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Sign up");
            setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseFileBtn;
    private javax.swing.JRadioButton femaleRadioBtn;
    private javax.swing.JLabel genderErrorMessage;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel genders;
    private javax.swing.JLabel imageHolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loginLink;
    private javax.swing.JRadioButton maleRadioBtn;
    private javax.swing.JLabel password;
    private javax.swing.JLabel passwordConfirm;
    private javax.swing.JLabel passwordConfirmErrorMessage;
    private javax.swing.JPasswordField passwordConfirmInput;
    private javax.swing.JLabel passwordErrorMessage;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel phoneNumber;
    private javax.swing.JLabel phoneNumberErrorMessage;
    private javax.swing.JTextField phoneNumberInput;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel photoErrorMessage;
    private javax.swing.JButton registerBtn;
    private javax.swing.JCheckBox showPasswordBtn;
    private javax.swing.JCheckBox showPasswordConfirmBtn;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userNameErrorMessage;
    private javax.swing.JTextField userNameInput;
    // End of variables declaration//GEN-END:variables
}
