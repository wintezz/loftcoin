package com.alexpetrov.loftcoin.ui.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.alexpetrov.loftcoin.R;
import com.alexpetrov.loftcoin.ui.main.MainActivity;
import com.alexpetrov.loftcoin.ui.welcome.WelcomeActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    private Runnable goNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)){
            goNext = () -> startActivity(new Intent(this, WelcomeActivity.class));
        } else {
            goNext = () -> startActivity(new Intent(this, MainActivity.class));
        }
        handler.postDelayed(goNext, 1500);
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(goNext);
        super.onStop();
    }
}