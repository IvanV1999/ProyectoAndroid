package com.example.ivanvelazquez.proyectointent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActBotoneada extends ButterBind {

    @BindView(R.id.tvMensaje) TextView TVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mensajeEntrada = getIntent().getStringExtra("mensajeEntrada");
        TVM.setText(mensajeEntrada);
        Log.i("LOG DE INFO---","Se logueo el mensaje de entrada: "+mensajeEntrada);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_act_botoneada;
    }
}
