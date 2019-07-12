package com.example.ivanvelazquez.proyectointent;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class LocaleHelper {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Configuration changeLocale(Resources res, String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());
        Locale localek;
        switch (locale){
            case "es":
                localek = new Locale("es_AR");
                break;
            case "en":
                localek = new Locale("en");
                break;
            default:
                localek = Locale.ENGLISH;

        }
        Locale.setDefault(localek);
        config.setLocale(localek);
        return config;


    }

}
