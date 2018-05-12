package gui;
import java.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiProcessor  {

   private Bitcoin btc;

   private double input;

    public GuiProcessor(){
      btc = new Bitcoin();

       //automatically update the price every minute
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(btc,0,1, TimeUnit.MINUTES);
    }

    public boolean isLessThanThreshold(int type, double userInput){
        //check if the user input is less than bitcoin's price


        boolean status = false;

        double value = currentBtcPrice(type);

        if (value < userInput){
        status = true;
        }
        return status;
    }

    public double currentBtcPrice(int type){
        //this method takes an int as a parameter and returns
        //btc price of the currency
        // check the choiceboxType() method in GuiController to understand more

        double usdValue =  btc.getUsdValue();
        double eurValue = btc.getEurValue();
        double gbpValue = btc.getGbpValue();

        double value = 0.0;

        switch (type) {
            case 1 : // usd
                value = usdValue;
                break;
            case 2 : //eur
                value = eurValue;
                break;
            case 3 : //gbp
                value = gbpValue;
                break;
        }
        return value;
    }

    public void setInput(double input){
        //this method sets the input variable when the "set" button is clicked
         this.input = input;

    }
}
