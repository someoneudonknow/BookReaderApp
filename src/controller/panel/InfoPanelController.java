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

public class InfoPanelController {

    private InforPanel infoPanel;
    private UserModel currentUser;
    private MainView mainView;

    public InfoPanelController(InforPanel panel, UserModel currentUser, MainView mainView) {
        this.infoPanel = panel;
        this.currentUser = currentUser;
        this.mainView = mainView;

        this.initUI();

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
            if (imagePath.endsWith("png") || imagePath.endsWith("jpg") || imagePath.endsWith("ipeg")) {
                ImageIcon userImage = new ImageIcon(imagePath);
                Image resizedImage = userImage.getImage().getScaledInstance(this.infoPanel.getImageHolder().getWidth(), this.infoPanel.getImageHolder().getHeight(), Image.SCALE_SMOOTH);
                this.infoPanel.getImageHolder().setIcon(new ImageIcon(resizedImage));
            } else {
                JOptionPane.showMessageDialog(this.infoPanel, "Sai định dạng ảnh!");
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
        String password = JOptionPane.showInputDialog("Nhập lại mật khẩu để chỉnh sửa!");
        if (password.equals(this.currentUser.getPassword())) {
            this.setEditable();
        } else {
            JOptionPane.showMessageDialog(infoPanel, "Sai mật khẩu");
        }
    }

    private void handleSaveBtnClicked() {
        if (isFormValid()) {
            String userName = this.infoPanel.getUserNameInput().getText();
            String phoneNumber = this.infoPanel.getPhoneNumberInput().getText();
            String password = String.valueOf(this.infoPanel.getPasswordInput().getPassword());
            Blob avatar = Converter.convertImageToBlob((ImageIcon) this.infoPanel.getImageHolder().getIcon());
            UserDAO userDAO = new UserDAO();

            boolean isPhoneNumChanged = !phoneNumber.equals(this.currentUser.getPhoneNumber());
            boolean isUserNameChanged = !userName.equals(this.currentUser.getUserName());
            boolean isPasswordChanged = !password.equals(this.currentUser.getPassword());

            System.out.println("isPhoneNumChanged: " + isPhoneNumChanged);
            System.out.println("isUserNameChanged: " + isUserNameChanged);
            System.out.println("isPasswordChanged: " + isPasswordChanged);
            UserModel editedUser = new UserModel(this.currentUser.getId(),
                    userName,
                    password,
                    phoneNumber,
                    avatar,
                    this.currentUser.isIsManager(),
                    this.currentUser.getManagerId());

            if (userDAO.updateUser(this.currentUser.getId(), editedUser, isPhoneNumChanged, isUserNameChanged)) {
                JOptionPane.showMessageDialog(infoPanel, "Sửa thông tin thành công");
                this.currentUser = editedUser;
                this.updateMainViewUI(editedUser);
                this.setUnEditable();
            } else {
                JOptionPane.showMessageDialog(infoPanel, "Số điện thoại hoặc tên đăng nhập đã tồn tại!");
            }
        }
    }

    private void updateMainViewUI(UserModel user) {
        this.mainView.setUserModel(user);
        this.mainView.getLbAvatar().setIcon(Converter.convertBlobToImageIcon(user.getAvatar()));
        this.mainView.getLbUsername().setText(user.getUserName());
    }

    private void handleCancelBtnClicked() {
        this.initUI();
        this.setUnEditable();
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
        boolean isPasswordConfirmValid = passwordConfirmValidate.isValid();

        this.infoPanel.getUserNameErrorMessage().setText(userNameValidate.getErrorMessage());
        this.infoPanel.getPasswordErrorMessage().setText(passwordValidate.getErrorMessage());
        this.infoPanel.getPasswordConfirmErrorMessage().setText(passwordConfirmValidate.getErrorMessage());
        this.infoPanel.getPhoneNumberErrorMessage().setText(phoneNumberValidate.getErrorMessage());
//        this.infoPanel.getPhotoErrorMessage().setText(isPhotoValid ? "" : "Vui longf cho");

        if (isUserNameValid && isPhoneNumberValid && isPasswordValid && isPasswordConfirmValid) {
            return true;
        }
        return false;
    }
}
