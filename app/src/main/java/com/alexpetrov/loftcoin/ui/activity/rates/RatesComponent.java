package com.alexpetrov.loftcoin.ui.activity.rates;
import androidx.lifecycle.ViewModelProvider;


import com.alexpetrov.loftcoin.BaseComponent;
import com.alexpetrov.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        RatesModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class RatesComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract RatesAdapter ratesAdapter();

}