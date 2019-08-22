package com.example.ivanvelazquez.proyectointent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;


public class FavoritoView extends LinearLayout {
    @BindView(R.id.StarID)
    ImageView star;
    @BindView(R.id.tvFavorito)
    TextView fav;
    private Animal callbackAnimal;
    private String animal;
    private boolean estaLikeado = false;
    public Callback clb;
    private final int timeAlarm = 2000;
    public static final String SUPERSTATE = "SUPERSTATE";
    public static final String LIKEADO = "LIKEADO";
    public static final String EXTRA_BUNDLE= "EXTRA_BUNDLE";
    public static final int ALARM_REQUEST_CODE = 12;
    public Context context;

    public void setClb(Callback clb) {
        this.clb = clb;
    }

    public FavoritoView(Context context) {
        super(context);
        init(context, null);

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public FavoritoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FavoritoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.favoritosview, this, true);
        ButterKnife.bind(this);
        fav.setText(String.format(setState(), getAnimal()));
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPERSTATE, super.onSaveInstanceState());
        bundle.putBoolean(LIKEADO, this.estaLikeado);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) // implicit null check
        {
            Bundle bundle = (Bundle) state;
            this.estaLikeado = bundle.getBoolean(LIKEADO);
            state = bundle.getParcelable(SUPERSTATE);
        }
        super.onRestoreInstanceState(state);
    }

    public void setEstaLikeado(boolean likeado) {
        estaLikeado = likeado;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
    }

    public String setState() {
        String text;
        text = estaLikeado ? getResources().getString(R.string.recuestGrats) : getResources().getString(R.string.recuest);
        return text;

    }

    public void favChangeState() {
        star.setImageResource(estaLikeado ? R.drawable.nofavorito : R.drawable.favorito);
        estaLikeado = !estaLikeado;
        fav.setText(String.format(setState(), getAnimal()));
    }

    public interface Callback {
        Animal onClick();
    }

    @OnClick(R.id.StarID)
    public void favouriteAnimal() {
        favChangeState();
        callbackAnimal = clb.onClick();
        setAlarm(callbackAnimal);

    }

    public void setAlarm(Animal animal) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);


        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        //Bundle bundle = new Bundle();
        alarmIntent.putExtra(AlarmReceiver.EXTRA_ATRACCION, animal.getAtraccion());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_INFO, animal.getInfo());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_NOMBRE, animal.getNombre());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_URL, animal.getUrl());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_COLOR_FONDO, animal.getColorFondo());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_ESPECIE, animal.getEspecie());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_FOTO, animal.getFoto());
        alarmIntent.putExtra(AlarmReceiver.EXTRA_IMAGEN, animal.getImagen());

        //alarmIntent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeAlarm, pendingIntent);
        }


    }

}
