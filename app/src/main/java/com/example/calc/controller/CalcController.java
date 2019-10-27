package com.example.calc.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import java.util.HashSet;
import java.util.Set;

public class CalcController {

    private static final Set<String> mSet = new HashSet<String>(){{
        add("×");
        add("÷");
        add("+");
        add(",");
    }};

    @NonNull
    private Consumer<String> mUpdateText;

    @NonNull
    private StringBuilder mNumber;

    public CalcController(@NonNull final Consumer<String> updateText) {
        mUpdateText = updateText;
        setNumber("0");
    }

    @NonNull
    public String getNumber() {
        return mNumber.toString();
    }

    public void setNumber(@Nullable final String number) {
        final String newNumber = number != null ? number : "0";
        mNumber = new StringBuilder(newNumber);
        mUpdateText.accept(mNumber.toString());
    }

    public void addValue(@Nullable final String value) {
        final int length = mNumber.length();
        if (length == 1 && mNumber.charAt(0) == '0' && mSet.contains(value) == false) {
            setNumber(value);
        } else {
            mNumber.append(value);
        }
        mUpdateText.accept(mNumber.toString());
    }

    public void delete() {
        final int length = mNumber.length();
        if (length == 1 ) {
            setNumber("0");
        } else {
            mNumber.deleteCharAt(length - 1);
        }
        mUpdateText.accept(mNumber.toString());
    }

    public void clear() {
        setNumber("0");
    }
}
