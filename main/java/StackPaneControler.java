import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class StackPaneControler {

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
