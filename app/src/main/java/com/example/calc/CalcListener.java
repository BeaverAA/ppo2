package com.example.calc;

import androidx.annotation.NonNull;

public interface CalcListener {

    void addValue(@NonNull final String value);

    void delete();

    void clear();

}
