package Adapter;


import android.annotation.SuppressLint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivanvelazquez.proyectointent.Animal;
import com.example.ivanvelazquez.proyectointent.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private  ArrayList<Animal> animales;
    private AnimalListener animalListener;
    private int position;

    public void deleteItem() {
        animales.remove(position);
        notifyItemRemoved(position);
    }

    public AdapterDatos(ArrayList<Animal> animales, AnimalListener animalListener) {
        this.animales = animales;
        this.animalListener = animalListener;

    }
    public Animal getAnimalatPosition(int position){
        return animales.get(position);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.asignarDatos(animales.get(position));



    }

    public int getanimalPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public int getItemCount() {
        return animales.size();
    }

    public ArrayList<Animal> getAnimales() {
        return animales;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        @BindView(R.id.tvTexto)
        TextView nombre;
        @BindView(R.id.ImgAnimal)
        ImageView imagen;
        Animal animal;


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            animalListener.onCreateContextMenu(menu, v, menuInfo);
        }


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setPosition(getLayoutPosition());
                    itemView.showContextMenu();
                    return true;
                }
            });
        }

        public void asignarDatos(Animal s) {
            nombre.setText(String.format("%s  |  %s", s.getNombre(), s.getEspecie()));
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
        void shareAnimal(Animal animal);
        void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo);
    }

}
