package com.example.calc.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calc.R;
import com.example.calc.controller.CalcController;

public class BaseCalcFragment extends CalcFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_base_calc, container, false);
        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        view.findViewById(R.id.three).setOnClickListener(this);
        view.findViewById(R.id.four).setOnClickListener(this);
        view.findViewById(R.id.five).setOnClickListener(this);
        view.findViewById(R.id.six).setOnClickListener(this);
        view.findViewById(R.id.seven).setOnClickListener(this);
        view.findViewById(R.id.eight).setOnClickListener(this);
        view.findViewById(R.id.nine).setOnClickListener(this);
        view.findViewById(R.id.zero).setOnClickListener(this);
        view.findViewById(R.id.point).setOnClickListener(this);
        view.findViewById(R.id.mult).setOnClickListener(this);
        view.findViewById(R.id.div).setOnClickListener(this);
        view.findViewById(R.id.plus).setOnClickListener(this);
        view.findViewById(R.id.minus).setOnClickListener(this);
        view.findViewById(R.id.result).setOnClickListener(this);

        view.findViewById(R.id.delete).setOnClickListener(this);
        view.findViewById(R.id.delete).setOnLongClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if (mCalcController == null) return;

        switch (view.getId()) {
            case R.id.one:
                mCalcController.addValue("1");
                break;
            case R.id.two:
                mCalcController.addValue("2");
                break;
            case R.id.three:
                mCalcController.addValue("3");
                break;
            case R.id.four:
                mCalcController.addValue("4");
                break;
            case R.id.five:
                mCalcController.addValue("5");
                break;
            case R.id.six:
                mCalcController.addValue("6");
                break;
            case R.id.seven:
                mCalcController.addValue("7");
                break;
            case R.id.eight:
                mCalcController.addValue("8");
                break;
            case R.id.nine:
                mCalcController.addValue("9");
                break;
            case R.id.zero:
                mCalcController.addValue("0");
                break;
            case R.id.point:
                mCalcController.addValue(".");
                break;
            case R.id.mult:
                mCalcController.addValue("*");
                break;
            case R.id.div:
                mCalcController.addValue("/");
                break;
            case R.id.plus:
                mCalcController.addValue("+");
                break;
            case R.id.minus:
                mCalcController.addValue("-");
                break;
            case R.id.result:
                mCalcController.calculate();
                break;
            case R.id.delete:
                mCalcController.delete();
                break;
        }

    }

    @Override
    public void setCalcController(@NonNull final CalcController calcListener) {
        mCalcController = calcListener;
    }

    @Override
    public boolean onLongClick(View v) {

        if (mCalcController == null) return false;

        if (v.getId() == R.id.delete){
            mCalcController.clear();
            return true;
        } else {
            return false;
        }
    }
}

