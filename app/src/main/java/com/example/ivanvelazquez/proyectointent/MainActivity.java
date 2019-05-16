package com.example.ivanvelazquez.proyectointent;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;

        import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String idioma = Locale.getDefault().toString();
    private TextView holaMundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internacionalizar();
    }

    public void internacionalizar(){
         holaMundo=(TextView)findViewById(R.id.tvHolaMundo);
        if (idioma.equals("en_US")){
            holaMundo.setText("Hello World");
        }
        else{
            holaMundo.setText("Hola Mundo");
        }
    }

    public void apretarBoton(View view){
        String mensaje = "mensaje test";
        Intent actboton = new Intent(this, ActBotoneada.class);
        actboton.putExtra("mensajeEntrada",mensaje);
        Log.i("LOG DE INFO---", "Se ingreso al metodo apretar boton y se enviara el mensaje" +mensaje);
        startActivity(actboton);
    }
}
