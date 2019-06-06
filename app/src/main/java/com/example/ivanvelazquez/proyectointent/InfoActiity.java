package com.example.ivanvelazquez.proyectointent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import static com.example.ivanvelazquez.proyectointent.ZooAnimales.EXTRAANIMAL;

public class InfoActiity extends AppCompatActivity {

    private Animal animal;
    private TextView especie;
    private TextView info;
    private ImageView foto;
    private TextView nombre;
    private Button regresar;
    private String idioma = Locale.getDefault().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_actiity);
        Bundle bundle = getIntent().getExtras();
        especie = findViewById(R.id.tvEspecie);
        info = findViewById(R.id.tvInfo);
        info.setMovementMethod(new ScrollingMovementMethod());
        foto = findViewById(R.id.ivFoto);
        regresar = findViewById(R.id.btnRegresar);
        nombre = findViewById(R.id.tvNombre);
        animal = (Animal) bundle.get(EXTRAANIMAL);
        especie.setText("Especie: " + animal.getEspecie());
        info.setText("Descripcion: \n" + animal.getInfo());
        foto.setImageResource(animal.getFoto());
        nombre.setText("Nombre: " + animal.getNombre());
        asignarTextoBoton();

    }
    public void regresar(View view){
        finish();
    }

    public void asignarTextoBoton() {
        if (idioma.equals("en_US")) {
            regresar.setText(R.string.back);
        } else {
            regresar.setText(R.string.regresar);
        }
    }
}
