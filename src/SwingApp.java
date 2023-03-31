import views.LoginForm;
import controller.LoginController;
import views.LoginForm;

public class SwingApp {

    public static void main(String[] args) {
        LoginForm login = new LoginForm();
        LoginController loginController = new LoginController(login);
    }
}
