package com.example.ivanvelazquez.proyectointent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.ivanvelazquez.proyectointent.ZooAnimales.EXTRAANIMAL;

public class InfoActiity extends AppCompatActivity {

    private  Animal animal;
    private TextView especie;
    private TextView info;
    private ImageView foto;
    private TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_actiity);
        Bundle bundle = getIntent().getExtras();
        especie = (TextView)findViewById(R.id.tvEspecie);
        info = (TextView)findViewById(R.id.tvInfo);
        foto = (ImageView)findViewById(R.id.ivFoto);
        nombre = findViewById(R.id.tvNombre);
        animal = (Animal) bundle.get(EXTRAANIMAL);
        especie.setText("Especie: "+animal.getEspecie());
        info.setText("Descripcion: \n"+animal.getInfo());
        foto.setImageResource(animal.getFoto());
        nombre.setText("Nombre: "+animal.getNombre());


    }
}
