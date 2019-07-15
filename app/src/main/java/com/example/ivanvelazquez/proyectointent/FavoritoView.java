package com.example.ivanvelazquez.proyectointent;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FavoritoView extends LinearLayout {
    @BindView(R.id.StarID)
    ImageView star;
    @BindView(R.id.tvFavorito)
    TextView fav;
    private String animal;
    private boolean estaLikeado = false;
    public Callback clb;
    public static final String SUPERSTATE = "SUPERSTATE";
    public static final String LIKEADO = "LIKEADO";

    public void setClb(Callback clb) {
        this.clb = clb;
    }

    public FavoritoView(Context context) {
        super(context);
        init(context, null);
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
        void onClick();
    }

    @OnClick(R.id.StarID)
    public void asd() {
        favChangeState();
        clb.onClick();

    }

}
