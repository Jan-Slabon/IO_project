import com.classes.DataBase;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PaneControler {

    ApplicationContext conn = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = conn.getBean(DataBase.class);
    private StackPaneControler stackPaneControler;
    @FXML
    public Button LoginButton;
    public Button SingUpButton;
    public PasswordField Password;
    public TextField Login;
    private Stage stage;

    public PaneControler() {

    }

    public void setStackPaneControler(StackPaneControler stackPaneControler) {
        this.stackPaneControler = stackPaneControler;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
        EventHandler<ActionEvent> LoginButtonActionHandler = e -> {
            User user;
            user = dataBase.LogIntoAccount(Login.getText(), Password.getText());
            if (user != null) {
                System.out.println(Login.getText() + " " + Password.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                SplitPane splitPane = null;
                try {
                    splitPane = fxmlLoader.load();
                    stackPaneControler.stackPane.getChildren().clear();
                    stackPaneControler.stackPane.getChildren().add(splitPane);

                } catch (Exception expcept) {
                    System.out.println(expcept + " poroblm z zaladowaniem split pane");
                }
            } else {
                System.out.println("User = null");
            }
        };
        LoginButton.addEventHandler(ActionEvent.ACTION, LoginButtonActionHandler);
        EventHandler<ActionEvent> SingUpButtonActionHandler = e -> {
            System.out.println("Handler2 " + e.getSource());
        };
        SingUpButton.addEventHandler(ActionEvent.ACTION, SingUpButtonActionHandler);

    }
}
