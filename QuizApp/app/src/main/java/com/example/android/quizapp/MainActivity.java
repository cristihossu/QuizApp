package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* CUSTOM_TOAST is used to display a custom (surprise) Toast message instead of the default one
        set it to false, if you want to see the default one
     */
    private final static boolean CUSTOM_TOAST = true;

    public boolean answer = false;
    String[] quotes = new String[11];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add a Filter to allow only uppercase letters when entering the answer to question 2
        EditText e = (EditText) findViewById(R.id.a2);
        InputFilter[] editFilters = e.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        int length = editFilters.length;
        System.arraycopy(editFilters, 0, newFilters, length, length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        e.setFilters(newFilters);

        //  initially position the cursor after the $ sign for the answer to question 2
        e.setSelection(1);

        // quotes that the Master gives to the user that in subject to test
        quotes[0] = getString(R.string.score0);
        quotes[1] = getString(R.string.score1);
        quotes[2] = getString(R.string.score2);
        quotes[3] = getString(R.string.score3);
        quotes[4] = getString(R.string.score4);
        quotes[5] = getString(R.string.score5);
        quotes[6] = getString(R.string.score6);
        quotes[7] = getString(R.string.score7);
        quotes[8] = getString(R.string.score8);
        quotes[9] = getString(R.string.score9);
        quotes[10] = getString(R.string.score10);


        /* enable the submit score button only after the user has scrolled the application, i.e. has seen the questions */
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

                                                @Override
                                                public void onScrollChanged() {
                                                    Button button = (Button) findViewById(R.id.submitButton);
                                                    button.setEnabled(true);
                                                }
                                            }
                );
    }


    /**
     * @param score shows a Toast with the score result
     *              depending on the CUSTOM_TOAST constant, it shows the custom toast or the default toast
     */
    private void showToast(int score) {
        if (score >= 7) {
            if (CUSTOM_TOAST) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(quotes[score] + "\n" + getString(R.string.pass, score));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            } else {
                Toast.makeText(this, quotes[score] + "\n" + getString(R.string.pass, score), Toast.LENGTH_LONG).show();
            }
        } else {
            if (CUSTOM_TOAST) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(quotes[score] + "\n" + getString(R.string.fail, score));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            } else {
                Toast.makeText(this, quotes[score] + "\n" + getString(R.string.fail, score), Toast.LENGTH_LONG).show();

            }
        }


    }

    /**
     * @param id the id of the RadioButton to be checked whether it is checked or not
     * @return true if the RadioButton is checked or false if it is not checked
     */
    private boolean isRadioButtonChecked(int id) {
        return ((RadioButton) findViewById(id)).isChecked();
    }

    /**
     * @param id the id of the CheckButton to be checked whether it is checked or not
     * @return true if the CheckButton is checked or false if it is not checked
     */
    private boolean isCheckBoxChecked(int id) {
        return ((CheckBox) findViewById(id)).isChecked();
    }

    /**
     * computes the score
     * calls the showToast method with the score as parameter
     *
     * @param v is the View
     */
    public void submitScore(View v) {
        int score = 0;

        if (isRadioButtonChecked(R.id.a1_1)) {
            score++;
        }


        if (((TextView) findViewById(R.id.a2)).getText().toString().equals(getString(R.string.mft))) {
            score++;
        }

        if (!isCheckBoxChecked(R.id.a3_1) && isCheckBoxChecked(R.id.a3_2) &&
                isCheckBoxChecked(R.id.a3_3) && isCheckBoxChecked(R.id.a3_4)) {
            score++;
        }

        if (isCheckBoxChecked(R.id.a4_1) && isCheckBoxChecked(R.id.a4_2) &&
                !isCheckBoxChecked(R.id.a4_3) && !isCheckBoxChecked(R.id.a4_4)) {
            score++;
        }

        if (isRadioButtonChecked(R.id.a5_3)) {
            score++;
        }

        if (isRadioButtonChecked(R.id.a6_2)) {
            score++;
        }

        if (isRadioButtonChecked(R.id.a7_4)) {
            score++;
        }

        if (isRadioButtonChecked(R.id.a8_1)) {
            score++;
        }
        if (isRadioButtonChecked(R.id.a9_3)) {
            score++;
        }

        if (isRadioButtonChecked(R.id.a10_2)) {
            score++;
        }

        showToast(score);
    }
}
