package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Building implements Serializable {

    private String name;
    private String description;
    private Integer coutPolitique;
    private Integer coutSocial;
    private Integer coutEconomique;
    private Integer avancementCoutPolitique;
    private Integer avancementCoutSocial;
    private Integer avancementCoutEconomique;
    private Integer effetAttractivite;
    private Integer effetFluidite;
    private Integer effetEnvironnemental;
    private Integer scoreLogistique;
    private String explicationLogistique;

    public Building(String name, String description, Integer coutPolitique, Integer coutSocial, Integer coutEconomique, Integer effetAttractivite, Integer effetFluidite, Integer effetEnvironnemental, Integer scoreLogistique, String explicationLogistique) {
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

    public void setAvancementCoutPolitique(Integer avancementCoutPolitique) {
        this.avancementCoutPolitique = avancementCoutPolitique;
    }

    public void setAvancementCoutSocial(Integer avancementCoutSocial) {
        this.avancementCoutSocial = avancementCoutSocial;
    }

    public void setAvancementCoutEconomique(Integer avancementCoutEconomique) {
        this.avancementCoutEconomique = avancementCoutEconomique;
    }

    public Integer getScoreLogistique() {
        return scoreLogistique;
    }

    public void setScoreLogistique(Integer scoreLogistique) {
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

    public Integer getCoutPolitique() {
        return coutPolitique;
    }

    public void setCoutPolitique(Integer coutPolitique) {
        this.coutPolitique = coutPolitique;
    }

    public Integer getCoutSocial() {
        return coutSocial;
    }

    public void setCoutSocial(Integer coutSocial) {
        this.coutSocial = coutSocial;
    }

    public Integer getCoutEconomique() {
        return coutEconomique;
    }

    public void setCoutEconomique(Integer coutEconomique) {
        this.coutEconomique = coutEconomique;
    }

    public Integer getEffetAttractivite() {
        return effetAttractivite;
    }

    public void setEffetAttractivite(Integer effetAttractivite) {
        this.effetAttractivite = effetAttractivite;
    }

    public Integer getEffetFluidite() {
        return effetFluidite;
    }

    public void setEffetFluidite(Integer effetFluidite) {
        this.effetFluidite = effetFluidite;
    }

    public Integer getEffetEnvironnemental() {
        return effetEnvironnemental;
    }

    public void setEffetEnvironnemental(Integer effetEnvironnemental) {
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

    public Integer getAvancementCoutPolitique() {
        return avancementCoutPolitique;
    }

    public Integer getAvancementCoutSocial() {
        return avancementCoutSocial;
    }

    public Integer getAvancementCoutEconomique() {
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
        if(//(avancementCoutSocial == coutSocial)
                        //&&
                        (avancementCoutPolitique == coutPolitique)
                        && (avancementCoutEconomique == coutEconomique))
            return true;
        else
            return false;
    }
}