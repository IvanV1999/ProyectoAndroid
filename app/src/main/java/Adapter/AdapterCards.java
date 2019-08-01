package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ivanvelazquez.proyectointent.ChargePoint;
import com.example.ivanvelazquez.proyectointent.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCards extends RecyclerView.Adapter<AdapterCards.ViewHolder> {

    ArrayList<ChargePoint> chargePoints;
    private final Callback clb;
    private final int STANDAR_CUANTITY_CARDS = 4;

    public AdapterCards(ArrayList<ChargePoint> chargePoints, Callback clb) {
        this.chargePoints = chargePoints;
        this.clb = clb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chargepointcard, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.assingData(chargePoints.get(position));
    }

    @Override
    public int getItemCount() {
        return chargePoints.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivBatterycard)
        ImageView ivBatterycard;
        @BindView(R.id.tvbays)
        TextView tvBays;
        @BindView(R.id.tvbayDescription)
        TextView tvBayDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void assingData(ChargePoint ch) {
            tvBays.setText(String.format("%s    %s", clb.getContextForAssing().getString(R.string.aviableBays), ch.getBays()));
            tvBayDescription.setText(ch.getDescription());
            if (ch.getBays() <= STANDAR_CUANTITY_CARDS) {
                ivBatterycard.setImageResource(R.drawable.lowbattery);
            } else {
                ivBatterycard.setImageResource(R.drawable.chargedbattery);
            }
        }
    }

    public interface Callback {
        Context getContextForAssing();
    }
}
