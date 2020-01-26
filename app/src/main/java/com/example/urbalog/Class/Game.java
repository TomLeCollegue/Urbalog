package com.example.urbalog.Class;

import com.example.urbalog.MainActivity;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {

    private Integer scoreLogistique;
    private Integer scoreAttractivite;
    private Integer scoreFluidite;
    private Integer scoreEnvironnemental;
    private Market market;
    private City city;

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
        MainActivity.setDataText(Integer.toString(getMarket().getBuildings().size()));
    }
}
