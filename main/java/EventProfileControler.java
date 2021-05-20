import com.classes.Event;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class EventProfileControler {

    public Event event;
    public User user;
    @FXML
    public Label Name;
    public Label Location;
    public Label Date;
    public HBox Tile;
    public Button button;
    EventHandler<ActionEvent> click = e -> {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetails.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
            EventDetailsControler controler = loader.getController();
            controler.setEvent(event);
            controler.setUser(user);
            controler.SetLabels();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }
    };

    public void setUser(User user) {
        this.user = user;
    }

    public void setUpLabels() {
        Name.setText(event.getName());
        Location.setText(event.getAdres().toString());
        if (event.getData() != null)
            Date.setText(event.getData().toString());
        else Date.setText("Brak Daty");
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @FXML
    void initialize() {
        button.addEventHandler(ActionEvent.ACTION, click);
    }
}
