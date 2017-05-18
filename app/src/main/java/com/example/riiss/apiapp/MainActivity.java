package com.example.riiss.apiapp;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        TextView textView=(TextView) findViewById(R.id.textView5);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String folderName = formatter.format(today);
        textView.setText(folderName);

        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

                city=place.getName().toString();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Fel");
                builder.setMessage("Ett fel inträffades : " + status);
                builder.setNegativeButton("OK" , null);
                builder.create();
                builder.show();

            }
        });

    }


    public void Searchbutton(View v){

        //textviews
        final TextView textView = (TextView) findViewById(R.id.textViewtemp);
        final TextView textView2=(TextView) findViewById(R.id.textViewpressure);
        //imageviews
        ImageView imageView=(ImageView)findViewById(R.id.imageView2);

        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=" + city
                +"," + country +"&appid=69585efc9d6d443139ff3fc87c85a87a&units=metric");

        new GetWeatherCondtition(imageView).execute(url);
        new GetWeatherTask(textView).execute(url);
        new GetPressureTask(textView2).execute(url);

    }

}
