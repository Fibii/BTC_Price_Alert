package gui;
import java.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiProcessor  {

   private Bitcoin btc;

    public GuiProcessor(){
      btc = new Bitcoin();

        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(btc,0,1, TimeUnit.MINUTES);
    }

    public boolean isLessThanThreshold(double input, int type){

        boolean status = false;

        double value = currentBtcPrice(type);

        if (value < input){
        status = true;
        }

        return status;
    }

    public double currentBtcPrice(int type){
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
}
