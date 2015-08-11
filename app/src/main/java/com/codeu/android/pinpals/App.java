package com.codeu.android.pinpals;
import android.app.Application;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * Created by alisonpeltz on 8/7/15.
 */


public class App extends Application{

    @Override public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "FHM13Idh9QJxvGs8m3HLhng0P3BeQMkiEwyq9u0N", "jN4FAoEowF5xmK5YLdttcaWuxDUnDzXIiydv3l7X");





    }



}