package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage Menu) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

 /*       primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        Application.launch(Menu.class, args);
        //launch(args);
    }
}
