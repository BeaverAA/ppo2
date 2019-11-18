package com.example.calc.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.calc.R;
import com.example.calc.fragment.BaseCalcFragment;
import com.example.calc.fragment.CalcFragment;
import com.example.calc.controller.CalcController;
import com.example.calc.ScienceCalcFragment;

public class MainActivity extends FragmentActivity {

    private static final String NUMBER_KEY = "number";

    private TextView mTextView;
    private CalcController mCalcController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextView = findViewById(R.id.textView);

        Consumer<String> updateText = new Consumer<String>() {
            @Override
            public void accept(String s) {
                mTextView.setText(s);
            }
        };

        mCalcController = new CalcController(updateText);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            final BaseCalcFragment baseCalcFragment = new BaseCalcFragment();
            final ScienceCalcFragment scienceCalcFragment = new ScienceCalcFragment();
            baseCalcFragment.setCalcController(mCalcController);
            scienceCalcFragment.setCalcController(mCalcController);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.baseCalc, baseCalcFragment)
                    .replace(R.id.scienceCalc, scienceCalcFragment)
                    .commit();
        } else {
            changeMode(findViewById(R.id.toggleButton));
        }

    }

    public void tapChangeMode(View view) {
        changeMode(view);
    }

    private void changeMode(@Nullable final View view) {
        if (view == null) return;
        final ToggleButton toggleButton = (ToggleButton) view;
        CalcFragment fragment = getFragment(toggleButton.isChecked());
        fragment.setCalcController(mCalcController);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.calc, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NUMBER_KEY, mCalcController.getNumber());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String number = savedInstanceState.getString(NUMBER_KEY);
        mCalcController.setNumber(number);
        setText(mCalcController.getNumber());
    }

    private void setText(String text){
        mTextView.setText(text);
    }

    @NonNull
    private CalcFragment getFragment(boolean science) {
        if (science) {
            return new ScienceCalcFragment();
        } else {
            return new BaseCalcFragment();
        }
    }

}
