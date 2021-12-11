package com.alexpetrov.loftcoin.ui.activity.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.alexpetrov.loftcoin.data.Coin;
import com.alexpetrov.loftcoin.data.CoinsRepo;
import com.alexpetrov.loftcoin.data.CurrencyRepo;
import com.alexpetrov.loftcoin.data.SortBy;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

public class RatesViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    private final MutableLiveData<AtomicBoolean> forceRefresh = new MutableLiveData<>(new AtomicBoolean(false));

    private final MutableLiveData<SortBy> sortBy = new MutableLiveData<>(SortBy.RANK);

    private final LiveData<List<Coin>> coins;

    private int sortingIndex = 1;

    // AppComponent(BaseComponent) -> MainComponent -> Fragment(BaseComponent) -> RatesComponent -> RatesViewModel()

    @Inject
    public RatesViewModel(CoinsRepo coinsRepo, CurrencyRepo currencyRepo) {
        //    t           f            f          f         f          t
        // (f|t) -> forceRefresh -> currency -> sortBy -> query -> listings
        // USD -> RUB -> USD -> USD -> USD -> EUR -> EUR
        // Transformations.distinctUntilChanged()
        final LiveData<CoinsRepo.Query> query = Transformations.switchMap(forceRefresh, (r) -> Transformations.switchMap(currencyRepo.currency(), (c) -> {
            r.set(true);
            isRefreshing.postValue(true);
            return Transformations.map(sortBy, (s) -> CoinsRepo.Query.builder()
                    .currency(c.code())
                    .forceUpdate(r.getAndSet(false))
                    .sortBy(s)
                    .build());
        }));
        final LiveData<List<Coin>> coins = Transformations.switchMap(query, coinsRepo::listings);
        this.coins = Transformations.map(coins, (c) -> {
            isRefreshing.postValue(false);
            return c;
        });
    }

    @NonNull
    LiveData<List<Coin>> coins() {
        return coins;
    }

    @NonNull
    LiveData<Boolean> isRefreshing() {
        return isRefreshing;
    }

    final void refresh() {
        forceRefresh.postValue(new AtomicBoolean(true));
    }

    void switchSortingOrder() {
        sortBy.postValue(SortBy.values()[sortingIndex++ % SortBy.values().length]);
    }

}