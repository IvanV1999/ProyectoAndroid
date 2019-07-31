package com.example.ivanvelazquez.proyectointent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;


public class AlarmReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(50000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(5000);
        }


    }
}
