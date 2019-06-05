package com.example.ivanvelazquez.proyectointent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRAMENSAJE;
import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRAUSUARIO;

public class PostLoginActivity extends AppCompatActivity {
    private TextView tvSaludo;
    String opcion;
    String usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString(EXTRAMENSAJE);
         usr = bundle.getString(EXTRAUSUARIO);
        Log.i("DATOSRECIBIDOS",usr+opcion);
        this.tvSaludo = (TextView)findViewById(R.id.tvSaludo);
        saludar();
    }
    public void saludar(){
            tvSaludo.setText(String.format(opcion+usr));
    }
    public void logOut(View view){
        this.finish();
    }
}
