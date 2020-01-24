package com.example.urbalog.Class;

import java.util.Objects;

public class Building {

    private String nameBuilding;
    private String description;
    private String information;
    private Integer scoreLogistique;
    private Integer scoreAttractivite;
    private Integer scoreFluidite;
    private Integer scoreEnvironnemental;

    public Building(String nameBuilding, String description, String information, Integer scoreLogistique, Integer scoreAttractivite, Integer scoreFluidite, Integer scoreEnvironnemental) {
        this.nameBuilding = nameBuilding;
        this.description = description;
        this.information = information;
        this.scoreLogistique = scoreLogistique;
        this.scoreAttractivite = scoreAttractivite;
        this.scoreFluidite = scoreFluidite;
        this.scoreEnvironnemental = scoreEnvironnemental;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(nameBuilding, building.nameBuilding) &&
                Objects.equals(description, building.description) &&
                Objects.equals(information, building.information) &&
                Objects.equals(scoreLogistique, building.scoreLogistique) &&
                Objects.equals(scoreAttractivite, building.scoreAttractivite) &&
                Objects.equals(scoreFluidite, building.scoreFluidite) &&
                Objects.equals(scoreEnvironnemental, building.scoreEnvironnemental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBuilding, description, information, scoreLogistique, scoreAttractivite, scoreFluidite, scoreEnvironnemental);
    }

    @Override
    public String toString() {
        return "Building{" +
                "nameBuilding='" + nameBuilding + '\'' +
                ", description='" + description + '\'' +
                ", information='" + information + '\'' +
                ", scoreLogistique=" + scoreLogistique +
                ", scoreAttractivite=" + scoreAttractivite +
                ", scoreFluidite=" + scoreFluidite +
                ", scoreEnvironnemental=" + scoreEnvironnemental +
                '}';
    }
}
