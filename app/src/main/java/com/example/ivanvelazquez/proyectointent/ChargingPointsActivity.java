package com.example.ivanvelazquez.proyectointent;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.AdapterCards;
import butterknife.BindView;

public class ChargingPointsActivity extends ButterBind {

    @BindView(R.id.rvBays)
    RecyclerView rvBays;
    private ArrayList<ChargePoint> chargePoints;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chargePoints = new ArrayList<ChargePoint>();
        rvBays.setLayoutManager(new LinearLayoutManager(this));
        loadChargePoints();
        AdapterCards adapterCards = new AdapterCards(chargePoints, new AdapterCardsCallbackImpl());
        rvBays.setAdapter(adapterCards);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.zooChargePoint));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.zoogoback:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chargepointtoolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadChargePoints() {

        chargePoints.add(new ChargePoint(1, "Entrance ChargePoint"));
        chargePoints.add(new ChargePoint(8, "Main Hall ChargePoint"));
        chargePoints.add(new ChargePoint(3, "Mammal Sector ChargePoint"));
        chargePoints.add(new ChargePoint(4, "Oviparous Sector ChargePoint"));
        chargePoints.add(new ChargePoint(6, "Restaurant ChargePoint"));
        chargePoints.add(new ChargePoint(6, "Exit ChargePoint"));
        chargePoints.add(new ChargePoint(3, "Reception Sector ChargePoint"));
        chargePoints.add(new ChargePoint(8, "bathroom ChargePoint"));
        chargePoints.add(new ChargePoint(1, "Entrance ChargePoint"));
        chargePoints.add(new ChargePoint(8, "Main Hall ChargePoint"));
        chargePoints.add(new ChargePoint(3, "Mammal Sector ChargePoint"));
        chargePoints.add(new ChargePoint(4, "Oviparous Sector ChargePoint"));
        chargePoints.add(new ChargePoint(5, "Restaurant ChargePoint"));
        chargePoints.add(new ChargePoint(6, "Exit ChargePoint"));
        chargePoints.add(new ChargePoint(1, "Reception Sector ChargePoint"));
        chargePoints.add(new ChargePoint(8, "bathroom ChargePoint"));

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
