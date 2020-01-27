package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Market implements Serializable {

    private ArrayList<Building> buildings;

    public Market(){
        this.buildings = new ArrayList<Building>();
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return Objects.equals(buildings, market.buildings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildings);
    }

    @Override
    public String toString() {
        return "Market{" +
                "buildings=" + buildings +
                '}';
    }
}
