package com.example.urbalog.Class;

import java.io.Serializable;
import com.example.urbalog.Json.JsonBuilding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Market implements Serializable {

    private ArrayList<Building> deck;
    private ArrayList<Building> buildings;

    public Market(){
        this.deck = JsonBuilding.readBuilding();
        this.buildings = new ArrayList<Building>();
        updateMarket();
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void deleteBuilding(Building b){
        int index = 0;
        do {
            if(deck.get(index).getName() == b.getName())
                deck.remove(index);
        }while(++index < deck.size() && deck.get(index).getName() != b.getName());
    }

    public void updateMarket()
    {
        buildings.clear();

        Integer[] arr = new Integer[deck.size()];
        for (int i = 0; i < deck.size(); i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        for(int i = 0; i < 5; i++) {
            buildings.add(deck.get(arr[i]));
            buildings.get(i).reset();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Market)) return false;
        Market market = (Market) o;
        return Objects.equals(deck, market.deck) &&
                Objects.equals(getBuildings(), market.getBuildings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, getBuildings());
    }

    @Override
    public String toString() {
        return "Market{" +
                "buildings=" + buildings +
                '}';
    }
}
