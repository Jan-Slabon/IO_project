import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("Style.fxml"));
        StackPane stackpane = loader.load();
        Scene scene = new Scene(stackpane, stackpane.getPrefHeight(), stackpane.getPrefWidth());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Party Time");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Icon.png")));
        primaryStage.show();
        //initialize(primaryStage);
    }

    public void initialize(Stage primaryStage) {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(this.getClass().getResource("LoginScreen.fxml"));
        PaneControler paneControler = loader2.getController();
        paneControler.setStage(primaryStage);
    }
}
