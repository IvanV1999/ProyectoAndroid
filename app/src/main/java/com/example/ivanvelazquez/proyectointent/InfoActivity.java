package com.example.ivanvelazquez.proyectointent;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tvShare)
    TextView tvCamera;
    @BindView(R.id.IvCamera)
    ImageView ivCamera;
    @BindView(R.id.IvShare)
    ImageView ivShare;
    private String idioma = Locale.getDefault().toString();
    private String url;
    public int baseHour = 0;
    private int backgroundColor = 0;
    private Uri photoUri;
    public static final String BACKGROUND = "BACKGROUNDCOLOR";
    public static final String BASEHOUR = "BASEHOUR";
    private static final int REQUEST_CAMERA = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreSavedInstance(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        info.setMovementMethod(new ScrollingMovementMethod());
        animal = (Animal) bundle.get(EXTRA_ANIMAL);
        favoritoView.setClb(this);

        especie.setText(String.format("%s %s",getString(R.string.species),animal.getEspecie()));
        info.setText(String.format(getString(R.string.description) + animal.getInfo()));
        horariosTv.setText(String.format("%s %s",getString(R.string.show),String.format(getResources().getString(R.string.atraccion), animal.getAtraccion().getNombre(), animal.getAtraccion().getHolrario())));
        foto.setImageResource(animal.getFoto());
        nombre.setText(String.format("%s %s",getString(R.string.name),animal.getNombre()));
        url = animal.getUrl();
        favoritoView.setEstaLikeado(false);
        favoritoView.setAnimal(animal.getNombre());
        tvCamera.setText(R.string.shareAnimal);


        regresar.setText(R.string.back);
        masInfo.setText(R.string.moreInfo);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_info_actiity;
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


    @OnClick(R.id.IvShare)
    public void sendinfo() {
        Intent infoIntent = new Intent(Intent.ACTION_SEND);
        infoIntent.setType("text/plain");
        infoIntent.putExtra(Intent.EXTRA_SUBJECT, "Aplicacion Animales");
        infoIntent.putExtra(Intent.EXTRA_TEXT, String.format("Lee la info de este animal:\n\n %s: \n %s", animal.getNombre(), animal.getInfo()));
        startActivity(Intent.createChooser(infoIntent, "Send info..."));
    }


    @OnClick(R.id.IvCamera)
    public void takePic() {
        File photo = new File(android.os.Environment.getExternalStorageDirectory(), "Animal.jpg");
        photoUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".GenericFileProvider", photo);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            sendMail();
            photoUri = null;
        } else {
            Log.d("InfoActivity", "Camera error");
        }

    }

    public void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ivandvelazquez99@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Aplicacion Animales");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Mirá el " + animal.getNombre() + " que estoy viendo!");
        emailIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

}



