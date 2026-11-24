package app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("PhoneBook");
        stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("contact-view.fxml")), 820, 620));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}