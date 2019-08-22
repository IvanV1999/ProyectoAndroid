package com.example.ivanvelazquez.proyectointent;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;


import static android.content.Context.NOTIFICATION_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {


    public static final String EXTRA_ATRACCION = "EXTRA_ATRACCION";
    public static final String EXTRA_COLOR_FONDO = "EXTRA_COLOR_FONDO";
    public static final String EXTRA_ESPECIE = "EXTRA_ESPECIE";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    public static final String EXTRA_IMAGEN = "EXTRA_IMAGEN";
    public static final String EXTRA_INFO = "EXTRA_INFO";
    public static final String EXTRA_NOMBRE = "EXTRA_NOMBRE";
    public static final String EXTRA_URL = "EXTRA_URL";

    private final int miliseconds = 1000;
    private final String channelId = "notificationChannel";


    @Override
    public void onReceive(Context context, Intent intent) {

        Animal a = createAnimal(intent);

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && v != null) {
            v.vibrate(VibrationEffect.createOneShot(miliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
        } else if (v != null) {
            //deprecated in API 26
            v.vibrate(miliseconds);
        }
        buildNotif(context, a);

    }

    private Animal createAnimal(Intent intent) {

        //Bundle asd = intent.getExtras();
        String nombre = intent.getStringExtra(EXTRA_NOMBRE);
        String info = intent.getStringExtra(EXTRA_INFO);
        String especie = intent.getStringExtra(EXTRA_ESPECIE);
        Atraccion atraccion = (Atraccion) intent.getSerializableExtra(EXTRA_ATRACCION);
        int colorFondo = intent.getIntExtra(EXTRA_COLOR_FONDO, 0);
        int foto = intent.getIntExtra(EXTRA_FOTO, 0);
        int imagen = intent.getIntExtra(EXTRA_IMAGEN, 0);
        String url = intent.getStringExtra(EXTRA_URL);

        return new Animal(nombre, foto, especie, info, imagen, colorFondo, url, atraccion);

    }

    public void buildNotif(Context context, Animal animal) {
        int mNotificationId = 1;
        Intent animalIntent = new Intent(context, InfoActivity.class);
        //agregar el extra de animal
        animalIntent.putExtra("EXTRA_ANIMAL", animal);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 1568, animalIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelId)

                        .setSmallIcon(R.drawable.notificon)
                        .setContentTitle(String.format(context.getString(R.string.showStart), animal.getNombre()))
                        .setContentText(context.getString(R.string.lookbeautiful))
                        .setContentIntent(contentIntent)
                        .setChannelId(channelId);


        NotificationManager notifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notifyMgr.createNotificationChannel(channel);
        }
        notifyMgr.notify(mNotificationId, builder.build());

    }
}
