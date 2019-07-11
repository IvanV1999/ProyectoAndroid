package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;


public class LoginZoo extends AppCompatActivity {

    private static final String USRDEF = "GL", PASSDEF = "Android";
    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE", EXTRA_USUARIO = "EXTRA_USUARIO";
    private RadioGroup rgSexo;
    private RadioButton rbMasculino;
    private RadioButton rbFemenino;
    private String opcion;
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
            opcion = (String) getResources().getText(R.string.hi);
        rbMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    opcion = (String) getResources().getText(R.string.welcomeSr);


            }
        });
        rbFemenino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    opcion = (String) getResources().getText(R.string.welcomeMiss);


            }
        });
    }

    public void loguear(View view) {
        usr = user.getText().toString();
        psw = password.getText().toString();
        Log.i("INGRESANDO-USUARIO", usr + " " + psw);
        if (usr.equals(USRDEF) && psw.equals(PASSDEF)) {

            Intent postLog = new Intent(this, ZooAnimales.class);
            postLog.putExtra(EXTRA_MENSAJE, opcion);
            postLog.putExtra(EXTRA_USUARIO, usr);
            Log.i("LOG-INFO", "se inicia la actividad postLogueo, la opcion seleccionada de genero es: " + opcion + " usuario: " + usr);
            startActivity(postLog);

        } else {
            result.setTextColor(getResources().getColor(R.color.colorError));
            result.setText(R.string.incorrecto);
        }


    }
}
