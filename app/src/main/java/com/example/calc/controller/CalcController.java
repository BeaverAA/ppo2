package com.example.calc.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.udojava.evalex.Expression;

import java.util.HashSet;
import java.util.Set;

public class CalcController {

    private static final Set<String> mSet = new HashSet<String>(){{
        add("*");
        add("/");
        add("+");
        add(".");
        add("%");
        add("^");
    }};

    private static final Set<String> mFunctions = new HashSet<String>(){{
        add("SIN(");
        add("COS(");
        add("TAN(");
        add("LOG10(");
        add("LN(");
        add("FACT(");
        add("PI");
        add("E");
        add("SQRT(");
    }};

    private boolean isTextError = false;

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

    public void addValue(@NonNull final String value) {
        if (isTextError) {
            setNumber("0");
            isTextError = false;
        }
        final int length = mNumber.length();
        if (length == 1 && mNumber.charAt(0) == '0' && mSet.contains(value) == false) {
            setNumber(value);
        } else if (mSet.contains(value) && mSet.contains(mNumber.substring(length - 1))) {
            mNumber.replace(length - 1, length, value);
        } else {
            mNumber.append(value);
        }
        mUpdateText.accept(mNumber.toString());
    }

    public void calculate() {
        Expression expression = new Expression(mNumber.toString());
        try {
            setNumber(expression.eval().toPlainString());
        } catch (Exception e) {
            setNumber(e.getMessage());
            isTextError = true;
        }
    }

    public void delete() {
        final int length = mNumber.length();
        if (length == 1 || isTextError) {
            setNumber("0");
        } else {
            String stringNumber = mNumber.toString();
            String equalSuffix = null;
            for (String suffix : mFunctions) {
                if (stringNumber.endsWith(suffix)) {
                    equalSuffix = suffix;
                    break;
                }
            }
            if (equalSuffix == null) {
                mNumber.deleteCharAt(length - 1);
            } else {
                mNumber.delete(length - equalSuffix.length(), length);
                if (mNumber.length() == 0) setNumber("0");
            }
        }
        mUpdateText.accept(mNumber.toString());
    }

    public void clear() {
        setNumber("0");
    }
}
