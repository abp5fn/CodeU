package com.codeu.android.pinpals;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    static GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //how to go through query of pins and do stuff with them
        //in this case, doing stuff is putting them on the map --> creating new pins



      //  String currentTime = DateFormat.getDateTimeInstance().format(new Date());

        Calendar c = Calendar.getInstance();



       // System.out.println(currentTime);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pins");
        //query.whereEqualTo("date", "May 4, 2015");
       // query.whereEqualTo("Date", toString(c.get(Calendar.DATE)));
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, ParseException e) {


                for (int i = 0; i < list.size(); i++) {

                    BitmapDescriptor bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
                    //if statements for certain colors

                    if (list.get(i).getInt("Index") == 1){
                       bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    }
                    else if (list.get(i).getInt("Index") == 2){
                        bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
                    }
                    else if (list.get(i).getInt("Index") == 3){
                        bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
                    }
                    else if (list.get(i).getInt("Index") == 4){
                        bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);
                    }
                    else if (list.get(i).getInt("Index") == 5){
                        bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    }
                    else if (list.get(i).getInt("Index") == 6){
                        bit = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                    }

                    if (e == null) {

                        LatLng temp = new LatLng(list.get(i).getDouble("Latitude"), list.get(i).getDouble("Longitude"));

                        String ContentString = list.get(i).getString("Description");
                        String TitleString = list.get(i).getString("Activity") + ": " +
                                list.get(i).getString("Start_Time") + " - " + list.get(i).getString("End_Time");

                        map.addMarker(new MarkerOptions()
                                .position(temp)
                                .icon(bit)
                                .title(TitleString)
                                .snippet(ContentString));


                        // Google.maps.event.addListener(marker, 'click', function() {
                        //     infowindow.open(map,marker);
                        // });



                    } else {
                        Log.d("Activity", "Error: " + e.getMessage());
                    }

                }
            }


        });



    }

    /**
     * Calls create activity (which adds PinPal event to database), then creates a new marker.
     *
     * @param map
     * @param clicked_point
     */
    public void createPinPalEvent(GoogleMap map, LatLng clicked_point) {
        Intent nextScreen = new Intent(getApplicationContext(), AddPinFragment.class);
        startActivity(nextScreen);

        /* activity types:
         * 1 = entertainment
         * 2 = fitness
         * 3 = food
         * 4 = social
         * 5 = studying
         * 0 = other
         */

        System.out.println(clicked_point.latitude + " + " + clicked_point.longitude);

        AddPinFragment.setLocation(clicked_point);

        //map.addMarker(options);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;
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
        //String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or, use GPS location data:
        String locationProvider = LocationManager.GPS_PROVIDER;

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);

        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        if (lastKnownLocation == null) {
            // If location error, assume location to be Boston.
            LatLng sanjose = new LatLng(37.3382, -121.8863);
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(sanjose, 13));
        } else {
            // Add a marker in the current location
            LatLng current_loc = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            // Center map (aka "move camera") to current location & zoom in
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(current_loc, 13));
        }


        GoogleMap.OnMapLongClickListener clickListener = new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng clicked_point) {

                Marker temp_marker = map.addMarker(new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked\nhere")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                createPinPalEvent(map, clicked_point);

                temp_marker.remove();
            }
        };
        map.setOnMapLongClickListener(clickListener);

    }

    public static void placePin(MarkerOptions options){
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