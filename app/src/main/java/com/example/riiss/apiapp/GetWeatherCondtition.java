package com.example.riiss.apiapp;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by riiss on 2017-05-08.
 */

public class GetWeatherCondtition extends AsyncTask<String, Void, JSONObject> {

    private ImageView WeatherIcon;
    JSONObject topLevel = null;

    public GetWeatherCondtition(ImageView imageView ) {


        this.WeatherIcon=imageView;

    }


    @Override
    protected JSONObject doInBackground(String... strings) {



        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            topLevel = new JSONObject(builder.toString());



        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return topLevel;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObjekt) {



        String iconUrl = null;

        try {

            JSONArray weather = jsonObjekt.getJSONArray("weather");
            JSONObject weatherObjekt = weather.getJSONObject(0);
            //icon
            String iconCode = String.valueOf(weatherObjekt.getString("icon"));
            iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";


        } catch (JSONException e) {
            e.printStackTrace();
        }

        new DownloadImage().execute(iconUrl);
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            WeatherIcon.setImageBitmap(result);
        }
    }

}
