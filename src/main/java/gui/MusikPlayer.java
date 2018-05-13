package gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class MusikPlayer implements Runnable{

    private GuiProcessor guiProcessor ;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File file;
    private double userInput;
    private int type;


    MusikPlayer(){
        guiProcessor = new GuiProcessor();

        file = new File("src/main/java/np.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

    }

    public void setType(int type){
        this.type = type;
    }

    private int getType(){
        return this.type;
    }

    public void setUserInput(double input){
        this.userInput = input;
    }

    private double getUserInput(){
        return  this.userInput;
    }
    public void playMusic(){
        if(guiProcessor.isLessThanThreshold(getType(),getUserInput())) {
            mediaPlayer.setVolume(1);
            mediaPlayer.play();
            //reset the player to the start of the song
            mediaPlayer.seek(Duration.seconds(0));
        }
    }

    @Override
    public void run(){
        playMusic();
    }
}
