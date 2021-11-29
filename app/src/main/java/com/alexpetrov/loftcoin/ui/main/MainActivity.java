package com.alexpetrov.loftcoin.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

import com.alexpetrov.loftcoin.R;
import com.alexpetrov.loftcoin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());

        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        NavigationUi.setNavController(binding.bottomNav, navController);
        NavigationUi.setNavController(binding.toolbar, navController, new AppBarConfiguration
                .Builder(binding.bottomNav.getMenu())
                .build());

    }
}