package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.Objects;

public class Bet implements Serializable {

    private int misePolitique;
    private int miseEco;
    private int miseSocial;
    private String nameBuilding;
    private int numbuilding;
    private long playerID;

    public Bet(String nameBuilding, int numbuilding, int misePolitique, int miseEco, int miseSocial, long playerID) {
        this.nameBuilding = nameBuilding;
        this.numbuilding = numbuilding;
        this.misePolitique = misePolitique;
        this.miseEco = miseEco;
        this.miseSocial = miseSocial;
        this.playerID = playerID;
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

    public int getNumbuilding() {
        return numbuilding;
    }

    public void setNumbuilding(int numbuilding) {
        this.numbuilding = numbuilding;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return misePolitique == bet.misePolitique &&
                miseEco == bet.miseEco &&
                miseSocial == bet.miseSocial &&
                numbuilding == bet.numbuilding;
    }

    @Override
    public int hashCode() {
        return Objects.hash(misePolitique, miseEco, miseSocial, numbuilding);
    }
}

