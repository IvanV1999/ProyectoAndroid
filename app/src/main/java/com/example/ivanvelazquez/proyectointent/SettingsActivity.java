package com.example.ivanvelazquez.proyectointent;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends ButterBind {


    @BindView(R.id.applyButton)
    Button applyButton;

    @BindView(R.id.backButton)
    Button backButton;

    @BindView(R.id.logOutButton)
    Button logOutButton;

    @BindView(R.id.tVLanguages)
    TextView languageTv;

    @BindView(R.id.rBLanguages)
    RadioGroup languageRb;

    @BindView(R.id.rBEs)
    RadioButton esRb;

    @BindView(R.id.rBEn)
    RadioButton enRb;

    @BindView(R.id.TvSettings)
    TextView settingsTv;
    private String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);
        super.onCreate(savedInstanceState);

        settingsTv.setText(R.string.Settings);
        languageTv.setText(R.string.language);
        logOutButton.setText(R.string.logout);
        applyButton.setText(R.string.apply);
        backButton.setText(R.string.back);


    }

    @OnClick(R.id.rBEs)
    public void ESonClick() {
        idioma = "es";
    }

    @OnClick(R.id.rBEn)
    public void ENonClick() {
        idioma = "en";
    }

    public void logOut(View view) {
        Intent restart = new Intent(this, LoginZoo.class);
        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        restart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(restart);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void apply(View view) {

        attachBaseContext(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Locale("es_AR");

        Context context = ContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }

    public void back(View view) {
        finish();
    }

}
