package com.example.ivanvelazquez.proyectointent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Animal implements Serializable {
    private String nombre;
    private int imagen;
    private String especie;
    private String info;
    private int foto;
    private int colorFondo;
    private String url;


    public Animal(String nombre, int imagen, String especie, String info, int foto, int colorFondo, String url) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.especie = especie;
        this.info = info;
        this.foto = foto;
        this.colorFondo = colorFondo;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Animal(String nombre, int imagen, String especie, String info, int foto) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.especie = especie;
        this.info = info;
        this.foto = foto;
        this.colorFondo = 255;
    }

    public int getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(int colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }


}
