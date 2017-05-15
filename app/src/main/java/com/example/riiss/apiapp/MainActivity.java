package com.example.riiss.apiapp;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    String city="";
    String country="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.editText);
        TextView textView=(TextView) findViewById(R.id.textView5);
        editText.setText("");


        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String folderName = formatter.format(today);
        textView.setText(folderName);

        if(editText.getText().toString().trim().length()==0){

            editText.setText("Stad..");
            editText.setTextColor(Color.GRAY);
        }
        else
            editText.setTextColor(Color.BLACK);

//rsdrfetert
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });

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


        if(editText.getText().toString().trim().length()==0){

            editText.setText("Stad..");
            editText.setTextColor(Color.GRAY);
        }
        else
            editText.setTextColor(Color.BLACK);


        textView1.setText(editText.getText());
        city=editText.getText().toString();

        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=" + city
                +"," + country +"&appid=69585efc9d6d443139ff3fc87c85a87a&units=metric");


        new GetWeatherCondtition(imageView).execute(url);
        new GetWeatherTask(textView).execute(url);
        new GetPressureTask(textView2).execute(url);


    }

}
