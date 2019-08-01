package com.example.ivanvelazquez.proyectointent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;


public class AlarmReceiver extends BroadcastReceiver {

    private final int miliseconds = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && v != null) {
            v.vibrate(VibrationEffect.createOneShot(miliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
        } else if (v != null) {
            //deprecated in API 26
            v.vibrate(miliseconds);
        }


    }
}
