package com.example.ivanvelazquez.proyectointent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import Adapter.AdapterCards;
import butterknife.BindView;

public class ChargingPointsActivity extends ButterBind {

    @BindView(R.id.rvBays)
    RecyclerView rvBays;
    private ArrayList<ChargePoint> chargePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chargePoints=new ArrayList<ChargePoint>();
        rvBays.setLayoutManager(new LinearLayoutManager(this));
        loadChargePoints();
        AdapterCards adapterCards = new AdapterCards(chargePoints, new AdapterCardsCallbackImpl());
        rvBays.setAdapter(adapterCards);
    }

    private void loadChargePoints() {

        chargePoints.add(new ChargePoint(1,"Entrance ChargePoint"));
        chargePoints.add(new ChargePoint(8,"Main Hall ChargePoint"));
        chargePoints.add(new ChargePoint(3,"Mammal Sector ChargePoint"));
        chargePoints.add(new ChargePoint(4,"Oviparous Sector ChargePoint"));
        chargePoints.add(new ChargePoint(6,"Restaurant ChargePoint"));
        chargePoints.add(new ChargePoint(6,"Exit ChargePoint"));
        chargePoints.add(new ChargePoint(3,"Reception Sector ChargePoint"));
        chargePoints.add(new ChargePoint(8,"bathroom ChargePoint"));
        chargePoints.add(new ChargePoint(1,"Entrance ChargePoint"));
        chargePoints.add(new ChargePoint(8,"Main Hall ChargePoint"));
        chargePoints.add(new ChargePoint(3,"Mammal Sector ChargePoint"));
        chargePoints.add(new ChargePoint(4,"Oviparous Sector ChargePoint"));
        chargePoints.add(new ChargePoint(5,"Restaurant ChargePoint"));
        chargePoints.add(new ChargePoint(6,"Exit ChargePoint"));
        chargePoints.add(new ChargePoint(1,"Reception Sector ChargePoint"));
        chargePoints.add(new ChargePoint(8,"bathroom ChargePoint"));

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_charging_points;
    }

    private class AdapterCardsCallbackImpl implements AdapterCards.Callback {
        @Override
        public Context getContextForAssing() {
            return getApplicationContext();
        }
    }
}
