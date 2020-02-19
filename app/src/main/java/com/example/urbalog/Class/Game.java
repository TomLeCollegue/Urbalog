package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game implements Serializable {

    private Integer scoreLogistique;
    private Integer scoreAttractivite;
    private Integer scoreFluidite;
    private Integer scoreEnvironnemental;
    private Market market;
    private City city;
    private List<Bet> listBet;
    private ArrayList<Building> deck;

    public Game() {
        scoreLogistique = 0;
        scoreAttractivite = 0;
        scoreFluidite = 0;
        scoreEnvironnemental = 0;
        city = new City();
    }

    public Integer getScoreLogistique() {
        return scoreLogistique;
    }

    public void setScoreLogistique(Integer scoreLogistique) {
        this.scoreLogistique = scoreLogistique;
    }

    public Integer getScoreAttractivite() {
        return scoreAttractivite;
    }

    public void setScoreAttractivite(Integer scoreAttractivite) {
        this.scoreAttractivite = scoreAttractivite;
    }

    public Integer getScoreFluidite() {
        return scoreFluidite;
    }

    public void setScoreFluidite(Integer scoreFluidite) {
        this.scoreFluidite = scoreFluidite;
    }

    public Integer getScoreEnvironnemental() {
        return scoreEnvironnemental;
    }

    public void setScoreEnvironnemental(Integer scoreEnvironnemental) {
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