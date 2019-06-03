package Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivanvelazquez.proyectointent.Animal;
import com.example.ivanvelazquez.proyectointent.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    ArrayList<Animal>  animales;

    public AdapterDatos(ArrayList<Animal> animales) {
        this.animales = animales;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tvTexto);
            imagen = itemView.findViewById(R.id.ImgAnimal);
        }

        public void asignarDatos(Animal s) {
            nombre.setText(s.getNombre());
            imagen.setImageResource(s.getImagen());
        }
    }
}
