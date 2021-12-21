package com.alexpetrov.loftcoin.ui.activity.converter;

import androidx.lifecycle.ViewModelProvider;

import com.alexpetrov.loftcoin.BaseComponent;
import com.alexpetrov.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ConverterModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class ConverterComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract CoinsSheetAdapter coinsSheetAdapter();

}
