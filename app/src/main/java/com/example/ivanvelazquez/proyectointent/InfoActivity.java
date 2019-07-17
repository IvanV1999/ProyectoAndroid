package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ivanvelazquez.proyectointent.ZooAnimales.EXTRA_ANIMAL;

public class InfoActivity extends ButterBind implements FavoritoView.Callback {

    public static final String LINK = "link de mayor informacion";
    private Animal animal;
    @BindView(R.id.tvEspecie)
    TextView especie;
    @BindView(R.id.tvInfo)
    TextView info;
    @BindView(R.id.ivFoto)
    ImageView foto;
    @BindView(R.id.TvHorarios)
    TextView horariosTv;
    @BindView(R.id.tvNombre)
    TextView nombre;
    @BindView(R.id.btnRegresar)
    Button regresar;
    @BindView(R.id.btnMasInfo)
    Button masInfo;
    @BindView(R.id.idFavourite)
    FavoritoView favoritoView;
    private String idioma = Locale.getDefault().toString();
    private String url;
    public int baseHour = 0;
    private int backgroundColor = 0;
    public static final String BACKGROUND = "BACKGROUNDCOLOR";
    public static final String BASEHOUR = "BASEHOUR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreSavedInstance(savedInstanceState);
        setContentView(R.layout.activity_info_actiity);
        Bundle bundle = getIntent().getExtras();
        ButterKnife.bind(this);
        info.setMovementMethod(new ScrollingMovementMethod());
        animal = (Animal) bundle.get(EXTRA_ANIMAL);
        favoritoView.setClb(this);

        especie.setText(getString(R.string.species) + animal.getEspecie());
        info.setText(String.format(getString(R.string.description) + animal.getInfo()));
        horariosTv.setText(getString(R.string.show) + String.format(getResources().getString(R.string.atraccion), animal.getAtraccion().getNombre(), animal.getAtraccion().getHolrario()));
        foto.setImageResource(animal.getFoto());
        nombre.setText(getString(R.string.name) + animal.getNombre());
        url = animal.getUrl();
        favoritoView.setEstaLikeado(false);
        favoritoView.setAnimal(animal.getNombre());


        regresar.setText(R.string.back);
        masInfo.setText(R.string.moreInfo);
    }

    private void restoreSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(BACKGROUND);
            int calculatedHour = createTime() - savedInstanceState.getInt(BASEHOUR);
            Log.i("Calculated Hour: ", "" + calculatedHour);
        } else {
            backgroundColor = randomRGB();
            baseHour = createTime();
            Log.i("BaseHour:", "" + baseHour);

        }
        setActivityBackgroundColor(backgroundColor);
    }

    public void regresar(View view) {
        finish();
    }

    public void mostrarMasInfo(View view) {
        Intent irAWeb = new Intent(this, webActivity.class);
        irAWeb.putExtra(LINK, url);
        startActivity(irAWeb);
    }

    private int randomRGB() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return Color.rgb(r, g, b);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(BACKGROUND, backgroundColor);
        savedInstanceState.putInt(BASEHOUR, baseHour);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        backgroundColor = savedInstanceState.getInt(BACKGROUND);
        baseHour = savedInstanceState.getInt(BASEHOUR);
    }

    public int createTime() {
        Calendar calendar = new GregorianCalendar();
        int newHour = calendar.get(Calendar.MINUTE);
        return newHour;
    }

    @Override
    public void onClick() {
        backgroundColor = randomRGB();
        setActivityBackgroundColor(backgroundColor);

    }


}



