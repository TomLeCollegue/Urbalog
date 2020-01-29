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


    public Building(String name, String description, Integer coutPolitique, Integer coutSocial, Integer coutEconomique, Integer effetAttractivite, Integer effetFluidite, Integer effetEnvironnemental) {
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

     /*public void refresh(List<Bet> newBet)
    {
        for(int i = 0; i<newBet.size(); i++)
        {
            if(newBet.get(i).getBuilding().getName() == this.name)
            {
                this.avancementCoutEconomique += newBet.get(i).getMiseEco();
                this.avancementCoutPolitique += newBet.get(i).getMisePolitique();
                this.avancementCoutSocial += newBet.get(i).getMiseSocial();
            }
        }
    } */

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


    public void addAvancementSocial(int mise){
        avancementCoutSocial = avancementCoutSocial + mise;
    }
    public void addAvancementEco(int mise){
        avancementCoutSocial = avancementCoutEconomique + mise;
    }
}