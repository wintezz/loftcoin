package com.alexpetrov.loftcoin.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.alexpetrov.loftcoin.BaseComponent;
import com.alexpetrov.loftcoin.LoftApp;
import com.alexpetrov.loftcoin.R;
import com.alexpetrov.loftcoin.ui.main.MainActivity;
import com.alexpetrov.loftcoin.util.Notifier;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

public class FcmService extends FirebaseMessagingService {

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    Notifier notifier;

    @Override
    public void onCreate() {
        super.onCreate();
        final BaseComponent baseComponent = ((LoftApp) getApplication()).getComponent();
        DaggerFcmComponent.builder().baseComponent(baseComponent).build().inject(this);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        final RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
            disposable.add(notifier.sendMessage(
                    Objects.toString(notification.getTitle(), getString(R.string.app_name)),
                    Objects.toString(notification.getBody(), "Somethings wrong!"),
                    MainActivity.class
            ).subscribe());
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}