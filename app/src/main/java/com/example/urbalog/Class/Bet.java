package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.Objects;

public class Bet extends Object implements Serializable {
    private Player player;
    private Building building;
    private int misePolitique;
    private int miseEco;
    private int miseSocial;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getMisePolitique() {
        return misePolitique;
    }

    public void setMisePolitique(int misePolitique) {
        this.misePolitique = misePolitique;
    }

    public int getMiseEco() {
        return miseEco;
    }

    public void setMiseEco(int miseEco) {
        this.miseEco = miseEco;
    }

    public int getMiseSocial() {
        return miseSocial;
    }

    public void setMiseSocial(int miseSocial) {
        this.miseSocial = miseSocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bet)) return false;
        Bet bet = (Bet) o;
        return getMisePolitique() == bet.getMisePolitique() &&
                getMiseEco() == bet.getMiseEco() &&
                getMiseSocial() == bet.getMiseSocial() &&
                Objects.equals(getPlayer(), bet.getPlayer()) &&
                Objects.equals(getBuilding(), bet.getBuilding());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer(), getBuilding(), getMisePolitique(), getMiseEco(), getMiseSocial());
    }

    @Override
    public String toString() {
        return "Bet{" +
                "player=" + player.toString() +
                ", building=" + building.toString() +
                ", misePolitique=" + misePolitique +
                ", miseEco=" + miseEco +
                ", miseSocial=" + miseSocial +
                '}';
    }
}
