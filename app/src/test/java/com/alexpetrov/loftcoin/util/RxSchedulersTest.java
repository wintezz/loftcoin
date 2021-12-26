package com.alexpetrov.loftcoin.util;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersTest implements RxSchedulers {

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler cmp() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler main() {
        return Schedulers.trampoline();
    }
}