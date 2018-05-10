import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import Api.*;

import java.io.IOException;

public class Bitcoin {

    private OkHttpClient client;

    public Bitcoin(){
        client = new OkHttpClient();
    }



    private String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private CoindeskAPI parseTheJson(){
        //get the json
        String json = null;

        try{
            json = getJson("https://api.coindesk.com/v1/bpi/currentprice.json");
        }catch (Exception e){
            e.printStackTrace();
        }

        //parse the json
        Gson gson = new Gson();

        CoindeskAPI coindeskAPI = gson.fromJson(json,CoindeskAPI.class);

        return coindeskAPI;

    }

    public double getUsdValue(){
        CoindeskAPI coindeskAPI = parseTheJson();
        double usdValue = Double.parseDouble(coindeskAPI.getBpi().getUSD().toString());

        return usdValue;
    }


}

/*
    to do : threads
            
 */