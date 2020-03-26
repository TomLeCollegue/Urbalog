package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Building implements Serializable {

    private String name;
    private String description;
    private int coutPolitique;
    private int coutSocial;
    private int coutEconomique;
    private int avancementCoutPolitique;
    private int avancementCoutSocial;
    private int avancementCoutEconomique;
    private int effetAttractivite;
    private int effetFluidite;
    private int effetEnvironnemental;
    private int scoreLogistique;
    private String explicationLogistique;

    public Building(String name, String description, int coutPolitique, int coutSocial, int coutEconomique, int effetAttractivite, int effetFluidite, int effetEnvironnemental, int scoreLogistique, String explicationLogistique) {
        this.name = name;
        this.description = description;
        this.coutPolitique = coutPolitique;
        this.coutSocial = coutSocial;
        this.coutEconomique = coutEconomique;
        this.avancementCoutPolitique = 0;
        this.avancementCoutSocial = 0;
        this.avancementCoutEconomique = 0;
        this.effetAttractivite = effetAttractivite;
        this.effetFluidite = effetFluidite;
        this.effetEnvironnemental = effetEnvironnemental;
        this.scoreLogistique = scoreLogistique;
        this.explicationLogistique = explicationLogistique;
    }

    public void setAvancementCoutPolitique(int avancementCoutPolitique) {
        this.avancementCoutPolitique = avancementCoutPolitique;
    }

    public void setAvancementCoutSocial(int avancementCoutSocial) {
        this.avancementCoutSocial = avancementCoutSocial;
    }

    public void setAvancementCoutEconomique(int avancementCoutEconomique) {
        this.avancementCoutEconomique = avancementCoutEconomique;
    }

    public int getScoreLogistique() {
        return scoreLogistique;
    }

    public void setScoreLogistique(int scoreLogistique) {
        this.scoreLogistique = scoreLogistique;
    }

    public String getExplicationLogistique() {
        return explicationLogistique;
    }

    public void setExplicationLogistique(String explicationLogistique) {
        this.explicationLogistique = explicationLogistique;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoutPolitique() {
        return coutPolitique;
    }

    public void setCoutPolitique(int coutPolitique) {
        this.coutPolitique = coutPolitique;
    }

    public int getCoutSocial() {
        return coutSocial;
    }

    public void setCoutSocial(int coutSocial) {
        this.coutSocial = coutSocial;
    }

    public int getCoutEconomique() {
        return coutEconomique;
    }

    public void setCoutEconomique(int coutEconomique) {
        this.coutEconomique = coutEconomique;
    }

    public int getEffetAttractivite() {
        return effetAttractivite;
    }

    public void setEffetAttractivite(int effetAttractivite) {
        this.effetAttractivite = effetAttractivite;
    }

    public int getEffetFluidite() {
        return effetFluidite;
    }

    public void setEffetFluidite(int effetFluidite) {
        this.effetFluidite = effetFluidite;
    }

    public int getEffetEnvironnemental() {
        return effetEnvironnemental;
    }

    public void setEffetEnvironnemental(int effetEnvironnemental) {
        this.effetEnvironnemental = effetEnvironnemental;
    }

     public void reset(){
         avancementCoutEconomique = 0;
         avancementCoutPolitique = 0;
         avancementCoutSocial = 0;
     }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(name, building.name) &&
                Objects.equals(description, building.description) &&
                Objects.equals(coutPolitique, building.coutPolitique) &&
                Objects.equals(coutSocial, building.coutSocial) &&
                Objects.equals(coutEconomique, building.coutEconomique) &&
                Objects.equals(effetAttractivite, building.effetAttractivite) &&
                Objects.equals(effetFluidite, building.effetFluidite) &&
                Objects.equals(effetEnvironnemental, building.effetEnvironnemental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, coutPolitique, coutSocial, coutEconomique, effetAttractivite, effetFluidite, effetEnvironnemental);
    }

    @NonNull
    @Override
    public String toString() {
        return "Building ===> {" +
                "\nname='" + name + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \ncoutPolitique=" + coutPolitique +
                ", \ncoutSocial=" + coutSocial +
                ", \ncoutEconomique=" + coutEconomique +
                ", \neffetAttractivite=" + effetAttractivite +
                ", \neffetFluidite=" + effetFluidite +
                ", \neffetEnvironnemental=" + effetEnvironnemental +
                '}';
    }

    public int getAvancementCoutPolitique() {
        return avancementCoutPolitique;
    }

    public int getAvancementCoutSocial() {
        return avancementCoutSocial;
    }

    public int getAvancementCoutEconomique() {
        return avancementCoutEconomique;
    }


    public void addAvancementSocial(int miseSocial){
        avancementCoutSocial = avancementCoutSocial + miseSocial;
    }
    public void addAvancementEco(int miseEco){
        avancementCoutEconomique = avancementCoutEconomique + miseEco;
    }
    public  void addAvancementPolitique(int misePoli){
        avancementCoutPolitique = avancementCoutPolitique + misePoli;
    }

    public boolean isFilled()
    {
        return /*(avancementCoutSocial == coutSocial) && */(avancementCoutPolitique == coutPolitique) && (avancementCoutEconomique == coutEconomique);
    }
}