package com.example.calc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.udojava.evalex.Expression;

import java.util.HashSet;
import java.util.Set;

public class CalcManager {

    private static final Set<String> mOperators = new HashSet<String>(){{
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
        add("SQRT(");
    }};

    private boolean isTextError = false;

    private CalcView mCalcView;

    @NonNull
    private StringBuilder mExp = new StringBuilder("0");

    public CalcManager(@NonNull final CalcView calcView) {
        mCalcView = calcView;
        mCalcView.updateExp(mExp.toString());
    }

    @NonNull
    String getNumber() {
        return mExp.toString();
    }

    void setNumber(@Nullable final String number) {
        final String newNumber = number != null ? number : "0";
        mExp = new StringBuilder(newNumber);
        mCalcView.updateExp(mExp.toString());
    }

    void addValue(@NonNull final String value) {
        if (isTextError) {
            setNumber("0");
            isTextError = false;
        }
        final int length = mExp.length();
        if (isStartExp(value)) {
            setNumber(value);
        } else if (isOperator(value)) {
            mExp.replace(length - 1, length, value);
        } else {
            char lastChar = mExp.charAt(mExp.length() - 1);
            if (value.length() > 1 && (Character.isDigit(lastChar) || lastChar == ')')) {
                mExp.append("*");
            }
            mExp.append(value);
        }
        mCalcView.updateExp(mExp.toString());
    }

    private boolean isOperator(String value) {
        final int length = mExp.length();
        return mOperators.contains(value) && mOperators.contains(mExp.substring(length - 1));
    }

    private boolean isStartExp(String value) {
        final int length = mExp.length();
        return length == 1 && mExp.charAt(0) == '0' && mOperators.contains(value) == false;
    }

    void calculate() {
        Expression expression = new Expression(replaceLog());
        try {
            setNumber(expression.eval().toPlainString());
        } catch (Exception e) {
            setNumber(e.getMessage());
            isTextError = true;
        }
    }

    private String replaceLog() {
        String exp = mExp.toString();
        return exp.replaceAll("LN", "LOG");
    }

    void delete() {
        final int length = mExp.length();
        if (length == 1 || isTextError) {
            setNumber("0");
        } else {
            String suffix = getLastOperator();
            if (suffix == null) {
                mExp.deleteCharAt(length - 1);
            } else {
                mExp.delete(length - suffix.length(), length);
                if (mExp.length() == 0) setNumber("0");
            }
        }

        mCalcView.updateExp(mExp.toString());
    }

    String getLastOperator() {
        String stringNumber = mExp.toString();
        String equalSuffix = null;
        for (String suffix : mFunctions) {
            if (stringNumber.endsWith(suffix)) {
                equalSuffix = suffix;
                break;
            }
        }
        return equalSuffix;
    }

    void clear() {
        setNumber("0");
    }
}
