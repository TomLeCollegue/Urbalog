package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class City implements Serializable {
    private ArrayList<Building> buildings;

    public City() {
        this.buildings = new ArrayList<Building>();
    }

    public ArrayList<Building> getBuildings() {
        return (ArrayList<Building>) buildings.clone();
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void addBuilding(Building b) {
        buildings.add(b);
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

    @NonNull
    @Override
    public String toString() {
        return "City{" +
                "buildings=" + buildings +
                '}';
    }

    /*
    * This function is called at the end of the game and changes the logistic score
    * of the game with the sum of the logistic scores of all the buildings.
    *
    *
     */
    public int updateLogisticScore(){
        int res = 0;
        for(Building building : buildings){
            res = res + building.getScoreLogistique();
        }
        return res;
    }
    public int updateAttractiviteScore(){
        int res = 0;
        for(Building building : buildings){
            res = res + building.getEffetAttractivite();
        }
        return res;
    }
    public int updateFluiditeScore(){
        int res = 0;
        for(Building building : buildings){
            res = res + building.getEffetFluidite();
        }
        return res;
    }
    public int updateEnvironnementalScore(){
        int res = 0;
        for(Building building : buildings){
            res = res + building.getEffetEnvironnemental();
        }
        return res;
    }
}
