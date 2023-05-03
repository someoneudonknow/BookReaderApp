package controller.view;

import java.sql.Blob;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.DAO.UserDAO;
import models.UserModel;
import other.Converter;
import other.Rules;
import other.Validate;
import views.LoginForm;
import views.MainView;
import views.RegisterForm;

public class SignUpController {
    private RegisterForm rgsForm;

    public SignUpController(RegisterForm rgsForm) {
        this.rgsForm = rgsForm;
        this.rgsForm.onSignUp(e -> {
            handleSignUp();
        });
        this.rgsForm.onLoginLinkClicked(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rgsForm.dispose();
                LoginForm loginForm = new LoginForm();
                new LoginController(loginForm);
            }
        });
        this.rgsForm.onChooseFile(e -> {
            handleChooseFile();
        });
        this.rgsForm.onShowPassword(e -> {
            handleShowPassword();
        });
        this.rgsForm.onShowPasswordConfirm(e -> {
            handleShowPasswordConfirm();
        });
        this.rgsForm.start();
    }

    private boolean isFormValid() {
        String userName = this.rgsForm.getUserNameInput().getText();
        String phoneNumber = this.rgsForm.getPhoneNumberInput().getText();
        String password = String.valueOf(this.rgsForm.getPasswordInput().getPassword());
        String passwordConfirm = String.valueOf(this.rgsForm.getPasswordConfirmInput().getPassword());

        Rules[] userNameRules = new Rules[]{new Rules("Please enter your username!", Rules.IS_REQUIRED),
            new Rules("Username cannot have spaces!", Rules.IS_CONTAINS_WHITE_SPACE)};
        Rules[] phoneNumberRules = new Rules[]{new Rules("Please enter your phone number!", Rules.IS_REQUIRED),
            new Rules("Invalid phone number!", Rules.IS_PHONE_NUMBER)};
        Rules[] passwordRules = new Rules[]{new Rules("Please enter your password!", Rules.IS_REQUIRED),
            new Rules("Password must equal or greater than 6 characters!", Rules.IS_MIN, 6)};
        Rules[] passwordConfirmRules = new Rules[]{new Rules("Please confirm your password!", Rules.IS_REQUIRED),
            new Rules("Incorrect password!", Rules.IS_PASSWORD_CONFIRM, password)};

        Validate userNameValidate = new Validate(userName, userNameRules);
        Validate phoneNumberValidate = new Validate(phoneNumber, phoneNumberRules);
        Validate passwordValidate = new Validate(password, passwordRules);
        Validate passwordConfirmValidate = new Validate(passwordConfirm, passwordConfirmRules);

        boolean isUserNameValid = userNameValidate.isValid();
        boolean isPhoneNumberValid = phoneNumberValidate.isValid();
        boolean isPasswordValid = passwordValidate.isValid();
        boolean isPasswordConfirmValid = passwordConfirmValidate.isValid();
        boolean isGenderValid = (this.rgsForm.getGenderGroup().getSelection() != null);

        this.rgsForm.getUserNameErrorMessage().setText(userNameValidate.getErrorMessage());
        this.rgsForm.getPasswordErrorMessage().setText(passwordValidate.getErrorMessage());
        this.rgsForm.getPasswordConfirmErrorMessage().setText(passwordConfirmValidate.getErrorMessage());
        this.rgsForm.getPhoneNumberErrorMessage().setText(phoneNumberValidate.getErrorMessage());
        this.rgsForm.getGenderErrorMessage().setText(isGenderValid ? "" : "Please choose your gender!");

        if (isUserNameValid && isPhoneNumberValid && isPasswordValid && isPasswordConfirmValid && isGenderValid ) {
            return true;
        }
        return false;
    }

    private void handleSignUp() {
        if (isFormValid()) {
            String userName = this.rgsForm.getUserNameInput().getText();
            String phoneNumber = this.rgsForm.getPhoneNumberInput().getText();
            String password = String.valueOf(this.rgsForm.getPasswordInput().getPassword());
            Blob avatar = Converter.convertImageToBlob((ImageIcon) this.rgsForm.getImageHolder().getIcon());
            
            UserDAO userDAO = new UserDAO();
            UserModel signUpUser = new UserModel(0, userName, password, phoneNumber, avatar, false, 1);
               
            if(!userDAO.insertNewUser(signUpUser)) {
                JOptionPane.showMessageDialog(rgsForm, "Account already existed. Do you want to log in ?");
                this.resetForm();
            }else {
                this.rgsForm.dispose();
                UserModel regiterUser = userDAO.login(userName, password);
                new MainViewController(new MainView(regiterUser));
            }
        }
    }

    private void handleChooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("image", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this.rgsForm, "Save");

        if (x == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            String imagePath = selectedImage.getAbsolutePath();
            if (imagePath.endsWith("png") || imagePath.endsWith("jpg") || imagePath.endsWith("ipeg")) {
                ImageIcon userImage = new ImageIcon(imagePath);
                Image resizedImage = userImage.getImage().getScaledInstance(this.rgsForm.getImageHolder().getWidth(), this.rgsForm.getImageHolder().getHeight(), Image.SCALE_SMOOTH);
                this.rgsForm.getImageHolder().setIcon(new ImageIcon(resizedImage));
            } else {
                JOptionPane.showMessageDialog(this.rgsForm, "Wrong image format!");
            }
        }
    }

    private void handleShowPassword() {
        if (this.rgsForm.getShowPasswordBtn().isSelected()) {
            this.rgsForm.getPasswordInput().setEchoChar(((char) 0));
        } else {
            this.rgsForm.getPasswordInput().setEchoChar('\u2022');
        }
    }
    
    private void handleShowPasswordConfirm() {
        if (this.rgsForm.getShowPasswordConfirmBtn().isSelected()) {
            this.rgsForm.getPasswordConfirmInput().setEchoChar(((char) 0));
        } else {
            this.rgsForm.getPasswordConfirmInput().setEchoChar('\u2022');
        }
    }
    
    private void resetForm() {
        this.rgsForm.getUserNameInput().setText("");
        this.rgsForm.getPhoneNumberInput().setText("");
        this.rgsForm.getPasswordInput().setText("");
        this.rgsForm.getPasswordConfirmInput().setText("");
        this.rgsForm.getImageHolder().setIcon(null);
    }
}
