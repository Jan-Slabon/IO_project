import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneControler {


    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public StackPane stackPane;

    public StackPaneControler() {

    }

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        stackPane.getChildren().add(pane);
    }
}
