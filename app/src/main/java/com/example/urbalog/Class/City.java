package com.example.urbalog.Class;

import java.util.ArrayList;
import java.util.Objects;

public class City {
    private ArrayList<Building> buildings;

    public City() {
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
        City city = (City) o;
        return Objects.equals(buildings, city.buildings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildings);
    }

    @Override
    public String toString() {
        return "City{" +
                "buildings=" + buildings +
                '}';
    }
}
