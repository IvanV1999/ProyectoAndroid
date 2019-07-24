package Adapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivanvelazquez.proyectointent.Animal;
import com.example.ivanvelazquez.proyectointent.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private static ArrayList<Animal> animales;
    private AnimalListener animalListener;


    public AdapterDatos(ArrayList<Animal> animales, AnimalListener animalListener) {
        this.animales = animales;
        this.animalListener = animalListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.asignarDatos(animales.get(i));

    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvTexto)
        TextView nombre;
        @BindView(R.id.ImgAnimal)
        ImageView imagen;
        Animal animal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void asignarDatos(Animal s) {
            nombre.setText(String.format("%s  |  %s",s.getNombre(),s.getEspecie()));
            imagen.setImageResource(s.getImagen());
            animal = s;
            itemView.setBackgroundColor(s.getColorFondo());

        }

        @OnClick(R.id.idItemList)
        public void onClick() {
            animalListener.onClick(animal);

        }

    }

    public interface AnimalListener {
        void onClick(Animal animal);
    }

}
