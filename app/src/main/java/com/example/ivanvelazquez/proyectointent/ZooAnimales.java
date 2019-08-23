package com.example.ivanvelazquez.proyectointent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import Adapter.AdapterDatos;
import butterknife.BindView;
import butterknife.OnClick;

import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRA_MENSAJE;
import static com.example.ivanvelazquez.proyectointent.LoginZoo.EXTRA_USUARIO;


public class ZooAnimales extends ButterBind implements AdapterDatos.AnimalListener {

    @BindView(R.id.IdRecycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvBienvenido)
    TextView tvBienvenida;
    @BindView(R.id.IvSetting)
    ImageView settings;
    @BindView(R.id.tvZooMaps)
    TextView tvZooMaps;
    @BindView(R.id.tvZooSettings)
    TextView tvZooSettings;
    @BindView(R.id.map)
    ImageView map;
    @BindView(R.id.tvBattery)
    TextView tvBattery;
    @BindView(R.id.ivBattery)
    ImageView ivBattery;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<Animal> animales = new ArrayList<Animal>();
    private String opcion;
    private String usr;
    public static final String EXTRA_ANIMAL = "EXTRA_ANIMAL";
    private BatteryBroadcastReceiver receiver = new BatteryBroadcastReceiver();
    private AdapterDatos adapterDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString(EXTRA_MENSAJE);
        usr = bundle.getString(EXTRA_USUARIO);
        saludar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, mRecyclerView.VERTICAL, false));
        cargarAnimales();
        adapterDatos = new AdapterDatos(animales, this);
        mRecyclerView.setAdapter(adapterDatos);
        tvZooMaps.setText(R.string.map);
        tvZooSettings.setText(R.string.Settings);

        registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_OKAY));


        setSupportActionBar(toolbar);
        setTitle(getString(R.string.zoo));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zooanimalestoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        // casteamos a la clase de nuestro adapter para poder usar el metodo getPosition() que definimos anteriormente
        int position = ((AdapterDatos) mRecyclerView.getAdapter()).getanimalPosition();
        Animal animal = ((AdapterDatos) mRecyclerView.getAdapter()).getAnimalatPosition(position);

        switch (item.getItemId()) {
            case R.id.optionDelete:
                Toast.makeText(this, R.string.deletedAnimal, Toast.LENGTH_SHORT).show();
                adapterDatos.deleteItem();
                return true;
            case R.id.optionshare:
                Toast.makeText(this, R.string.sharingAnimal, Toast.LENGTH_SHORT).show();
                shareAnimal(animal);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void shareAnimal(Animal animal) {
        Intent infoIntent = new Intent(Intent.ACTION_SEND);
        infoIntent.setType(getString(R.string.TextType));
        infoIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.animalApplication));
        infoIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s\n\n %s: \n %s", getString(R.string.lookThisAnimal), animal.getNombre(), animal.getInfo()));
        startActivity(Intent.createChooser(infoIntent, getString(R.string.SendInfo)));
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_zoo_animales;
    }

    @OnClick(R.id.map)
    public void showMap() {
        double coordinateF = -34.9085327;
        double coordinateS = -57.9378387;
        Uri uri = Uri.parse("geo:" + coordinateF + "," + coordinateS);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void cargarAnimales() {

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico ", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));

        animales.add(new Animal("araña", R.drawable.arania, "Araneae", "El grupo está abundantemente representado en todos los continentes, excepto en la Antártida. Todas son depredadoras, generalmente solitarias, de pequeños animales.", R.drawable.spider, randomColour(), "http://es.wikipedia.org/wiki/Araneae", new Atraccion("Las arañas de Peter", "17-05-2019 12:00:00")));
        animales.add(new Animal("cerdo", R.drawable.cerdo, "Sus scrofa", "Es un animal doméstico", R.drawable.cerditogrande, randomColour(), "http://es.wikipedia.org/wiki/Sus_scrofa_domestica", new Atraccion("Peppa", "17-05-2019 14:00:00")));
        animales.add(new Animal("gallina", R.drawable.gallina, "G. gallus", "una especie de ave galliforme de la familia Phasianidae procedente del sudeste asiático. Los nombres comunes son: gallo, para el macho; gallina, para la hembra, y pollo, para los subadultos.", R.drawable.gallinagrande, randomColour(), "http://es.wikipedia.org/wiki/Gallus_gallus_domesticus", new Atraccion("Pequeño Pollito ", "17-05-2019 08:00:00")));
        animales.add(new Animal("gato", R.drawable.gato, "F. silvestris", "es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano.", R.drawable.gatogrande, randomColour(), "http://es.wikipedia.org/wiki/Felis_silvestris_catus", new Atraccion("El gato con botas", "17-05-2019 16:30:00")));
        animales.add(new Animal("panda", R.drawable.panda, "Ailuropoda melanoleuca", "es una especie de mamífero del orden de los carnívoros y aunque hay una gran controversia al respecto, los últimos estudios de su ADN lo engloban entre los miembros de la familia de los osos ", R.drawable.pandagrande, randomColour(), "http://es.wikipedia.org/wiki/Panda", new Atraccion("karate panda", "17-05-2019 09:15:00")));
        animales.add(new Animal("perro", R.drawable.perro, "(Canis lupus familiaris", "es un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.\n\n\n\n\nes un mamífero carnívoro de la familia de los cánidos, que constituye una subespecie del lobo (Canis lupus). En 2001, se estimaba que había cuatrocientos millones de perros en el mundo.El perro doméstico proviene de un ancestro o grupo ancestral común que data de hace aproximadamente 30 000 años y desde entonces se ha extendido a todas partes del mundo Los primeros restos fósiles de perros enterrados junto con humanos fueron encontrados en Israel y datan de hace unos 12 000 años. Desde entonces, los perros y los humanos han evolucionado conjuntamente, tanto en las culturas africanas y euroasiáticas, como en las que poblaron América y se mantuvieron sin contacto con aquellas hasta el siglo XV. Los perros comparten el entorno, los hábitos y el estilo de vida humanos, como las dietas ricas en cereales y almidón. La alimentación inadecuada, así como el uso de antibióticos, son la causa del desarrollo de muchas enfermedades inflamatorias e inmunológicas. Unas cuatrocientas enfermedades del perro tienen una equivalente humana, destacando especialmente la enfermedad de Alzheimer y otros trastornos neurológicos, así como cánceres, enfermedades autoinmunes y enfermedades cardiovasculares.", R.drawable.perrogrande, randomColour(), "http://es.wikipedia.org/wiki/Canis_lupus_familiaris", new Atraccion("Bolt", "17-05-2019 11:45:00")));
        animales.add(new Animal("Dragon", R.drawable.dragon, "Dragonicus", "Especie en peligro de exticion, de hecho es el unico que existe actualmente en el mundo.\n By Fernando Leon Edmundo Martel Stefani", R.drawable.dragongrande, randomColour(), "http://es.wikipedia.org/wiki/Drag%C3%B3n", new Atraccion("Como entrenar a tu dragon", "17-05-2019 17:00:00")));


    }

    public void saludar() {
        tvBienvenida.setText(String.format(opcion, usr));
    }

    @Override
    public void onClick(Animal animal) {

        Intent intentDatos = new Intent(this, InfoActivity.class);
        intentDatos.putExtra(EXTRA_ANIMAL, animal);
        startActivity(intentDatos);

    }


    private int randomColour() {
        int base = Color.CYAN;
        Random random = new Random();
        int r = (base + random.nextInt(255)) / 2;
        int g = (base + random.nextInt(255)) / 2;
        int b = (base + random.nextInt(255)) / 2;
        return Color.rgb(r, g, b);


    }

    public void goToSettings(View view) {
        Intent settingsAct = new Intent(this, SettingsActivity.class);
        startActivity(settingsAct);
    }

    @OnClick(R.id.ivBattery)
    public void chargingPointIntent() {
        Intent chargingPointIntent = new Intent(this, ChargingPointsActivity.class);
        startActivity(chargingPointIntent);
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Intent.ACTION_BATTERY_LOW) {
                tvBattery.setText(getString(R.string.lowBattery));
                ivBattery.setImageResource(R.drawable.lowbattery);
            }
            if (intent.getAction() == Intent.ACTION_BATTERY_OKAY) {
                tvBattery.setText(null);
                ivBattery.setImageResource(0);
            }
        }
    }
}


