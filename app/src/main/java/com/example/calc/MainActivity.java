package com.example.calc;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FragmentActivity implements CalcView {

    private static final String NUMBER_KEY = "number";

    private TextView mTextView;
    private CalcManager mCalcManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mCalcManager = new CalcManager(this);

        if (savedInstanceState != null) {
            mCalcManager.setNumber(savedInstanceState.getString(NUMBER_KEY));
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            final BaseCalcFragment baseCalcFragment = new BaseCalcFragment();
            final ScienceCalcFragment scienceCalcFragment = new ScienceCalcFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.baseCalc, baseCalcFragment)
                    .replace(R.id.scienceCalc, scienceCalcFragment)
                    .commit();
        } else {
            final ViewPager viewPager = findViewById(R.id.viewPager);
            final BaseCalcFragment baseCalcFragment = new BaseCalcFragment();
            final ScienceCalcFragment scienceCalcFragment = new ScienceCalcFragment();
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 0) {

                @NonNull
                @Override
                public Fragment getItem(int position) {
                    if (position == 0) {
                        return baseCalcFragment;
                    } else {
                        return scienceCalcFragment;
                    }
                }

                @Override
                public int getCount() {
                    return 2;
                }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NUMBER_KEY, mCalcManager.getNumber());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateExp(String exp) {
        mTextView.setText(exp);
    }

    @Override
    public CalcManager getManager() {
        return mCalcManager;
    }
}
