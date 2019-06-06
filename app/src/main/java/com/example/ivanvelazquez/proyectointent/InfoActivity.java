package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import static com.example.ivanvelazquez.proyectointent.ZooAnimales.EXTRAANIMAL;

public class InfoActivity extends AppCompatActivity {

    public static final String LINK= "link de mayor informacion";
    private Animal animal;
    private TextView especie;
    private TextView info;
    private ImageView foto;
    private TextView nombre;
    private Button regresar;
    private Button masInfo;
    private String idioma = Locale.getDefault().toString();
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_actiity);
        Bundle bundle = getIntent().getExtras();
        masInfo = findViewById(R.id.btnMasInfo);
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
        url=animal.getUrl();
        asignarTextoBoton();


    }
    public void regresar(View view){
        finish();
    }
    public void mostrarMasInfo(View view){
        Intent irAWeb = new Intent(this,webActivity.class);
        irAWeb.putExtra(LINK,url);
        startActivity(irAWeb);
    }
    public void asignarTextoBoton() {
        if (idioma.equals("en_US")) {
            regresar.setText(R.string.back);
            masInfo.setText(R.string.moreInfo);
        }
    }
}
