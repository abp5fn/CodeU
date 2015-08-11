package com.codeu.android.pinpals;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            case 2:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                break;
            case 3:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                break;
            case 4:
                options = new MarkerOptions()
                        .position(clicked_point)
                        .title(editTextEventName.getText().toString())
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

        MainActivity.placePin(options);
        finish();
    }

    public void addPinFragCancel(View v){
      //  MainActivity.removeMarker(clicked_point);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()) {
                case R.id.radio_entertainment:
                    if (checked)
                        // Pirates are the best
                        break;
                case R.id.radio_fitness:
                    if (checked)
                        // Ninjas rule
                        break;
                case R.id.radio_food:
                    if (checked)
                        // Ninjas rule
                        break;
                case R.id.radio_social:
                    if (checked)
                        // Ninjas rule
                        break;
                case R.id.radio_studying:
                    if (checked)
                        // Ninjas rule
                        break;
                case R.id.radio_other:
                    if (checked)
                        // Ninjas rule
                        break;
        }
    }

    public static void setLocation(LatLng cp){
        clicked_point = cp;
    }
}
