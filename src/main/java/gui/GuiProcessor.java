package gui;
import java.*;

public class GuiProcessor {

   private Bitcoin btc;

    public GuiProcessor(){
      btc = new Bitcoin();
    }

    public boolean isLessThanThreshold(double input, int type){
        double usdValue =  btc.getUsdValue();
        double eurValue = btc.getEurValue();
        double gbpValue = btc.getGbpValue();

        boolean status = false;


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

        if (value < input){
        status = true;
        }

        return status;
    }
}
