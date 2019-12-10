package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ScienceCalcFragment extends Fragment implements View.OnClickListener {

    private CalcManager mCalcManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_science_calc, container, false);
        view.findViewById(R.id.mod).setOnClickListener(this);
        view.findViewById(R.id.fact).setOnClickListener(this);
        view.findViewById(R.id.op).setOnClickListener(this);
        view.findViewById(R.id.cb).setOnClickListener(this);
        view.findViewById(R.id.sin).setOnClickListener(this);
        view.findViewById(R.id.cos).setOnClickListener(this);
        view.findViewById(R.id.sqrt).setOnClickListener(this);
        view.findViewById(R.id.pi).setOnClickListener(this);
        view.findViewById(R.id.e).setOnClickListener(this);
        view.findViewById(R.id.pow).setOnClickListener(this);
        view.findViewById(R.id.log).setOnClickListener(this);
        view.findViewById(R.id.log10).setOnClickListener(this);

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
            case R.id.mod:
                mCalcManager.addValue("%");
                break;
            case R.id.fact:
                mCalcManager.addValue("FACT(");
                break;
            case R.id.op:
                mCalcManager.addValue("(");
                break;
            case R.id.cb:
                mCalcManager.addValue(")");
                break;
            case R.id.sin:
                mCalcManager.addValue("SIN(");
                break;
            case R.id.cos:
                mCalcManager.addValue("COS(");
                break;
            case R.id.log:
                mCalcManager.addValue("LN(");
                break;
            case R.id.log10:
                mCalcManager.addValue("LOG10(");
                break;
            case R.id.sqrt:
                mCalcManager.addValue("SQRT(");
                break;
            case R.id.pi:
                mCalcManager.addValue("PI");
                break;
            case R.id.e:
                mCalcManager.addValue("E");
                break;
            case R.id.pow:
                mCalcManager.addValue("^");
                break;
        }
    }

}
