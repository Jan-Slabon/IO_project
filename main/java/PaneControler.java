import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.util.Random;

public class PaneControler {
    @FXML
    public Button LoginButton;
    public Button SingUpButton;
    public PasswordField Password;
    public TextField Login;
    DataBase dataBase = new DataBase() {
        @Override
        public Boolean connect() {
            Random rand = new Random();
            return true;
        }

        @Override
        public Boolean executeQuery(String query) {
            return null;
        }

        @Override
        public Boolean executeQuery(String query, String[] result) {
            return null;
        }

        @Override
        public Boolean setIsolationLevel(String IsolationLevel) {
            return null;
        }

        @Override
        public Boolean LogIntoAccount(String login, String Password) {
            Random rand = new Random();
            return true;
        }
    };

    public PaneControler() {

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
            if (dataBase.LogIntoAccount(Login.getText(), Password.getText())) {
                System.out.println(Login.getText() + " " + Password.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                SplitPane splitPane = null;
                try {
                    splitPane = fxmlLoader.load();

                } catch (Exception expcept) {

                }
            } else {

            }
        };
        LoginButton.addEventHandler(ActionEvent.ACTION, LoginButtonActionHandler);
        EventHandler<ActionEvent> SingUpButtonActionHandler = e -> {
            System.out.println("Handler2 " + e.getSource());
        };
        SingUpButton.addEventHandler(ActionEvent.ACTION, SingUpButtonActionHandler);

    }
}
