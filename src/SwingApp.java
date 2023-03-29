
//import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import views.forms.LoginForm;

public class SwingApp {

    public static void main(String[] args) {
        LoginForm login = new LoginForm();
        login.start();
    }
}
