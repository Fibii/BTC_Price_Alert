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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiController {

    @FXML
    private Text errorText;

    @FXML
    private TextField textField;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Text btcPriceText;


    @FXML
    private Button setBtn;
    private GuiProcessor guiProcessor;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File file;

    private int type;
    private  ScheduledExecutorService scheduledExecutorService;
    private  Runnable r1;

    public GuiController(){
        guiProcessor  = new GuiProcessor();

        file = new File("src/main/java/np.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //create a runnable that calls the initialize method
        r1 = new Runnable() {
            @Override
            public void run() {
                choiceBoxListener();
            }
        };

        //create scheduled thread that class t1
        scheduledExecutorService =
                Executors.newScheduledThreadPool(1);


    }

    private int choiceboxType(){
        //checks the chosen choicebox values and returns as an int
        if (choiceBox.getValue().equalsIgnoreCase("usd")){
            this.type = 1;
        } else if (choiceBox.getValue().equalsIgnoreCase("eur")) {
            this.type = 2;
        } else if (choiceBox.getValue().equalsIgnoreCase("gbp")) {
            this.type = 3;
        }
        return this.type;
    }

    public void choiceBoxListener(){

        //set the btc price by default
        btcPriceText.setText("current btc price: " +
                guiProcessor.currentBtcPrice(choiceboxType()));

        //set the current btc price when the choicebox is changed
        choiceBox.getSelectionModel().selectedItemProperty().addListener(
                (v,oldValue,newValue) ->
                        btcPriceText.setText("current btc price: " + guiProcessor.currentBtcPrice(choiceboxType())) );

    }

    @FXML
    public void initialize(){

        choiceBox.getItems().add("USD");
        choiceBox.getItems().add("EUR");
        choiceBox.getItems().add("GBP");

        //select usd by default
        choiceBox.getSelectionModel().selectFirst();


        choiceBoxListener();

        scheduledExecutorService.scheduleAtFixedRate(r1,0,1, TimeUnit.MINUTES);

    }



    @FXML
    void setBtn_Click(ActionEvent event) {
        try{
            //get the user input & parse it ..
            double input = Double.parseDouble(textField.getText());

            int type = 0;

            if(guiProcessor.isLessThanThreshold(input,this.type)){
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
           // errorText.setText("Please only use numbers");
        }
    }

}
