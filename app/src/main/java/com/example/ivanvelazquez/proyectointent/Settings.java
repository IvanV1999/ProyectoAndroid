package com.example.ivanvelazquez.proyectointent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    private Button applyButton;
    private Button backButton;
    private Button logOutButton;
    private TextView languageTv;
    private RadioGroup languageRb;
    private RadioButton esRb;
    private RadioButton enRb;
    private TextView settingsTv;
    private String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        applyButton=findViewById(R.id.applyButton);
        backButton=findViewById(R.id.backButton);
        logOutButton=findViewById(R.id.logOutButton);
        languageTv = findViewById(R.id.tVLanguages);
        languageRb=findViewById(R.id.rBLanguages);
        esRb=findViewById(R.id.rBEs);
        enRb=findViewById(R.id.rBEn);
        settingsTv=findViewById(R.id.TvSettings);

        settingsTv.setText(R.string.Settings);
        languageTv.setText(R.string.language);
        logOutButton.setText(R.string.logout);
        applyButton.setText(R.string.apply);
        backButton.setText(R.string.back);


        esRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idioma = "es";
            }
        });
        enRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idioma = "en";
            }
        });


        /// TODO: 02/07/19 terminar el onCreate.
    }
    public void logOut(View view){
        Intent  restart = new Intent(this,LoginZoo.class);
        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        restart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(restart);
        finish();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void apply(View view){
        //getApplicationContext().createConfigurationContext(LocaleHelper.changeLocale(this.getResources(),"es"));
        //Locale l = Locale.getDefault();
        attachBaseContext(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Locale("es_AR");

        Context context = ContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }

    public void back(View view){
        finish();
    }

}
//terminar