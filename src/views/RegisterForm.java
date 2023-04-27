package views;

import java.awt.Image;
import java.awt.Toolkit;
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
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon_app.jpg"));  
        this.setIconImage(icon);
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
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign up");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 23, 739, 54));

        registerBtn.setBackground(new java.awt.Color(0, 153, 153));
        registerBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        getContentPane().add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 739, 44));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passwordConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordConfirm.setText("Password Confirm");
        jPanel1.add(passwordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 329, 524, -1));

        passwordConfirmErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(passwordConfirmErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 429, 524, -1));

        loginLink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginLink.setForeground(new java.awt.Color(0, 0, 255));
        loginLink.setText("Login");
        loginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(loginLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 435, 60, -1));

        genderErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(genderErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 217, 522, -1));

        passwordInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 249, 522, 42));

        phoneNumberInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(phoneNumberInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 110, 522, 42));

        userNameInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(userNameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 32, 524, 42));

        photo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        photo.setText("Avatar");
        jPanel1.add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 46, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Already have an account?");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 435, -1, -1));

        maleRadioBtn.setBackground(new java.awt.Color(255, 255, 255));
        genderGroup.add(maleRadioBtn);
        maleRadioBtn.setText("Male");
        maleRadioBtn.setOpaque(true);
        jPanel1.add(maleRadioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 190, 98, -1));

        passwordErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(passwordErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 323, 524, -1));

        passwordConfirmInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(passwordConfirmInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 355, 524, 42));

        imageHolder.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        imageHolder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageHolder.setText("photo");
        imageHolder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(imageHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 165, 191));

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password.setLabelFor(passwordInput);
        password.setText("Password");
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 223, 522, -1));

        femaleRadioBtn.setBackground(new java.awt.Color(255, 255, 255));
        genderGroup.add(femaleRadioBtn);
        femaleRadioBtn.setText("Female");
        femaleRadioBtn.setOpaque(true);
        jPanel1.add(femaleRadioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 98, -1));

        phoneNumberErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(phoneNumberErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 158, 522, -1));

        genders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        genders.setLabelFor(genders);
        genders.setText("Gender");
        jPanel1.add(genders, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 164, 522, -1));

        userNameErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(userNameErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 522, -1));

        chooseFileBtn.setBackground(new java.awt.Color(240, 173, 78));
        chooseFileBtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseFileBtn.setForeground(new java.awt.Color(255, 255, 255));
        chooseFileBtn.setText("Brown File");
        chooseFileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(chooseFileBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 109, 35));

        phoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumber.setLabelFor(phoneNumberInput);
        phoneNumber.setText("Phone number");
        jPanel1.add(phoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 86, 522, -1));

        userName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userName.setLabelFor(userNameInput);
        userName.setText("User name");
        jPanel1.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 524, -1));

        photoErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(photoErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 165, -1));

        showPasswordBtn.setBackground(new java.awt.Color(255, 255, 255));
        showPasswordBtn.setText("Show password");
        showPasswordBtn.setOpaque(true);
        jPanel1.add(showPasswordBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 297, 524, -1));

        showPasswordConfirmBtn.setBackground(new java.awt.Color(255, 255, 255));
        showPasswordConfirmBtn.setText("Show password");
        showPasswordConfirmBtn.setOpaque(true);
        jPanel1.add(showPasswordConfirmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 403, 524, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 89, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 799, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 799, 744));

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
            setTitle("Wibu Forever (hay gì gì đó)");
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
    private javax.swing.JPanel jPanel2;
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
