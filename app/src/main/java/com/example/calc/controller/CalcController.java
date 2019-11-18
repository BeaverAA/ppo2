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
    private StringBuilder mExp = new StringBuilder("0");

    public CalcController(@NonNull final Consumer<String> updateText) {
        mUpdateText = updateText;
        updateText.accept(mExp.toString());
    }

    @NonNull
    public String getNumber() {
        return mExp.toString();
    }

    public void setNumber(@Nullable final String number) {
        final String newNumber = number != null ? number : "0";
        mExp = new StringBuilder(newNumber);
        mUpdateText.accept(mExp.toString());
    }

    public void addValue(@NonNull final String value) {
        if (isTextError) {
            setNumber("0");
            isTextError = false;
        }
        final int length = mExp.length();
        if (length == 1 && mExp.charAt(0) == '0' && mSet.contains(value) == false) {
            setNumber(value);
        } else if (mSet.contains(value) && mSet.contains(mExp.substring(length - 1))) {
            mExp.replace(length - 1, length, value);
        } else {
            Character lastChar = mExp.charAt(mExp.length() - 1);
            if (value.length() > 1 && (Character.isDigit(lastChar) || lastChar == ')')) {
                mExp.append("*");
            }
            mExp.append(value);
        }
        mUpdateText.accept(mExp.toString());
    }

    public void calculate() {
        Expression expression = new Expression(getNormalizedExp());
        try {
            setNumber(expression.eval().toPlainString());
        } catch (Exception e) {
            setNumber(e.getMessage());
            isTextError = true;
        }
    }

    private String getNormalizedExp() {
        String exp = mExp.toString();
        return exp.replaceAll("LN", "LOG");
    }

    public void delete() {
        final int length = mExp.length();
        if (length == 1 || isTextError) {
            setNumber("0");
        } else {
            String stringNumber = mExp.toString();
            String equalSuffix = null;
            for (String suffix : mFunctions) {
                if (stringNumber.endsWith(suffix)) {
                    equalSuffix = suffix;
                    break;
                }
            }
            if (equalSuffix == null) {
                mExp.deleteCharAt(length - 1);
            } else {
                mExp.delete(length - equalSuffix.length(), length);
                if (mExp.length() == 0) setNumber("0");
            }
        }
        mUpdateText.accept(mExp.toString());
    }

    public void clear() {
        setNumber("0");
    }
}
