package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements BaseCalcFunc.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeCalcMod(View view) {
        final ToggleButton toggleButton = (ToggleButton) view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
