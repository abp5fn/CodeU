package com.codeu.android.pinpals;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Use Network location data (less battery usage, better in theory but wasn't working on emulator)
        //String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or, use GPS location data:
        String locationProvider = LocationManager.GPS_PROVIDER;

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);

        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        // Add a marker in last known location and move the camera.
        LatLng last = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
        map.addMarker(new MarkerOptions().position(last).title("Marker at last known location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(last));

    }



}