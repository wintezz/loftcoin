package com.alexpetrov.loftcoin;

import android.content.Context;

import com.alexpetrov.loftcoin.data.CoinsRepo;
import com.alexpetrov.loftcoin.data.CurrencyRepo;
import com.alexpetrov.loftcoin.util.ImageLoader;


public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    ImageLoader imageLoader();
}