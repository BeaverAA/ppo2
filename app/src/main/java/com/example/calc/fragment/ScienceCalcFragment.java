package com.example.calc.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calc.CalcListener;
import com.example.calc.R;

public class ScienceCalcFragment extends CalcFragment {

    @Nullable
    private CalcListener mCalcListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_science_calc, container, false);
        view.findViewById(R.id.mod).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if (mCalcListener == null) return;

        switch (view.getId()) {
            case R.id.mod:
                mCalcListener.addValue("%");
                break;
        }

    }

    @Override
    public void setCalcListener(@NonNull final CalcListener calcListener) {
        mCalcListener = calcListener;
    }
}
