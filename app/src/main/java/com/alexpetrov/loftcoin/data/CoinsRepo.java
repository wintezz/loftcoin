package com.alexpetrov.loftcoin.data;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.google.auto.value.AutoValue;

import java.io.IOException;
import java.util.List;

public interface CoinsRepo {
    @NonNull
    @WorkerThread
    List<? extends Coin> listings(@NonNull String currency) throws IOException;
}
