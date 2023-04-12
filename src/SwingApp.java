import views.LoginForm;
import controller.view.LoginController;
import java.util.ArrayList;
import models.HaveCategoryModel;
import models.ReviewModel;
import views.LoginForm;

public class SwingApp {

    public static void main(String[] args) {
        LoginForm login = new LoginForm();
        LoginController loginController = new LoginController(login);
//        ArrayList<HaveCategoryModels> models = new ArrayList<>();
//        models.add(new HaveCategoryModels(1,1));
//        HaveCategoryModels a = new HaveCategoryModels(1,1);
//        System.out.println("" + (models.get(0).equals(a)));
    }
}
