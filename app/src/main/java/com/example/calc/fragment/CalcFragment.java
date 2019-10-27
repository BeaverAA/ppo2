package com.example.calc.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calc.CalcListener;

public abstract class CalcFragment extends Fragment implements View.OnClickListener {

    @Nullable
    CalcListener mCalcListener;

    public abstract void setCalcListener(@NonNull final CalcListener calcListener);

}
