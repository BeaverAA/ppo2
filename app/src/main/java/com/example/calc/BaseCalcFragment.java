package com.example.calc;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseCalcFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private CalcManager mCalcManager;

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

        final Activity activity = getActivity();
        if (activity instanceof CalcView) {
            mCalcManager = ((CalcView) activity).getManager();
        }

        return view;
    }

    @Override
    public void onClick(View view) {

        if (mCalcManager == null) return;

        switch (view.getId()) {
            case R.id.one:
                mCalcManager.addValue("1");
                break;
            case R.id.two:
                mCalcManager.addValue("2");
                break;
            case R.id.three:
                mCalcManager.addValue("3");
                break;
            case R.id.four:
                mCalcManager.addValue("4");
                break;
            case R.id.five:
                mCalcManager.addValue("5");
                break;
            case R.id.six:
                mCalcManager.addValue("6");
                break;
            case R.id.seven:
                mCalcManager.addValue("7");
                break;
            case R.id.eight:
                mCalcManager.addValue("8");
                break;
            case R.id.nine:
                mCalcManager.addValue("9");
                break;
            case R.id.zero:
                mCalcManager.addValue("0");
                break;
            case R.id.point:
                mCalcManager.addValue(".");
                break;
            case R.id.mult:
                mCalcManager.addValue("*");
                break;
            case R.id.div:
                mCalcManager.addValue("/");
                break;
            case R.id.plus:
                mCalcManager.addValue("+");
                break;
            case R.id.minus:
                mCalcManager.addValue("-");
                break;
            case R.id.result:
                mCalcManager.calculate();
                break;
            case R.id.delete:
                mCalcManager.delete();
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {

        if (mCalcManager == null) return false;

        if (v.getId() == R.id.delete){
            mCalcManager.clear();
            return true;
        } else {
            return false;
        }
    }

}

