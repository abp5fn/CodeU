package com.codeu.android.pinpals;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
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
}
