package com.example.riiss.apiapp;

import android.os.AsyncTask;
import android.widget.TextView;

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
 * Created by riiss on 2017-05-04.
 */

public class GetPressureTask extends AsyncTask<String, Void, String> {

    private TextView textView;




    public GetPressureTask(TextView textView) {

        this.textView = textView;

    }



    @Override

    protected String doInBackground(String... strings) {

        String weather = "UNDEFINED";

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

            JSONObject topLevel = new JSONObject(builder.toString());

            JSONObject main = topLevel.getJSONObject("main");

            weather = String.valueOf(main.getDouble("pressure"));



        } catch (IOException | JSONException e) {

            e.printStackTrace();

        }

        return weather;

    }



    @Override
    protected void onPostExecute(String pressure) {

        textView.setText(pressure + " hpa");

    }
}
