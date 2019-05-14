package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void apretarBoton(View view){
        String mensaje = "mensaje test";
        Intent actboton = new Intent(this, ActBotoneada.class);
        actboton.putExtra("mensajeEntrada",mensaje);
        Log.i("LOG DE INFO---", "Se ingreso al metodo apretar boton y se enviara el mensaje" +mensaje);
        startActivity(actboton);
    }
}
