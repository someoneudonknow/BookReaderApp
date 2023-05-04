package controller.panel;

//import com.mysql.cj.jdbc.Blob;
import java.sql.Blob;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.DAO.UserDAO;
import models.UserModel;
import other.Converter;
import other.Rules;
import other.Validate;
import views.MainView;
import views.panels.InforPanel;

public class InforController {

    private InforPanel infoPanel;
    private UserModel currentUser;
    private MainView mainView;

    private boolean isPasswordValueChanged;
    private boolean isAvatarChanged = false;

    public InforController(InforPanel panel, UserModel currentUser, MainView mainView) {
        this.infoPanel = panel;
        this.currentUser = currentUser;
        this.mainView = mainView;
        this.initUI();
        this.isPasswordValueChanged = !this.currentUser.getPassword().equals(String.valueOf(infoPanel.getPasswordInput().getPassword()));

        setVisibleBtn(false);

        this.infoPanel.onChooseFile(e -> {
            handleChooseFileBtnCLicked();
        });

        this.infoPanel.onShowPassword(e -> {
            handleShowPaswwordBtnClicked();
        });

        this.infoPanel.onShowPasswordConfirm(e -> {
            this.handleShowPasswordConfirmBtnClicked();
        });

        this.infoPanel.onEditBtnClick(e -> {
            this.handleEditBtnClicked();
        });

        this.infoPanel.onSaveBtnClick(e -> {
            this.handleSaveBtnClicked();
        });

        this.infoPanel.onCancelBtnClick(e -> {
            this.handleCancelBtnClicked();
        });

        this.infoPanel.getPasswordInput().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTogglePasswordConfirmField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTogglePasswordConfirmField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleTogglePasswordConfirmField();
            }
        });

        this.setUnEditable();
    }

    private void initUI() {
        UserModel currentUser = this.currentUser;
        this.infoPanel.getUserNameInput().setText(currentUser.getUserName());
        this.infoPanel.getPhoneNumberInput().setText(currentUser.getPhoneNumber());
        this.infoPanel.getPasswordInput().setText(currentUser.getPassword());
        this.infoPanel.getImageHolder().setIcon(Converter.convertBlobToImageIcon(currentUser.getAvatar()));
    }

    private void handleChooseFileBtnCLicked() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("image", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);
        int x = fileChooser.showDialog(this.infoPanel, "Save");

        if (x == JFileChooser.APPROVE_OPTION) {
            File selectedImage = fileChooser.getSelectedFile();
            String imagePath = selectedImage.getAbsolutePath();
            this.isAvatarChanged = true;
            if (imagePath.endsWith("png") || imagePath.endsWith("jpg") || imagePath.endsWith("ipeg")) {
                ImageIcon userImage = new ImageIcon(imagePath);
                Image resizedImage = userImage.getImage().getScaledInstance(this.infoPanel.getImageHolder().getWidth(), this.infoPanel.getImageHolder().getHeight(), Image.SCALE_SMOOTH);
                this.infoPanel.getImageHolder().setIcon(new ImageIcon(resizedImage));
            } else {
                JOptionPane.showMessageDialog(this.infoPanel, "Incorrect image format!");
            }
        }
    }

    private void handleShowPaswwordBtnClicked() {
        if (this.infoPanel.getShowPasswordBtn().isSelected()) {
            this.infoPanel.getPasswordInput().setEchoChar(((char) 0));
        } else {
            this.infoPanel.getPasswordInput().setEchoChar('\u2022');
        }
    }

    private void handleShowPasswordConfirmBtnClicked() {
        if (this.infoPanel.getShowPasswordConfirmBtn().isSelected()) {
            this.infoPanel.getPasswordConfirmInput().setEchoChar(((char) 0));
        } else {
            this.infoPanel.getPasswordConfirmInput().setEchoChar('\u2022');
        }
    }

    private void handleEditBtnClicked() {

        String password = JOptionPane.showInputDialog("Confirm your password");
        if (!(password == null)) {
            if (password.equals(this.currentUser.getPassword())) {
                setVisibleBtn(true);
                this.setEditable();
            } else {
                JOptionPane.showMessageDialog(infoPanel, "Incorrect password");
            }
        }
    }

    private void handleTogglePasswordConfirmField() {
        this.isPasswordValueChanged = !currentUser.getPassword().equals(String.valueOf(infoPanel.getPasswordInput().getPassword()));
        this.infoPanel.getPasswordConfirmWrapper().setVisible(this.isPasswordValueChanged);
    }

    private void handleSaveBtnClicked() {
        if (isFormValid()) {
            String userName = this.infoPanel.getUserNameInput().getText();
            String phoneNumber = this.infoPanel.getPhoneNumberInput().getText();
            String password = String.valueOf(this.infoPanel.getPasswordInput().getPassword());
            Blob avatar = Converter.convertImageToBlob((ImageIcon) this.infoPanel.getImageHolder().getIcon());
            List<String> changedField = new ArrayList<>();

            boolean isPhoneNumChanged = !phoneNumber.equals(this.currentUser.getPhoneNumber());
            boolean isUserNameChanged = !userName.equals(this.currentUser.getUserName());
            boolean formHasChanged = isPhoneNumChanged || isUserNameChanged || this.isPasswordValueChanged || this.isAvatarChanged;

            if (isUserNameChanged) {
                changedField.add("user_name");
            }
            if (this.isPasswordValueChanged) {
                changedField.add("user_password");
            }
            if (this.isAvatarChanged) {
                changedField.add("user_avatar");
            }
            if (isPhoneNumChanged) {
                changedField.add("user_phoneNumber");
            }

            UserDAO userDAO = new UserDAO();

            UserModel editedUser = new UserModel(this.currentUser.getId(),
                    userName,
                    password,
                    phoneNumber,
                    avatar,
                    this.currentUser.isIsManager(),
                    this.currentUser.getManagerId());
            try {
                userDAO.update(this.currentUser.getId(), editedUser, changedField);
                JOptionPane.showMessageDialog(infoPanel, "Save successfully");
                this.currentUser = editedUser;
                this.updateMainViewUI(editedUser);
                this.setUnEditable();
                setVisibleBtn(false);
            } catch (Exception ex) {
                if (ex.getMessage().equals("data_unchanged")) {
                    int x = JOptionPane.showConfirmDialog(infoPanel, "Your information haven't changed yet!. Do you want to save?", "", JOptionPane.YES_NO_OPTION);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(infoPanel, "Save successfully");
                    }
                     this.setUnEditable();
                     setVisibleBtn(false);
                } else if (ex.getMessage().equals("user_name_exists")) {
                    JOptionPane.showMessageDialog(infoPanel, "Username has already existed!");
                } else if (ex.getMessage().equals("user_phoneNumber_exists")) {
                    JOptionPane.showMessageDialog(infoPanel, "Phone number has already existed!");
                }
            }
            
        }
