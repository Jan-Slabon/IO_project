import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainScreenControler {


    @FXML
    public Button CreateEventButton;
    public Button ListOfEventButton;
    public Button UserEventsButton;
    public Button UserAttendedEventsButton;
    public Label Login;
    public Label Name;
    public Label Mail;
    User user;
    EventHandler<ActionEvent> UserEvents = e -> {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOfUserEvents.fxml"));
        try {
            Parent parent = loader.load();
            ListOfUserEventsController controler = loader.getController();
            controler.setUser(user);
            controler.SetEvents();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }
    };
    EventHandler<ActionEvent> UserAttendedEvents = e -> {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOfAttendedEvents.fxml"));
        try {
            Parent parent = loader.load();
            ListOfAttendedEventsController controler = loader.getController();
            controler.setUser(user);
            controler.SetEvents();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }
    };
    EventHandler<ActionEvent> AddEvent = e -> {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventCreatorPanel.fxml"));
        try {
            Parent parent = loader.load();
            EventCreatorPanelControler controler = loader.getController();
            controler.setUser(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }
    };

    public void setLabels() {
        Name.setText(user.getName() + " " + user.getSurename());
        Mail.setText(user.getEmail());
        Login.setText(user.getLogin());
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        CreateEventButton.addEventHandler(ActionEvent.ACTION, AddEvent);
        UserEventsButton.addEventHandler(ActionEvent.ACTION, UserEvents);
        UserAttendedEventsButton.addEventHandler(ActionEvent.ACTION, UserAttendedEvents);
    }


}
