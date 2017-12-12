package com.techiesatish.googlemapproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class ShowPath extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    PlaceAutocompleteFragment placeAutoComplete1,placeAutoComplete2;
    LatLng sourceLatLang, destLatLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_path);
        getSupportActionBar().hide();

        placeAutoComplete1 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete1);
        placeAutoComplete1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place1 selected: " + place.getName());
                Log.e("LatLangi","source Latlang" +place.getLatLng());
                sourceLatLang=place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        placeAutoComplete2=(PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete2);
        placeAutoComplete2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                destLatLang=place.getLatLng();
                Log.e("Showpath","DestLatLang" +destLatLang);
            }

            @Override
            public void onError(Status status) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}