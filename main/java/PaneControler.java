import com.classes.DataBase;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PaneControler {

    ApplicationContext conn = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = conn.getBean(DataBase.class);

    @FXML
    public Button LoginButton;
    public Button SingUpButton;
    public PasswordField Password;
    public TextField Login;

    public PaneControler() {

    }


    EventHandler<ActionEvent> LoginButtonActionHandler = e -> {
        User user;
        user = dataBase.LogIntoAccount(Login.getText(), Password.getText());
        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen2.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                MainScreenControler controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.setLabels();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(anchorPane));
                newStage.show();
                System.out.println(user.getEvents().size());
                LoginButton.getScene().getWindow().hide();
            } catch (Exception expcept) {
                System.out.println(expcept + " poroblem z zaladowaniem anchor pane");
            }
        } else {
            System.out.println("User = null");
        }
    };


    EventHandler<ActionEvent> SingUpButtonActionHandler = e -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingUpForm.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            SingUpFormControler singup = fxmlLoader.getController();
            singup.setStage(stage);
            stage.show();
        } catch (Exception expcept) {
            System.out.println(expcept + " poroblem z zaladowaniem AnchorPane");
        }
    };


    @FXML
    void initialize() {
        try {
            if (!dataBase.connect()) {
                throw new Exception("Cannot connect to Data Base");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LoginButton.setDisable(true);
        }
        LoginButton.addEventHandler(ActionEvent.ACTION, LoginButtonActionHandler);
        SingUpButton.addEventHandler(ActionEvent.ACTION, SingUpButtonActionHandler);
    }
}
