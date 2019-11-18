package com.example.calc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calc.controller.CalcController;
import com.example.calc.fragment.CalcFragment;

public class ScienceCalcFragment extends CalcFragment {

    @Nullable
    private Button logButton;

    @Nullable
    private AlertDialog alertDialog;

    @NonNull
    private static String selectedLog = "LN";

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
        view.findViewById(R.id.tan).setOnClickListener(this);
        view.findViewById(R.id.sqrt).setOnClickListener(this);
        view.findViewById(R.id.pi).setOnClickListener(this);
        view.findViewById(R.id.e).setOnClickListener(this);
        view.findViewById(R.id.pow).setOnClickListener(this);

        logButton = view.findViewById(R.id.log);

        logButton.setText(selectedLog);
        logButton.setOnClickListener(this);
        logButton.setOnLongClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] logTypes = new String[] {"LOG10", "LN"};
        alertDialog = builder.setTitle("Select log type")
                        .setItems(logTypes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedLog = logTypes[which];
                                logButton.setText(selectedLog);
                            }
                        }).create();

        return view;
    }

    @Override
    public void onClick(View view) {

        if (mCalcController == null) return;

        switch (view.getId()) {
            case R.id.mod:
                mCalcController.addValue("%");
                break;
            case R.id.fact:
                mCalcController.addValue("FACT(");
                break;
            case R.id.op:
                mCalcController.addValue("(");
                break;
            case R.id.cb:
                mCalcController.addValue(")");
                break;
            case R.id.sin:
                mCalcController.addValue("SIN(");
                break;
            case R.id.cos:
                mCalcController.addValue("COS(");
                break;
            case R.id.tan:
                mCalcController.addValue("TAN(");
                break;
            case R.id.log:
                mCalcController.addValue(selectedLog + "(");
                break;
            case R.id.sqrt:
                mCalcController.addValue("SQRT(");
                break;
            case R.id.pi:
                mCalcController.addValue("PI");
                break;
            case R.id.e:
                mCalcController.addValue("E");
                break;
            case R.id.pow:
                mCalcController.addValue("^");
                break;
        }
    }

    @Override
    public void setCalcController(@NonNull final CalcController calcListener) {
        mCalcController = calcListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.log && alertDialog != null) {
            alertDialog.show();
            return true;
        }
        return false;
    }

}
