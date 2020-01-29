package com.example.urbalog.Class;

import java.io.Serializable;
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

    public Game() {
        scoreLogistique = 0;
        scoreAttractivite = 0;
        scoreFluidite = 0;
        scoreEnvironnemental = 0;
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

    /*public void addBet(Bet newBet)
    {
        this.listBet.add(newBet);
        for (int i = 0; i < market.getBuildings().size(); i++) {
            market.getBuildings().get(i).refresh(listBet);
        }
    }
    */


    public void majBet(Bet bet){
        market.getBuildings().get(bet.getNumbuilding()).addAvancementSocial(bet.getMiseSocial());
        market.getBuildings().get(bet.getNumbuilding()).addAvancementEco(bet.getMiseEco());
    }



    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(scoreLogistique, game.scoreLogistique) &&
                Objects.equals(scoreAttractivite, game.scoreAttractivite) &&
                Objects.equals(scoreFluidite, game.scoreFluidite) &&
                Objects.equals(scoreEnvironnemental, game.scoreEnvironnemental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreLogistique, scoreAttractivite, scoreFluidite, scoreEnvironnemental);
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

    public void refreshMarket(Market newMarket)
    {
        setMarket(newMarket);
        //updateMarketView(); // à faire quand la vue sera implémenté
        //PlayerViewActivity.setDataText(Integer.toString(getMarket().getBuildings().size()));
    }
}
