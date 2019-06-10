package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import Adapter.AdapterDatos;

import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRAMENSAJE;
import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRAUSUARIO;


public class ZooAnimales extends AppCompatActivity implements AdapterDatos.AnimalListener {

    private RecyclerView mRecyclerView;
    private ArrayList<Animal> animales = new ArrayList<Animal>();
    private TextView tvBienvenida;
    private String opcion;
    private String usr;
    public static final String EXTRAANIMAL = "se envia animal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_animales);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString(EXTRAMENSAJE);
        usr = bundle.getString(EXTRAUSUARIO);
        tvBienvenida = (TextView) findViewById(R.id.tvBienvenido);
        saludar();
        mRecyclerView = (RecyclerView) findViewById(R.id.IdRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cargarAnimales();
        AdapterDatos adapterDatos = new AdapterDatos(animales, this);
        mRecyclerView.setAdapter(adapterDatos);

    }

    public void cargarAnimales() {

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.araniagrande, randomColour()));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico usado en la alimentación humana por muchos pueblos", R.drawable.cerditogrande, randomColour()));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour()));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour()));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour()));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.", R.drawable.perrogrande, randomColour()));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour()));

    }

    public void saludar() {
        tvBienvenida.setText(String.format(opcion + usr));
    }

    @Override
    public void onClick(Animal animal) {

        Intent intentDatos = new Intent(this, InfoActiity.class);
        intentDatos.putExtra(EXTRAANIMAL, animal);
        startActivity(intentDatos);

    }

    private int randomColour() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);


    }
}


