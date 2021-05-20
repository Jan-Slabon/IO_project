import com.classes.Adres;
import com.classes.DataBase;
import com.classes.Event;
import com.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;

public class EventCreatorPanelControler {
    @FXML
    public TextField Name;
    public TextField Country;
    public TextField City;
    public TextField Street;
    public TextField Number;
    public TextField Flat;
    public TextArea Description;
    public Button Submit;
    public DatePicker DatePicker;
    ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = context.getBean(DataBase.class);
    User user;
    EventHandler<ActionEvent> SubmitEvent = e -> {
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        Adres adres = new Adres(Country.getText(), City.getText(), Street.getText(), Integer.parseInt(Number.getText()), Integer.parseInt(Flat.getText()));
        Event event = new Event(0, adres, Name.getText(), Description.getText(), userList, null);
        event.setData(Date.valueOf(DatePicker.getValue()));
        dataBase.connect();
        dataBase.CreateEvent(event);
        dataBase.disconect();
        Submit.getScene().getWindow().hide();
    };

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        Submit.addEventHandler(ActionEvent.ACTION, SubmitEvent);
    }
}
