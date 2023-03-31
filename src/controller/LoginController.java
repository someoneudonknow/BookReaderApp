package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import other.Rules;
import other.Validate;
import views.LoginForm;
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
    
    private void handleRegisterLinkCliked() {
        
    }
    
    private void handleLogin() {
        if(this.isFormValid()) {
            //check if login true here
            if (true) {
                loginForm.dispose();
                // change to user page here here
                JOptionPane.showMessageDialog(null, "Login success");
            }else {
                JOptionPane.showMessageDialog(null, "Wrong user name or password!");
            }
        }
    }
    
    private boolean isFormValid() {
        String userName = this.loginForm.getUserNameInput().getText();
        String password = String.valueOf(this.loginForm.getPasswordInput().getPassword());
        Rules[] passwordRules = new Rules[]{new Rules("Please enter password!", Rules.IS_REQUIRED),
            new Rules("Password must be greater than 6 characters", Rules.IS_MIN, 6)};
        Rules[] userNameRules = new Rules[]{new Rules("Please enter user name!", Rules.IS_REQUIRED),
                                            new Rules("User name can not contains white space!", Rules.IS_CONTAINS_WHITE_SPACE)};

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
