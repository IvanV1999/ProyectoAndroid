package com.example.ivanvelazquez.proyectointent;

import android.content.res.Resources;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PostLoginActivity extends AppCompatActivity {
    private TextView tvSaludo;
    int opcion;
    String usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getInt("EXTRAOPCION");
         usr = bundle.getString("EXTRAUSUARIO");
        Log.i("DATOSRECIBIDOS",usr+opcion);
        this.tvSaludo = (TextView)findViewById(R.id.tvSaludo);
        saludar();
    }
    public void saludar(){

        if(opcion==1){

            tvSaludo.setText(String.format(getString(R.string.bienvenidosr),usr));
        }
        else if (opcion==2){

            tvSaludo.setText(String.format(getString(R.string.bienvenidasra),usr));
        }
        else{
            tvSaludo.setText(String.format(getString(R.string.bienvenidoDefault),usr));
        }

    }
    public void logOut(View view){
        this.finish();
    }
}
