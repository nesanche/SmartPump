package com.smartpump.bismara.app.medic.notifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.ui.activities.mainactivity.MainActivity;

public class GCMMessageHandler extends IntentService {

    public GCMMessageHandler() {
        super("defaultName");
    }

    public GCMMessageHandler(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        Log.i("GCM",
                "Received : (" + messageType + ")  "
                        + extras.getString("message"));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this)
                .setSmallIcon(R.drawable.ic_stat_bismara)
                .setContentTitle(extras.getString("header"))
                .setContentText(extras.getString("message"))
                .setContentIntent(
                        getNotificationIntent(Integer.parseInt(extras
                                .getString("type"))))
                .setAutoCancel(true)
                .setVibrate(new long[] { 0, 300, 100, 300 })
                .setLights(Color.BLUE, 3000, 3000)
                .setSound(
                        Uri.parse("android.resource://com.smartpump.bismara.app.medic/"
                                + R.raw.bismara_ringtone));
        NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifManager.notify(1, builder.build());
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private PendingIntent getNotificationIntent(int notificationType) {
        Intent resultIntent = null;
        switch (notificationType) {
        case NotificationTypeConstants.TYPE_CONFIRMED_ACCOUNT:
            resultIntent = new Intent(this, MainActivity.class);
            break;
        case NotificationTypeConstants.TYPE_NEW_SCHEDULE:
            resultIntent = new Intent(this, MainActivity.class);
            break;
        default:
            resultIntent = new Intent(this, MainActivity.class);
            break;
        }
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }
}
