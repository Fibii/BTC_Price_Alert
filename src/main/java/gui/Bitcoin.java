package gui;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import Api.*;

import java.io.IOException;

public class Bitcoin implements Runnable {

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

        String json = null;

        //get the json
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
        double value = Double.parseDouble(coindeskAPI.getBpi().getUSD().toString());
        return value;
    }

    public double getEurValue(){
        CoindeskAPI coindeskAPI = parseTheJson();
        double value = Double.parseDouble(coindeskAPI.getBpi().getEUR().toString());
        return value;
    }

    public double getGbpValue(){
        CoindeskAPI coindeskAPI = parseTheJson();
        double value = Double.parseDouble(coindeskAPI.getBpi().getGBP().toString());
        return value;
    }

    @Override public void run(){
        //call the parseTheJson so it will update every now and then
        parseTheJson();
    }
}