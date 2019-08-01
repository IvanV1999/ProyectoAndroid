package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.ivanvelazquez.proyectointent.R.drawable.globallogic2;


public class LoginZoo extends ButterBind {

    private static final String USRDEF = "GL", PASSDEF = "Android";
    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE", EXTRA_USUARIO = "EXTRA_USUARIO";
    @BindView(R.id. imgLogo)
    ImageView ivLogo;
    @BindView(R.id.rgOpciones)
    RadioGroup rgSexo;
    @BindView(R.id.masculino)
    RadioButton rbMasculino;
    @BindView(R.id.femenino)
    RadioButton rbFemenino;
    @BindView(R.id.usr)
    EditText user;
    @BindView(R.id.pass)
    EditText password;
    @BindView(R.id.tvResult)
    TextView result;
    private String opcion;
    private String usr, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opcion = (String) getResources().getText(R.string.hi);
        ivLogo.setImageResource(globallogic2);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login_zoo;
    }

    @OnClick({R.id.masculino})
    public void onClickm() {
        opcion = (String) getResources().getText(R.string.welcomeSr);
    }

    @OnClick(R.id.femenino)
    public void onClickf() {
        opcion = (String) getResources().getText(R.string.welcomeMiss);
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
