package com.example.bills2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by delaroy on 1/8/18.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
