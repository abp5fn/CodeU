package com.codeu.android.pinpals;
import android.app.Application;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import android.util.Log;


/**
 * Created by alisonpeltz on 8/7/15.
 */


public class App extends Application{

    @Override public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "FHM13Idh9QJxvGs8m3HLhng0P3BeQMkiEwyq9u0N", "jN4FAoEowF5xmK5YLdttcaWuxDUnDzXIiydv3l7X");

        //how to create a new pin and push it in to the database
        ParseObject pin1 = new ParseObject("Pins");
        pin1.put("Activity", "Basketball");
        pin1.put("startTime", "4:00 PM");
        pin1.put("endTime", "6:00 PM");
        pin1.put("date", "May 4, 2015");
        //also need Latitude
        //also need Longitude
        pin1.saveInBackground();

        String pinKey = pin1.getObjectId();

        ParseObject pin2 = new ParseObject("Pins");
        pin2.put("Activity", "Hockey");
        pin2.put("startTime", "2:00 PM");
        pin2.put("endTime", "3:00 PM");
        pin2.put("date", "May 4, 2015");
        pin2.saveInBackground();

        //how to go through query of pins and do stuff with them
        //in this case, doing stuff is putting them on the map --> creating new pins
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pins");
        query.whereEqualTo("date", "May 4, 2015");
        //query.whereLessThan("endTime", currentTime??); doesn't pull down pins where the activity is over
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, ParseException e) {

                for (int i = 0; i < list.size(); i++) {

                    if (e == null) {
                        Log.d("Activity: ", list.get(i).getString("Activity"));
                        //create a pin based on activity (helper method?

                    } else {
                        Log.d("Activity", "Error: " + e.getMessage());
                    }

                }
            }


        });




    }



}
