package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class LoginZoo extends AppCompatActivity {

    private static final String USRDEF = "GL", PASSDEF = "Android";
    public static final String EXTRAMENSAJE = "mensaje bienvenida", EXTRAUSUARIO = "Usuario";
    private RadioGroup rgSexo;
    private RadioButton rbMasculino;
    private RadioButton rbFemenino;
    private String opcion = "Hola: ";
    private String usr, psw;
    private EditText user;
    private EditText password;
    private TextView result;
    private String idioma = Locale.getDefault().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_zoo);
        user = (EditText) findViewById(R.id.usr);
        password = (EditText) findViewById(R.id.pass);
        result = (TextView) findViewById(R.id.tvResult);

        listener();
    }


    public void listener() {
        rgSexo = (RadioGroup) findViewById(R.id.rgOpciones);
        rbMasculino = (RadioButton) findViewById(R.id.masculino);
        rbFemenino = (RadioButton) findViewById(R.id.femenino);
        if (idioma.equals("en_US")){
            opcion=getResources().getString(R.string.hello);
        }


        rbMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idioma.equals("en_US")){
                    opcion=getResources().getString(R.string.welcomeSr);
                }
                else{
                    opcion=getResources().getString(R.string.bienvenidosr);
                }
            }
        });
        rbFemenino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idioma.equals("en_US")){
                   opcion=getResources().getString(R.string.welcomeMs);
                }
                else{
                    opcion=getResources().getString(R.string.bienvenidasra);
                }

            }
        });
    }

    public void loguear(View view) {
        usr = user.getText().toString();
        psw = password.getText().toString();
        Log.i("INGRESANDO-USUARIO", usr + " " + psw);
        if (usr.equals(USRDEF) && psw.equals(PASSDEF)) {

            Intent postLog = new Intent(this, ZooAnimales.class);
            postLog.putExtra(EXTRAMENSAJE, opcion);
            postLog.putExtra(EXTRAUSUARIO, usr);
            Log.i("LOG-INFO", "se inicia la actividad postLogueo, la opcion seleccionada de genero es: " + opcion + " usuario: " + usr);
            startActivity(postLog);

        } else {
            result.setTextColor(Color.RED);
            result.setText(R.string.incorrecto);
        }


    }
}
