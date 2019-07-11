package com.example.ivanvelazquez.proyectointent;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocaleHelper {

    public static void changeLocale(Resources res,String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale){
            case "es":
                config.locale=new Locale("es");
                break;
            case "en":
                config.locale=new Locale("en");
            default:
                config.locale = Locale.ENGLISH;

        }
        res.updateConfiguration(config,res.getDisplayMetrics());

    }
}
