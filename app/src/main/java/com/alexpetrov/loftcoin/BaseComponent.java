package com.alexpetrov.loftcoin;

import android.content.Context;

import com.alexpetrov.loftcoin.data.CoinsRepo;
import com.alexpetrov.loftcoin.data.CurrencyRepo;
import com.alexpetrov.loftcoin.data.WalletsRepo;
import com.alexpetrov.loftcoin.util.ImageLoader;
import com.alexpetrov.loftcoin.util.Notifier;
import com.alexpetrov.loftcoin.util.RxSchedulers;



public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    ImageLoader imageLoader();
    RxSchedulers schedulers();
    WalletsRepo walletsRepo();
    Notifier notifier();
}