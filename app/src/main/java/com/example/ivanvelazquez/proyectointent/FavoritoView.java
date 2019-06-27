package com.example.ivanvelazquez.proyectointent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FavoritoView extends LinearLayout{
    private String animal;
    private TextView fav;
    private ImageView star;
    private boolean estaLikeado=false;

    public FavoritoView(Context context) {
        super(context);
        init(context,null);
    }

    public FavoritoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FavoritoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.favoritosview,this, true );
        star= findViewById(R.id.StarID);
        fav= findViewById(R.id.tvFavorito);


        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.attrFavoritos,0,0);

        fav.setText(String.format(setState(),getAnimal()));
        star.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estaLikeado){
                    estaLikeado=false;
                    fav.setText(String.format(setState(),getAnimal()));
                    star.setImageResource(R.drawable.nofavorito);

                }
                else{
                    estaLikeado=true;
                    fav.setText(String.format(setState(),getAnimal()));
                    star.setImageResource(R.drawable.favorito);
                }
            }
        });
    }
    public void setEstaLikeado(boolean likeado){
        estaLikeado=likeado;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
    }

    public String setState(){
        String text;
        text = estaLikeado ? getResources().getString(R.string.recuestGrats) :getResources().getString(R.string.recuest);
        return text;

    }

}
