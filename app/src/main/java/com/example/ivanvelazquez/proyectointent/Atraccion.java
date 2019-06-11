package com.example.ivanvelazquez.proyectointent;

public class Atraccion {
    private String nombre;
    private int horario;

    public Atraccion(String nombre, int holrario) {
        this.nombre = nombre;
        this.horario = holrario;
    }

    public int getHolrario() {
        return horario;
    }

    private void setHolrario(int horario) {
        while (horario<0 && horario>)
        this.horario = horario;

    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void actualizarHorario(int nuevoHorario){
            setHolrario(nuevoHorario);
    }
}
