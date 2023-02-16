package com.capacitorjs.plugins.pushnotifications;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.getcapacitor.JSObject;

public class LocalNotificationHandlerService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();

        JSObject notificationJson = new JSObject();
        notificationJson.put("id", extras.get("id"));

        JSObject dataObject = new JSObject();
        Bundle dataBundle = extras.getBundle("data");

        for (String key : dataBundle.keySet()) {
            Object value = dataBundle.get(key);
            String valueStr = (value != null) ? value.toString() : null;
            dataObject.put(key, valueStr);
        }
        notificationJson.put("data", dataObject);

        PushNotificationsPlugin.getPushNotificationsInstance().sendPushNotificationPerformed(notificationJson);

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // No binding available
        return null;
    }
}
