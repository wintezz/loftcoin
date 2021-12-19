package com.alexpetrov.loftcoin.util;

import com.alexpetrov.loftcoin.data.Wallet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

@Singleton
public class BalanceFormatter implements Formatter<Wallet> {

    @Inject
    BalanceFormatter() {
    }

    @NonNull
    @Override
    public String format(@NonNull Wallet value) {
        final DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance();
        final DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(value.coin().symbol());
        format.setDecimalFormatSymbols(symbols);
        return format.format(value.balance());
    }

}