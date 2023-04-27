import views.LoginForm;
import controller.view.LoginController;
import java.util.ArrayList;
import models.DAO.UserDAO;
import models.HaveCategoryModel;
import models.ReviewModel;
import models.UserModel;
import views.LoginForm;
import views.panels.MainPanel;

public class SwingApp {

    public static void main(String[] args) {
        LoginForm login = new LoginForm();
        LoginController loginController = new LoginController(login);
        
    }
}
