package com.example.ivanvelazquez.proyectointent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.ivanvelazquez.proyectointent.ZooAnimales.EXTRA_ANIMAL;

public class InfoActivity extends ButterBind implements FavoritoView.Callback {

    public static final String LINK = "link de mayor informacion";
    public static final int PERMISSION_CAMERA = 1;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String idioma = Locale.getDefault().toString();
    private String url;
    public int baseHour = 0;
    private int backgroundColor = 0;
    private Uri photoUri;
    public static final String BACKGROUND = "BACKGROUNDCOLOR";
    public static final String BASEHOUR = "BASEHOUR";
    private static final String PACKAGE = "package:";
    private static final int REQUEST_CAMERA = 0;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 30;
    private static final int ERROR_NO_CALENDAR = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_info_actiity);
        super.onCreate(savedInstanceState);
        restoreSavedInstance(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        info.setMovementMethod(new ScrollingMovementMethod());
        animal = (Animal) bundle.get(EXTRA_ANIMAL);
        favoritoView.setClb(this);
        favoritoView.setContext(getApplicationContext());
        especie.setText(String.format("%s %s", getString(R.string.species), animal.getEspecie()));
        info.setText(String.format(getString(R.string.description) + animal.getInfo()));
        horariosTv.setText(String.format("%s %s", getString(R.string.show), String.format(getResources().getString(R.string.atraccion), animal.getAtraccion().getNombre(), animal.getAtraccion().getHolrario())));
        foto.setImageResource(animal.getFoto());
        nombre.setText(String.format("%s %s", getString(R.string.name), animal.getNombre()));
        url = animal.getUrl();
        favoritoView.setEstaLikeado(false);
        favoritoView.setAnimal(animal.getNombre());
        tvCamera.setText(R.string.shareAnimal);


        regresar.setText(R.string.back);
        masInfo.setText(R.string.moreInfo);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.zooinfo));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.infoactivitytoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemshare:
                sendinfo();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        int base = Color.CYAN;
        Random random = new Random();
        int r = (base + random.nextInt(255)) / 2;
        int g = (base + random.nextInt(255)) / 2;
        int b = (base + random.nextInt(255)) / 2;
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
    public Animal onClick() {
        backgroundColor = randomRGB();
        setActivityBackgroundColor(backgroundColor);
        return  animal;
    }


    public void sendinfo() {
        Intent infoIntent = new Intent(Intent.ACTION_SEND);
        infoIntent.setType(getString(R.string.TextType));
        infoIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.animalApplication));
        infoIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s\n\n %s: \n %s", getString(R.string.lookThisAnimal), animal.getNombre(), animal.getInfo()));
        startActivity(Intent.createChooser(infoIntent, getString(R.string.SendInfo)));
    }


    public void takePic() {
        File photo = new File(Environment.getExternalStorageDirectory(), String.format("%s.jpg", animal.getNombre()));

        photoUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".GenericFileProvider", photo);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mailDialog();


        } else {
            Log.d("InfoActivity", "Camera error");
        }

    }

    private void mailDialog() {
        EditText mail = new EditText(this);
        mail.setHint(R.string.examplemail);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.confirmMail))
                .setTitle(getString(R.string.wantToMail))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendMail(mail.getText().toString());

                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setView(mail);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void sendMail(String mail) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType(getString(R.string.AppType));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.animalApplication));
        emailIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.lookAtTheAnimal), animal.getNombre()));
        Log.i("PHOTO URI", photoUri.toString());
        emailIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
        startActivity(Intent.createChooser(emailIntent, getString(R.string.SendMail)));
    }

    @OnClick(R.id.IvCamera)
    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.RequestTitle))
                        .setMessage(getString(R.string.RequestDescription))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse(PACKAGE + getPackageName()));
                                startActivity(intent);


                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_CAMERA);
            }
        } else {
            takePic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePic();
                }
            }

        }
    }


    public boolean isEventAlreadyExist(String eventTitle) {
        final String[] INSTANCE_PROJECTION = new String[]{
                CalendarContract.Instances.EVENT_ID,      // 0
                CalendarContract.Instances.BEGIN,         // 1
                CalendarContract.Instances.TITLE          // 2
        };

        long startMillis = 0;
        long endMillis = 0;

        startMillis = setTimeDate();
        endMillis = setTimeDate(1);

        // The ID of the recurring event whose instances you are searching for in the Instances table
        String selection = CalendarContract.Instances.TITLE + " = ?";
        String[] selectionArgs = new String[]{eventTitle};

        // Construct the query with the desired date range.
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);

        // Submit the query
        Cursor cur = getContentResolver().query(builder.build(), INSTANCE_PROJECTION, selection, selectionArgs, null);
        return cur.getCount() > 0;
    }

    @OnClick(R.id.TvHorarios)
    public void addCalendarEvent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return;
        }

        if (isEventAlreadyExist(this.animal.getAtraccion().getNombre())) {
            Toast.makeText(this, R.string.alredyadded, Toast.LENGTH_SHORT).show();
        } else {
            long calID = getPrimaryCalendar();

            if (calID == ERROR_NO_CALENDAR) {
                Toast.makeText(this, R.string.nocalendarfound, Toast.LENGTH_SHORT).show();
                return;
            }
            long startMillis = 0;
            long endMillis = 0;

            startMillis = setTimeDate();
            endMillis = setTimeDate(1);


            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, this.animal.getAtraccion().getNombre());
            values.put(CalendarContract.Events.DESCRIPTION, R.string.zooshow);
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, R.string.timezone);

            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            long eventID = Long.parseLong(uri.getLastPathSegment());
            Log.i("Calendar", String.format("%s %s", R.string.logevent, eventID));
            Toast.makeText(this, R.string.eventadded, Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);

        }


    }

    @SuppressLint("MissingPermission")
    public long getPrimaryCalendar() {

        //crea indices en vez de hacerlos dinamicamente, para mas performance
        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };


        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        ContentResolver contentResolver = getContentResolver();
        String selection = CalendarContract.Calendars.VISIBLE + " = 1 AND " + CalendarContract.Calendars.IS_PRIMARY + "=1";
        Cursor cur = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, null, null);


        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            return calID;
        }

        return ERROR_NO_CALENDAR;
    }

    public long setTimeDate() {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(this.animal.getAtraccion().getDate());
        return calendarTime.getTimeInMillis();
    }

    public long setTimeDate(int plusHour) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(this.animal.getAtraccion().getDate());
        calendarTime.add(Calendar.HOUR, plusHour);
        return calendarTime.getTimeInMillis();
    }

}