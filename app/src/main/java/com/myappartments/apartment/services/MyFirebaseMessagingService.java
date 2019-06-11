package com.myappartments.apartment.services;

import android.app.Service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        CustomLog.d(Constant.TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(token);
    }

}
