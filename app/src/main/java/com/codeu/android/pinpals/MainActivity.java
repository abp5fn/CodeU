package com.codeu.android.pinpals;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
    public void onMapReady(final GoogleMap map) {

        try {
            // Attempt to get current location for the map.

            // Acquire a reference to the system Location Manager
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            // Define a listener that responds to location updates
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    // Called when a new location is found by the network location provider.
                    //makeUseOfNewLocation(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            // Use Network location data (less battery usage, better in theory but wasn't working on emulator)
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            // Or, use GPS location data:
            // String locationProvider = LocationManager.GPS_PROVIDER;

            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);

            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            LatLng current_loc = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());


            // Add a marker to the current location
            map.addMarker(new MarkerOptions()
                    .position(current_loc)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .title("You are here."));


            // Center map (aka "move camera") to current location & zoom in
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(current_loc, 13));

        } catch (Error e) {
            // If location error, assume location to be Boston.
            LatLng boston = new LatLng(42.3601, -71.0589);
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(boston, 13));

            final Marker boston_marker = map.addMarker(new MarkerOptions()
                    .title("Boston")
                    .position(boston));
        }

        GoogleMap.OnMapLongClickListener clickListener = new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng clicked_point) {
                Marker temp_marker = map.addMarker(new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                createPinPalEvent(map, clicked_point);


                temp_marker.remove();
            }
        };
        map.setOnMapLongClickListener(clickListener);

    }


    /**
     * Calls create activity (which adds PinPal event to database), then creates a new marker.
     *
     * @param map
     * @param clicked_point
     */
    public void createPinPalEvent(GoogleMap map, LatLng clicked_point) {


        /* activity types:
         * 1 = entertainment
         * 2 = fitness
         * 3 = food
         * 4 = social
         * 5 = studying
         * 0 = other
         */
        int activity_type = 1;
        MarkerOptions options;
        switch (activity_type) {
            case 1:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            case 2:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                break;
            case 3:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                break;
            case 4:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                break;
            case 5:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
            default:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        } // end switch
        map.addMarker(options);

    }

    public void showAddPinScreen(View v) {
        Intent nextScreen = new Intent(getApplicationContext(), AddPinFragment.class);

        /*
        //Sending data to another Activity
        nextScreen.putExtra("name", inputName.getText().toString());
        nextScreen.putExtra("email", inputEmail.getText().toString());

        Log.e("n", inputName.getText() + "." + inputEmail.getText());
        */

        startActivity(nextScreen);

        /*
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_pin, null);
        setContentView(view);
        */
    }

}