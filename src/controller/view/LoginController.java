package controller.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import models.DAO.UserDAO;
import models.UserModel;
import other.Rules;
import other.Validate;
import views.LoginForm;
import views.MainView;
import views.RegisterForm;

public class LoginController {

    private LoginForm loginForm;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        this.loginForm.onLogin(e -> {
            this.handleLogin();
        });

        this.loginForm.onToggleShowPassword(e -> {
            this.handleToggleShowPassword();
        });
        this.loginForm.onRegisterLinkClicked(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loginForm.dispose();
                RegisterForm registerForm = new RegisterForm();
                new SignUpController(registerForm);
            }
        });
        this.loginForm.start();
    }

    private void handleToggleShowPassword() {
        if (loginForm.getShowPasswordBtn().isSelected()) {
            this.loginForm.getPasswordInput().setEchoChar((char) 0);
        } else {
            this.loginForm.getPasswordInput().setEchoChar('\u2022');
        }
    }

    private void handleLogin() {
        if (this.isFormValid()) {
            String userName = this.loginForm.getUserNameInput().getText();
            String password = String.valueOf(this.loginForm.getPasswordInput().getPassword());
            UserDAO userDAO = new UserDAO();
            UserModel loginUser = userDAO.login(userName, password);
            
            if (loginUser != null) {
                loginForm.dispose();
                JOptionPane.showMessageDialog(null, "Logged in successfully!");
                MainView mainView = new MainView(loginUser);
                new MainViewController(mainView);
            } else {
                JOptionPane.showMessageDialog(null, "Username or password incorrect!");
            }
        }
    }

    private boolean isFormValid() {
        String userName = this.loginForm.getUserNameInput().getText();
        String password = String.valueOf(this.loginForm.getPasswordInput().getPassword());
        Rules[] passwordRules = new Rules[]{new Rules("Please enter your pasword!", Rules.IS_REQUIRED),
            new Rules("Mật khẩu phải lớn hơn 6 kí tự!", Rules.IS_MIN, 6)};
        Rules[] userNameRules = new Rules[]{new Rules("Please enter your username!", Rules.IS_REQUIRED),
            new Rules("Username cannot have spaces!", Rules.IS_CONTAINS_WHITE_SPACE)};

        Validate validatePassword = new Validate(password, passwordRules);
        Validate validateUserName = new Validate(userName, userNameRules);

        boolean isPasswordValid = validatePassword.isValid();
        boolean isUserNameValid = validateUserName.isValid();

        this.loginForm.getPasswordErrorMess().setText(validatePassword.getErrorMessage());
        this.loginForm.getUserNameErrorMess().setText(validateUserName.getErrorMessage());

        if (isPasswordValid && isUserNameValid) {
            return true;
        }
        return false;
    }
}
