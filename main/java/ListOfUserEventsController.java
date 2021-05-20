import com.classes.DataBase;
import com.classes.Event;
import com.classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ListOfUserEventsController {
    User user;
    ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
    DataBase dataBase = context.getBean(DataBase.class);

    @FXML
    VBox box;

    @FXML
    void initialize() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void SetEvents() {
        List<Event> list = user.getEvents();
        FXMLLoader loader;
        try {
            AnchorPane parent;
            for (int i = 0; i < list.size(); i++) {
                loader = new FXMLLoader(getClass().getResource("EventProfile.fxml"));
                parent = loader.load();
                parent.setStyle("-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: black;");
                parent.setPrefWidth(box.getPrefWidth());
                EventProfileControler controller = loader.getController();
                System.out.println(list.get(i).toString());
                controller.setEvent(list.get(i));
                controller.setUser(user);
                controller.setUpLabels();
                box.getChildren().add(new AnchorPane(parent));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
