import com.classes.DataBase;
import com.classes.Event;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class EventDetailsControler {
    @FXML
    public Label Name;
    public Label Description;
    public Label Adress;
    public Label date;
    public Button AddUser;
    public Button DeleteUser;
    public TextField UserLogin;
    public TextField UserToDelete;
    ApplicationContext conn = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = conn.getBean(DataBase.class);
    User user;
    Event event;
    EventHandler<ActionEvent> AddUserTeEvent = e -> {
        try {
            dataBase.connect();
            User newUser = dataBase.getUser(UserLogin.getText());
            if (newUser == null) throw new Exception("no such User");
            dataBase.AddUserToEvent(newUser, user, event);
            dataBase.disconect();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }
    };
    EventHandler<ActionEvent> DeleteUserFromEvent = b -> {
        try {
            dataBase.connect();
            User newUser = dataBase.getUser(UserToDelete.getText());
            if (newUser == null) throw new Exception("no such User");
            dataBase.RemoveUserFromEvent(newUser, user, event);
            dataBase.disconect();
        } catch (Exception except) {
            System.out.println(except.getMessage());
        }

    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @FXML
    void initialize() {
        AddUser.addEventHandler(ActionEvent.ACTION, AddUserTeEvent);
        DeleteUser.addEventHandler(ActionEvent.ACTION, DeleteUserFromEvent);
    }

    public void SetLabels() {
        Name.setText(event.getName());
        Description.setText(event.getDescription());
        Adress.setText(event.getAdres().toString());
        date.setText(event.getData().toString());
    }
}