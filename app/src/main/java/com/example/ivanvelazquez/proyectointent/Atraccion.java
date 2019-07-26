package com.example.ivanvelazquez.proyectointent;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Atraccion implements Serializable {
    private String nombre;
    private Date horario;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM hh:mm:ss");

    public Atraccion(String nombre, String horario){
        this.nombre = nombre;
        setHolrario(horario);
    }

    public String getHolrario() {
        return dateFormat.format(this.horario);
    }

    private void setHolrario(String horario) {

        try {
            this.horario = dateFormat.parse(horario);
        } catch (ParseException e) {
            Log.i("ERR: ","error al tratar de transformar horario");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void actualizarHorario(String nuevoHorario){
            setHolrario(nuevoHorario);
    }
}
