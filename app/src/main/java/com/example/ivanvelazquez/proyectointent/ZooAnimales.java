package com.example.ivanvelazquez.proyectointent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.AdapterDatos;


public class ZooAnimales extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Animal> animales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_animales);
        mRecyclerView = (RecyclerView)findViewById(R.id.IdRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        cargarAnimales();
        AdapterDatos adapterDatos = new AdapterDatos(animales);
        mRecyclerView.setAdapter(adapterDatos);
    }
    public void cargarAnimales(){
        animales = new ArrayList<Animal>();
        animales.add(new Animal("araña",R.drawable.arania));
        animales.add(new Animal("cerdo",R.drawable.cerdo));
        animales.add(new Animal("gallina",R.drawable.gallina));
    }
}

