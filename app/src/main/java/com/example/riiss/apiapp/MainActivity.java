package com.example.riiss.apiapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class MainActivity extends AppCompatActivity {

    String city="";
    String country="";
    String weather="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.editText);
        editText.setText("");


    }


    public void Searchbutton(View v){

        //edittexts
        EditText editText=(EditText) findViewById(R.id.editText);
        //textviews
        TextView textView = (TextView) findViewById(R.id.textViewtemp);
        TextView textView1=(TextView) findViewById(R.id.textView4);
        TextView textView2=(TextView) findViewById(R.id.textViewpressure);
        //imageviews
        ImageView imageView=(ImageView)findViewById(R.id.imageView2);


        textView1.setText(editText.getText());
        city=editText.getText().toString();

        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=" + city
                +"," + country +"&appid=69585efc9d6d443139ff3fc87c85a87a&units=metric");


        new GetWeatherCondtition(imageView).execute(url);
        new GetWeatherTask(textView).execute(url);
        new GetPressureTask(textView2).execute(url);


    }

}
