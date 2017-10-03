package com.example.home.healthcare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle("Alarme ativado!")
                .setContentText("Hora de verificar sua pressão!")
                .setContentIntent(resultPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

       /* PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        wl.acquire(3000);
        wl.release();*/
    }
}
