package com.alexpetrov.loftcoin.data;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Currency {

    @NonNull
    static Currency create(String symbol, String code, String title) {
        return null;
    }

    public abstract String symbol();

    public abstract String code();

    public abstract String title();
}
