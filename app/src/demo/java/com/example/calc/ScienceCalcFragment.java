package com.example.calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calc.controller.CalcController;
import com.example.calc.fragment.CalcFragment;

public class ScienceCalcFragment extends CalcFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_science_calc, container, false);
    }

    @Override
    public void onClick(View view) {
        // Empty
    }


    @Override
    public void setCalcController(@NonNull CalcController calcListener) {
        // Empty
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
