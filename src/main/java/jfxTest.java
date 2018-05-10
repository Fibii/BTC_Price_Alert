import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.scene.media.Media;

import java.io.File;
import java.io.FileReader;

public class jfxTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {

        BorderPane pane = new BorderPane();

        Scene scene = new Scene(pane,400,400);

        Bitcoin btc = new Bitcoin();


        try {
            File file = new File("src/main/java/np.mp3");
            Media music = new Media(file.toURI().toString());
            MediaPlayer mp = new MediaPlayer(music);
            mp.setVolume(1);
        if(btc.getUsdValue() < 10000){
            mp.play();
        }

        } catch (Exception e){
            e.printStackTrace();
        }



        primaryStage.setScene(scene);
        primaryStage.setTitle("hmm");
        primaryStage.show();
    }
}
