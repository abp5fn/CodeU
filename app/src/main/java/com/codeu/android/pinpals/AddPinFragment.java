package com.codeu.android.pinpals;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RadioButton;

public class AddPinFragment extends FragmentActivity {
    boolean hasCheck;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pin);
    }

    public void showTimePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "TimePickerFragment");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePickerFragment");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        if(!hasCheck) {
            hasCheck=true;
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
    }
}
