package com.example.ivanvelazquez.proyectointent;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

import butterknife.BindView;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsTv.setText(R.string.Settings);
        languageTv.setText(R.string.language);
        logOutButton.setText(R.string.logout);
        applyButton.setText(R.string.apply);
        backButton.setText(R.string.back);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.zooSettings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingstoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
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
