
import javafx.animation.*;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.monitor.CounterMonitor;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.TimerTask;

public class Main extends Application {
    public static final int width = 1280;
    public static final int height = 720;


    Image background = new Image("/img/menu.png");
    ImageView view = new ImageView();
    static Pane root = new Pane();
    static Scene scene = new Scene(root, width, height, Color.WHITE);
    private Menu menu;
    Random random = new Random();

    MediaView geemuSongu = new MediaView();
    @Override
    public void start(Stage primaryStage) throws Exception{


/*        File lexicon = new File("tempfile.txt");
        lexicon.createNewFile();

        Path path1 = Paths.get("./src/words.txt");

        File dictionary = new File("./src/words.txt");
        Path path2 = Paths.get("/src/words.txt");
        File copyOfDict = new File("./src/wordsCopy.txt");

        FileReader read = new FileReader("./src/words.txt");
        String line = Files.readAllLines(path1).get(2);
        int words = 20000;*/




        view.setImage(background);
        //view.setFitWidth(100%);
        view.setSmooth(true);
        view.setCache(true);
        view.setFitWidth(1280);
        view.setPreserveRatio(true);

        menu = new Menu();





        root.getChildren().addAll(view, menu);
        root.setPrefSize(width, height);
        primaryStage.setTitle("The game of words");






        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo16.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo32.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo64.png")));
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);



        primaryStage.show();
    }
    public static void fade(ImageView view){
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), view);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(event -> {
            view.setVisible(false);
        });
        ft.play();

    }
    public int pickSong(){
        int randomized = 1 + random.nextInt(4);
        return randomized;

    }
    public static void gameScene(){
        Pane root1 = new Pane();
/*        Label label = new Label("words");
        label.setFont(new Font("Times New Roman", 28));*/

        Media video = new Media(new File("src/Damero.mp4").toURI().toString());
        MediaPlayer player1 = new MediaPlayer(video);
        player1.play();
        MediaView viewik = new MediaView(player1);
        root1.setPrefSize(width, height);
        root1.getChildren().addAll(viewik);
        scene.setRoot(root1);


    }


    private class Menu extends Parent {
        public Menu(){
            final int offset = 1000;

            VBox menu0 = new VBox(15);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            Media bMusic = new Media(new File("src/music/menu.mp3").toURI().toString());
            MediaPlayer player = new MediaPlayer(bMusic);
            player.setVolume(0.1);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();

            MediaView mediaView = new MediaView(player);
            VBox menu1 = new VBox(15);
            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            Buttons btnBack = new Buttons("Back");
            btnBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            Buttons btnMusic = new Buttons("Music off");
            btnMusic.setOnMouseClicked(event -> {
                player.pause();
            });

            Buttons btnMusic1 = new Buttons("Music on");
            btnMusic1.setOnMouseClicked(event ->{
                player.play();
            });

            menu1.setTranslateX(offset);
            menu1.getChildren().addAll(btnBack, btnMusic, btnMusic1);

            Buttons btnStart = new Buttons("Start");
            btnStart.setOnMouseClicked(event ->{
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);
                tt.play();
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(2),
                                new KeyValue(player.volumeProperty(), 0)));
                timeline.play();

                Main.fade(view);
                Main.gameScene();

                    });


            Buttons btnOptions = new Buttons("Options");
            btnOptions.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            Buttons btnExit = new Buttons("Quit");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });


            menu0.getChildren().addAll(btnStart, btnOptions, btnExit, mediaView);
            Rectangle bg = new Rectangle(1280, 720);
            bg.setFill(Color.LIGHTGRAY);
            bg.setOpacity(0);
            getChildren().addAll(bg, menu0);




        }
    }



    private static class Buttons extends StackPane {
        private Text text;

        public Buttons(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(30));
            text.setFill(Color.AQUA);
            Rectangle bg = new Rectangle(400, 50);
            bg.setOpacity(0.4);
            bg.setFill(Color.DARKGREY);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(event -> {

                bg.setFill(Color.DARKGREY);
                text.setFill(Color.AQUA);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
