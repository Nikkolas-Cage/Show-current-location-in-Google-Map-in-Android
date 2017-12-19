package com.techiesatish.googlemapproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class GoogleMapACT extends AppCompatActivity {
Button searchPlace;
TextView showPlace;
int REQUEST_CODE= 100;
String TAG="GoogleMapAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_act);
        searchPlace = (Button) findViewById(R.id.btn_autoSearch);
        showPlace=(TextView) findViewById(R.id.tv_placeDetail);
    }

    public void showLoc(View view) {
        try {
            Intent intent = new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
        }
        catch (GooglePlayServicesNotAvailableException e) {
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e(TAG, "Place Details: " +place.getName()+",\n"+place.getAddress()+",\n"+place.getLatLng());

                showPlace.setText(place.getName()+",\n"+place.getAddress()+",\n"+place.getLatLng());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);

                Log.e(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }


}