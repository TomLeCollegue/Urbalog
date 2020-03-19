package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Game implements Serializable {

    private int scoreLogistique;
    private int scoreAttractivite;
    private int scoreFluidite;
    private int scoreEnvironnemental;
    private Market market;
    private City city;
    private List<Bet> listBet;
    private int nTurn;

    public Game() {
        scoreLogistique = 0;
        scoreAttractivite = 0;
        scoreFluidite = 0;
        scoreEnvironnemental = 0;
        nTurn = 1;
        city = new City();
    }

    public int getScoreLogistique() {
        return scoreLogistique;
    }

    public void setScoreLogistique(int scoreLogistique) {
        this.scoreLogistique = scoreLogistique;
    }

    public int getScoreAttractivite() {
        return scoreAttractivite;
    }

    public void setScoreAttractivite(int scoreAttractivite) {
        this.scoreAttractivite = scoreAttractivite;
    }

    public int getScoreFluidite() {
        return scoreFluidite;
    }

    public void setScoreFluidite(int scoreFluidite) {
        this.scoreFluidite = scoreFluidite;
    }

    public int getScoreEnvironnemental() {
        return scoreEnvironnemental;
    }

    public void setScoreEnvironnemental(int scoreEnvironnemental) {
        this.scoreEnvironnemental = scoreEnvironnemental;
    }

    public List<Bet> getListBet() {
        return listBet;
    }

    public void setListBet(List<Bet> listBet) {
        this.listBet = listBet;
    }

    public void majBet(Bet bet) {
        market.getBuildings().get(bet.getNumbuilding()).addAvancementSocial(bet.getMiseSocial());
        market.getBuildings().get(bet.getNumbuilding()).addAvancementEco(bet.getMiseEco());
        market.getBuildings().get(bet.getNumbuilding()).addAvancementPolitique(bet.getMisePolitique());
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getnTurn() {
        return nTurn;
    }

    public void setnTurn(int nTurn) {
        this.nTurn = nTurn;
    }

    public void incrTurn()
    {
        nTurn++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(getScoreLogistique(), game.getScoreLogistique()) &&
                Objects.equals(getScoreAttractivite(), game.getScoreAttractivite()) &&
                Objects.equals(getScoreFluidite(), game.getScoreFluidite()) &&
                Objects.equals(getScoreEnvironnemental(), game.getScoreEnvironnemental()) &&
                Objects.equals(getMarket(), game.getMarket()) &&
                Objects.equals(city, game.city) &&
                Objects.equals(getListBet(), game.getListBet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScoreLogistique(), getScoreAttractivite(), getScoreFluidite(), getScoreEnvironnemental(), getMarket(), city, getListBet());
    }

    @NonNull
    @Override
    public String toString() {
        return "Game{" +
                "scoreLogistique=" + scoreLogistique +
                ", scoreAttractivite=" + scoreAttractivite +
                ", scoreFluidite=" + scoreFluidite +
                ", scoreEnvironnemental=" + scoreEnvironnemental +
                '}';
    }

    public void refreshMarket() {
        market.updateMarket();
    }

    /*
    * When called, this method update all the game scores with the methods
    * from city.
    *
     */
    public void updateAllGameScores() {
        this.scoreLogistique = city.updateLogisticScore();
        this.scoreAttractivite = city.updateAttractiviteScore();
        this.scoreFluidite = city.updateFluiditeScore();
        this.scoreEnvironnemental = city.updateEnvironnementalScore();
    }
}