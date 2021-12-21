package com.alexpetrov.loftcoin.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.alexpetrov.loftcoin.ui.activity.converter.CoinsSheet;
import com.alexpetrov.loftcoin.ui.activity.converter.ConverterFragment;
import com.alexpetrov.loftcoin.ui.activity.rates.RatesFragment;
import com.alexpetrov.loftcoin.ui.currency.CurrencyDialog;
import com.alexpetrov.loftcoin.ui.wallets.WalletsFragment;
import com.alexpetrov.loftcoin.util.LoftFragmentFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;


@Module
abstract class MainModule {

    @Binds
    abstract FragmentFactory fragmentFactory(LoftFragmentFactory impl);

    @Binds
    @IntoMap
    @ClassKey(RatesFragment.class)
    abstract Fragment ratesFragment(RatesFragment impl);

    @Binds
    @IntoMap
    @ClassKey(WalletsFragment.class)
    abstract Fragment walletsFragment(WalletsFragment impl);

    @Binds
    @IntoMap
    @ClassKey(ConverterFragment.class)
    abstract Fragment converterFragment(ConverterFragment impl);

    @Binds
    @IntoMap
    @ClassKey(CurrencyDialog.class)
    abstract Fragment currencyDialog(CurrencyDialog impl);

    @Binds
    @IntoMap
    @ClassKey(CoinsSheet.class)
    abstract Fragment coinsSheet(CoinsSheet impl);

}