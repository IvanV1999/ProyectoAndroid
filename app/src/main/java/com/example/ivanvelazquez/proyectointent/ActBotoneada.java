package com.example.ivanvelazquez.proyectointent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActBotoneada extends AppCompatActivity {

    private TextView TVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_botoneada);
        TVM = (TextView)findViewById(R.id.tvMensaje);
        String mensajeEntrada = getIntent().getStringExtra("mensajeEntrada");
        TVM.setText(mensajeEntrada);
        Log.i("LOG DE INFO---","Se logueo el mensaje de entrada: "+mensajeEntrada);
    }
}
