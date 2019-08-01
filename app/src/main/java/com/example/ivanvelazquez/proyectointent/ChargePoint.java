package com.example.ivanvelazquez.proyectointent;


public class ChargePoint {
    private int bays;
    private String description;

    public ChargePoint(int bays, String description) {
        this.bays = bays;
        this.description = description;
    }


    public int getBays() {
        return bays;
    }

    public String getDescription() {
        return description;
    }


}
