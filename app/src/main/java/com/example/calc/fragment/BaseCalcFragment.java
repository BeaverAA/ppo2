package com.example.calc.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calc.CalcListener;
import com.example.calc.R;

public class BaseCalcFragment extends CalcFragment implements View.OnLongClickListener {

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

        if (mCalcListener == null) return;

        switch (view.getId()) {
            case R.id.one:
                mCalcListener.addValue("1");
                break;
            case R.id.two:
                mCalcListener.addValue("2");
                break;
            case R.id.three:
                mCalcListener.addValue("3");
                break;
            case R.id.four:
                mCalcListener.addValue("4");
                break;
            case R.id.five:
                mCalcListener.addValue("5");
                break;
            case R.id.six:
                mCalcListener.addValue("6");
                break;
            case R.id.seven:
                mCalcListener.addValue("7");
                break;
            case R.id.eight:
                mCalcListener.addValue("8");
                break;
            case R.id.nine:
                mCalcListener.addValue("9");
                break;
            case R.id.zero:
                mCalcListener.addValue("0");
                break;
            case R.id.point:
                mCalcListener.addValue(",");
                break;
            case R.id.mult:
                mCalcListener.addValue("×");
                break;
            case R.id.div:
                mCalcListener.addValue("÷");
                break;
            case R.id.plus:
                mCalcListener.addValue("+");
                break;
            case R.id.minus:
                mCalcListener.addValue("-");
                break;
            case R.id.result:
                break;
            case R.id.delete:
                mCalcListener.delete();
                break;
        }

    }

    @Override
    public void setCalcListener(@NonNull final CalcListener calcListener) {
        mCalcListener = calcListener;
    }

    @Override
    public boolean onLongClick(View v) {

        if (mCalcListener == null) return false;

        if (v.getId() == R.id.delete){
            mCalcListener.clear();
            return true;
        } else {
            return false;
        }
    }
}

