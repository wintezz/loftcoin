package com.alexpetrov.loftcoin.data;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
abstract class Listings {

    abstract List<Coin> data();
}
