import com.classes.DataBase;
import com.classes.IMailTest;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;


public class SingUpFormControler {
    @FXML
    public TextField Name;
    public TextField Surename;
    public TextField Login;
    public TextField Mail;
    public Button Send;
    public PasswordField FirstPassword;
    public PasswordField SecondPassword;
    public DatePicker datePicker;
    public CheckBox Check;
    public Label ErrorBox;
    ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = context.getBean(DataBase.class);
    Stage stage;
    EventHandler<ActionEvent> SingUpButtonOnClick = e -> {
        User user = new User();
        IMailTest validate = context.getBean(IMailTest.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Pane pane = null;
        try {
            if (!FirstPassword.getText().equals(SecondPassword.getText())) {
                throw new Exception("Passwords are not the same");
            }
            if (!validate.ValidateEmail(Mail.getText())) {
                throw new Exception("That Email does not exist");
            }
            if (!Check.isSelected()) {
                throw new Exception("Agreement not checked");
            }
            user.setName(Name.getText());
            user.setSurename(Surename.getText());
            user.setLogin(Login.getText());
            user.setBirthDate(Date.valueOf(datePicker.getValue()));
            user.setPassHash(FirstPassword.getText());
            user.setEmail(Mail.getText());
            dataBase.connect();
            dataBase.CreateAccount(user);
            dataBase.disconect();
            stage.close();
        } catch (Exception except) {
            ErrorBox.setText(except.getMessage());
        }
    };

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void initialize() {
        Send.addEventHandler(ActionEvent.ACTION, SingUpButtonOnClick);
    }

}
