package com.example.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String NUMBER_KEY = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeCalcMod(View view) {
        final ToggleButton toggleButton = (ToggleButton) view;
        Fragment fragment = getFragment(toggleButton.isChecked());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.calc, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NUMBER_KEY, "123");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String number = savedInstanceState.getString(NUMBER_KEY);
    }

    @NonNull
    private Fragment getFragment(boolean science) {
        if (science) {
            return new ScienceCalcActivity();
        } else {
            return new BaseCalcActivity();
        }
    }

}
