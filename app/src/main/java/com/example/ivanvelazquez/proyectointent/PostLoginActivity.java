package com.example.ivanvelazquez.proyectointent;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PostLoginActivity extends AppCompatActivity {
    private TextView tvSaludo;
    private int opcion;
    private String usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        opcion = getIntent().getIntExtra("EXTRAOPCION",3);
        usr = getIntent().getStringExtra("EXTRAUSR");
        Log.i("DATOSRECIBIDOS",usr+opcion);
        tvSaludo = (TextView)findViewById(R.id.tvSaludo);
        saludar();
    }
    public void saludar(){
        if(opcion==1){
            tvSaludo.setText(R.string.bienvenidosr + usr);
        }
        else if (opcion==2){
            tvSaludo.setText(R.string.bienvenidasra + usr);
        }
        else{
            tvSaludo.setText("Hola "+usr);
        }

    }
}
