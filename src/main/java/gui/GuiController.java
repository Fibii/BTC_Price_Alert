package gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;

public class GuiController {

    @FXML
    private Text errorText;

    @FXML
    private TextField textField;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button setBtn;
    private GuiProcessor guiProcessor;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File file;

    public GuiController(){
        guiProcessor  = new GuiProcessor();

        file = new File("src/main/java/np.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    @FXML
    public void initialize(){
        choiceBox.getItems().add("USD");
        choiceBox.getItems().add("EUR");
        choiceBox.getItems().add("GBP");

        //select usd by default
        choiceBox.getSelectionModel().selectFirst();
    }



    @FXML
    void setBtn_Click(ActionEvent event) {
        try{
            //get the user input & parse it ..
            double input = Double.parseDouble(textField.getText());

            int type = 0;
            //check the choicebox
            if (choiceBox.getValue().equalsIgnoreCase("usd")){
                type = 1;
            } else if (choiceBox.getValue().equalsIgnoreCase("eur")) {
                type = 2;
            } else if (choiceBox.getValue().equalsIgnoreCase("gbp")) {
                type = 3;
            }

            if(guiProcessor.isLessThanThreshold(input,type)){
                //errorText.setText("Good");
               // errorText.setFill(Color.GREEN);
                mediaPlayer.setVolume(1);
                mediaPlayer.play();
            } else {
                errorText.setText("not below threshold");
            }

        }catch  (Exception e){
           e.printStackTrace();
           // errorText.setFill(Color.RED);
           // errorText.setText("Please use only numbers");
        }
    }

}
