package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ButterBind {

    @BindView(R.id.tvHolaMundo)
    TextView holaMundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        internacionalizar();

    }

    public void internacionalizar() {
        holaMundo.setText(R.string.helloWorld);
    }

    public void apretarBoton(View view) {
        String mensaje = "mensaje test";
        Intent actboton = new Intent(this, ActBotoneada.class);
        actboton.putExtra("mensajeEntrada", mensaje);
        Log.i("LOG DE INFO---", "Se ingreso al metodo apretar boton y se enviara el mensaje" + mensaje);
        startActivity(actboton);
    }


}

