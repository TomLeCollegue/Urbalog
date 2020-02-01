package com.example.urbalog.Class;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Role extends Object implements Serializable {
    private String typeRole;
    private boolean[] booleanRessource;
    private Integer tokenSocial;
    private Integer tokenEconomical;
    private Integer tokenPolitical;
    private String objective;
    private String hold;
    private String improve;

    public Role(String typeRole, boolean[] booleanRessource, Integer tokenSocial, Integer tokenEconomical, Integer tokenPolitical, String objective, String hold, String improve) {
        this.typeRole = typeRole;
        this.booleanRessource = booleanRessource;
        this.tokenSocial = tokenSocial;
        this.tokenEconomical = tokenEconomical;
        this.tokenPolitical = tokenPolitical;
        this.objective = objective;
        this.hold = hold;
        this.improve = improve;
    }
    public Role(String typeRole) {
        this.typeRole = typeRole;
    }

    public String getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(String typeRole) {
        this.typeRole = typeRole;
    }

    public boolean[] getBooleanRessource() {
        return booleanRessource;
    }

    public void setBooleanRessource(boolean[] booleanRessource) {
        this.booleanRessource = booleanRessource;
    }

    public Integer getTokenSocial() {
        return tokenSocial;
    }

    public void setTokenSocial(Integer tokenSocial) {
        this.tokenSocial = tokenSocial;
    }

    public Integer getTokenEconomical() {
        return tokenEconomical;
    }

    public void setTokenEconomical(Integer tokenEconomical) {
        this.tokenEconomical = tokenEconomical;
    }

    public Integer getTokenPolitical() {
        return tokenPolitical;
    }

    public void setTokenPolitical(Integer tokenPolitical) {
        this.tokenPolitical = tokenPolitical;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getImprove() {
        return improve;
    }

    public void setImprove(String improve) {
        this.improve = improve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(typeRole, role.typeRole) &&
                Arrays.equals(booleanRessource, role.booleanRessource) &&
                Objects.equals(tokenSocial, role.tokenSocial) &&
                Objects.equals(tokenEconomical, role.tokenEconomical) &&
                Objects.equals(tokenPolitical, role.tokenPolitical) &&
                Objects.equals(objective, role.objective) &&
                Objects.equals(hold, role.hold) &&
                Objects.equals(improve, role.improve);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(typeRole, tokenSocial, tokenEconomical, tokenPolitical, objective, hold, improve);
        result = 31 * result + Arrays.hashCode(booleanRessource);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "typeRole='" + typeRole + '\'' +
                ", booleanRessource=" + Arrays.toString(booleanRessource) +
                ", tokenSocial=" + tokenSocial +
                ", tokenEconomical=" + tokenEconomical +
                ", tokenPolitical=" + tokenPolitical +
                ", objective='" + objective + '\'' +
                ", hold='" + hold + '\'' +
                ", improve='" + improve + '\'' +
                '}';
    }
}
