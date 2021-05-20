import com.classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class PersonProfileControler {
    @FXML
    public Label Name;
    public Label Mail;
    public Label Login;
    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        Name.setText(user.getName() + " " + user.getSurename());
        Mail.setText(user.getEmail());
        Login.setText(user.getLogin());
    }
}
