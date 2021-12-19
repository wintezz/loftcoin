package com.alexpetrov.loftcoin.data;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface WalletsRepo {

    @NonNull
    Observable<List<Wallet>> wallets(@NonNull Currency currency);

    @NonNull
    Observable<List<Transaction>> transactions(@NonNull Wallet wallet);


    @NonNull
    Completable addWallet(@NonNull Currency currency, List<Integer> takenIds);

}