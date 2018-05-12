package gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


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

    private int type;
    private  ScheduledExecutorService scheduledExecutorService;
    private  Runnable r1;
    private  Runnable r2;
    private double input;
    private MusikPlayer musikPlayer;


    public GuiController(){
        guiProcessor  = new GuiProcessor();
        musikPlayer = new MusikPlayer();

        //creating a runnable to use threads
        r1 = new Runnable() {
            @Override
            public void run() {

                choiceBoxListener();
               // musikPlayer.playMusic();
            }
        };


        //create scheduled thread
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
        /* the purpose of this method is to change the value of btc
        when the user changes the value of the choicebox */


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

        musikPlayer.setType(choiceboxType());
        choiceBoxListener();


        scheduledExecutorService.scheduleAtFixedRate(r1, 0, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(musikPlayer, 0, 2, TimeUnit.SECONDS);

    }



    @FXML
    void setBtn_Click(ActionEvent event) {

        try{
            //get the user input & parse it ..
            this.input = Double.parseDouble(textField.getText());
            musikPlayer.setUserInput(input);

        }catch  (Exception e){
           //e.printStackTrace();
           errorText.setFill(Color.RED);
            errorText.setText("Please only use numbers");
        }
    }

}
