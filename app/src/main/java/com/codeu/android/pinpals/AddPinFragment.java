package com.codeu.android.pinpals;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseObject;

public class AddPinFragment extends FragmentActivity {
    static LatLng clicked_point;
    int activity_type = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pin);
    }

    public void showTimePickerDialogStart(View v) {
        EditText editText = (EditText)findViewById(R.id.editText_Start_Time);
        android.support.v4.app.DialogFragment newFragment = new TimePickerFragment(editText);
        newFragment.show(getSupportFragmentManager(), "TimePickerFragment");
    }

    public void showTimePickerDialogEnd(View v) {
        EditText editText = (EditText)findViewById(R.id.editText_End_Time);
        android.support.v4.app.DialogFragment newFragment = new TimePickerFragment(editText);
        newFragment.show(getSupportFragmentManager(), "TimePickerFragment");
    }

    public void showDatePickerDialog(View v) {
        EditText editText = (EditText)findViewById(R.id.editText_Date);
        DialogFragment newFragment = new DatePickerFragment(editText);
        newFragment.show(getSupportFragmentManager(), "DatePickerFragment");
    }

    public void addPinFragDone(View v){
        EditText editTextEventName, editTextDescription, editTextStartTime, editTextEndTime, editTextDate;

        /* Gets the text for Event Name*/
        editTextEventName   = (EditText)findViewById(R.id.editText_Event_Name);
         /* Gets the text for Description*/
        editTextDescription   = (EditText)findViewById(R.id.editText_Description);
         /* Gets the text for Start Time*/
        editTextStartTime   = (EditText)findViewById(R.id.editText_Start_Time);
         /* Gets the text for End Time*/
        editTextEndTime   = (EditText)findViewById(R.id.editText_End_Time);
         /* Gets the text for Date*/
        editTextDate   = (EditText)findViewById(R.id.editText_Date);



        ParseObject parseObject = new ParseObject("Pins");
        parseObject.put("Activity", editTextEventName.getText().toString());
        parseObject.put("Start_Time", editTextStartTime.getText().toString());
        parseObject.put("End_Time", editTextEndTime.getText().toString());
        parseObject.put("Date", editTextDate.getText().toString());

        parseObject.put("Longitude", clicked_point.longitude );
        parseObject.put("Latitude", clicked_point.latitude);

        parseObject.put("Description", editTextDescription.getText().toString());
        parseObject.put("Type", activity_type);
        parseObject.saveInBackground();


        MarkerOptions options;
        switch (activity_type) {
            case 1:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .snippet(editTextDescription.getText().toString());
                break;
            case 2:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                        .snippet(editTextDescription.getText().toString());
                break;
            case 3:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .snippet(editTextDescription.getText().toString());

                break;
            case 4:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                        .snippet(editTextDescription.getText().toString());
                break;
            case 5:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .snippet(editTextDescription.getText().toString());
                break;
            default:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title("Clicked here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .snippet(editTextDescription.getText().toString());
        } // end switch

        MainActivity.placePin(options);
        finish();
    }

    public void addPinFragCancel(View v){
      //  MainActivity.removeMarker(clicked_point);
        finish();
    }

    public int onActivityTypeClicked(View v) {
        boolean pressed = ((Button) v).isPressed();

        int[] type_buttons = { R.id.button_other, R.id.button_games, R.id.button_fitness,
                R.id.button_food, R.id.button_social, R.id.button_studying };

        String[] default_colors = { "#ffaa33", "#00ee99", "#33eeff", "#3366ff", "#7733ee", "#dd0033" };
        String[] selected_colors = { "#ff9933", "#00dd99", "#33ccff", "#3333ff", "#6600dd", "#bb0033" };

        // i keeps track of which button is pressed, is passed back to main_activity
        int i = 0;
        for (; i < type_buttons.length; i++) {

            if (v.getId() == type_buttons[i]) {
                // this is the button that was clicked
                v.setBackgroundColor(Color.parseColor(selected_colors[i]));

            } else {
                // these are the buttons that weren't clicked.
                Button temp_button = (Button) findViewById(type_buttons[i]);
                temp_button.setBackgroundColor(Color.parseColor(default_colors[i]));
            }
        }

        return i;
    }

    public static void setLocation(LatLng cp){
        clicked_point = cp;
    }
}