//        
    }

    private void updateMainViewUI(UserModel user) {
        this.mainView.setUserModel(user);
        if(this.infoPanel.getImageHolder().getIcon() != null) {
            this.mainView.getLbAvatar().setIcon(Converter.convertBlobToImageIcon(user.getAvatar()));
        }
        this.mainView.getLbUsername().setText(user.getUserName());
    }

    private void handleCancelBtnClicked() {
        this.initUI();
        this.setUnEditable();
        setVisibleBtn(false);
    }

    private void setUnEditable() {
        this.infoPanel.getUserNameInput().setEditable(false);
        this.infoPanel.getPhoneNumberInput().setEditable(false);
        this.infoPanel.getPasswordInput().setEditable(false);
        this.infoPanel.getPasswordConfirmInput().setEditable(false);
        this.infoPanel.getChooseFileBtn().setEnabled(false);
        this.infoPanel.getBtnSave().setEnabled(false);
        this.infoPanel.getBtnUndo().setEnabled(false);
        this.infoPanel.getShowPasswordBtn().setEnabled(false);
        this.infoPanel.getShowPasswordConfirmBtn().setEnabled(false);
        this.infoPanel.getBtnEdit().setEnabled(true);
        this.infoPanel.getPasswordConfirmWrapper().setVisible(false);
        this.infoPanel.getPasswordConfirmInput().setText("");
        if (this.infoPanel.getShowPasswordBtn().isSelected()) {
            this.infoPanel.getShowPasswordBtn().setSelected(false);
            this.infoPanel.getPasswordInput().setEchoChar('\u2022');
        }

        if (this.infoPanel.getShowPasswordConfirmBtn().isSelected()) {
            this.infoPanel.getShowPasswordConfirmBtn().setSelected(false);
            this.infoPanel.getPasswordConfirmInput().setEchoChar('\u2022');
        }
    }

    private void setEditable() {
        this.infoPanel.getUserNameInput().setEditable(true);
        this.infoPanel.getPhoneNumberInput().setEditable(true);
        this.infoPanel.getPasswordInput().setEditable(true);
        this.infoPanel.getPasswordConfirmInput().setEditable(true);
        this.infoPanel.getChooseFileBtn().setEnabled(true);
        this.infoPanel.getBtnSave().setEnabled(true);
        this.infoPanel.getBtnUndo().setEnabled(true);
        this.infoPanel.getShowPasswordBtn().setEnabled(true);
        this.infoPanel.getShowPasswordConfirmBtn().setEnabled(true);
        this.infoPanel.getBtnEdit().setEnabled(false);
    }

    private boolean isFormValid() {
        String userName = this.infoPanel.getUserNameInput().getText();
        String phoneNumber = this.infoPanel.getPhoneNumberInput().getText();
        String password = String.valueOf(this.infoPanel.getPasswordInput().getPassword());
        String passwordConfirm = String.valueOf(this.infoPanel.getPasswordConfirmInput().getPassword());

        Rules[] userNameRules = new Rules[]{new Rules("User name can not contains white space!", Rules.IS_CONTAINS_WHITE_SPACE)};
        Rules[] phoneNumberRules = new Rules[]{new Rules("Wrong phone number format!", Rules.IS_PHONE_NUMBER)};
        Rules[] passwordRules = new Rules[]{new Rules("Password must be greater than 6 characters!", Rules.IS_MIN, 6)};
        Rules[] passwordConfirmRules = new Rules[]{new Rules("Wrong password confirm!", Rules.IS_PASSWORD_CONFIRM, password)};

        Validate userNameValidate = new Validate(userName, userNameRules);
        Validate phoneNumberValidate = new Validate(phoneNumber, phoneNumberRules);
        Validate passwordValidate = new Validate(password, passwordRules);
        Validate passwordConfirmValidate = new Validate(passwordConfirm, passwordConfirmRules);

        boolean isUserNameValid = userNameValidate.isValid();
        boolean isPhoneNumberValid = phoneNumberValidate.isValid();
        boolean isPasswordValid = passwordValidate.isValid();
        boolean isPasswordConfirmValid = true;

        if (this.infoPanel.getPasswordConfirmWrapper().isVisible() == true) {
            isPasswordConfirmValid = passwordConfirmValidate.isValid();
            this.infoPanel.getPasswordConfirmErrorMessage().setText(passwordConfirmValidate.getErrorMessage());
        } else {
            this.infoPanel.getPasswordConfirmErrorMessage().setText("");
        }

        this.infoPanel.getUserNameErrorMessage().setText(userNameValidate.getErrorMessage());
        this.infoPanel.getPasswordErrorMessage().setText(passwordValidate.getErrorMessage());
        this.infoPanel.getPhoneNumberErrorMessage().setText(phoneNumberValidate.getErrorMessage());

        if (isUserNameValid && isPhoneNumberValid && isPasswordValid && isPasswordConfirmValid) {
            return true;
        }
        return false;
    }

    private void setVisibleBtn(boolean a) {
        this.infoPanel.getBtnSave().setVisible(a);
        this.infoPanel.getBtnUndo().setVisible(a);
        this.infoPanel.getChooseFileBtn().setVisible(a);

        this.infoPanel.getBtnEdit().setVisible(!a);
    }
}
