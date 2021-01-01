package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(1280, 720);
        InputStream background = Files.newInputStream(Paths.get("src/img/menu.png"));
        Image img = new Image(background);
        background.close();


        Stage primaryStage = new Stage();
        primaryStage.setTitle("Words");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }
}