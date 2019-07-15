package com.example.ivanvelazquez.proyectointent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRA_MENSAJE;
import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRA_USUARIO;

public class PostLoginActivity extends AppCompatActivity {
    @BindView(R.id.tvSaludo)
    TextView tvSaludo;
    String opcion;
    String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString(EXTRA_MENSAJE);
        usr = bundle.getString(EXTRA_USUARIO);
        ButterKnife.bind(this);
        Log.i("DATOSRECIBIDOS", usr + opcion);
        saludar();
    }

    public void saludar() {
        tvSaludo.setText(String.format(opcion + usr));
    }

    public void logOut(View view) {
        this.finish();
    }
}
