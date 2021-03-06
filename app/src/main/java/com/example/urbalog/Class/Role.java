package com.example.urbalog.Class;


import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Role implements Serializable {
    private String typeRole;
    private boolean[] booleanRessource;
    private int tokenSocial;
    private int tokenEconomical;
    private int tokenPolitical;
    private String objective;
    private String hold;
    private String improve;

    public Role(String typeRole, boolean[] booleanRessource, int tokenSocial, int tokenEconomical, int tokenPolitical, String objective, String hold, String improve) {
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

    public int getTokenSocial() {
        return tokenSocial;
    }

    public void setTokenSocial(int tokenSocial) {
        this.tokenSocial = tokenSocial;
    }

    public int getTokenEconomical() {
        return tokenEconomical;
    }

    public void setTokenEconomical(int tokenEconomical) {
        this.tokenEconomical = tokenEconomical;
    }

    public int getTokenPolitical() {
        return tokenPolitical;
    }

    public void setTokenPolitical(int tokenPolitical) {
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

    public boolean goalsAchieve(ArrayList<Building> newBuildings)
    {
        if (newBuildings != null){
            int totalAttractivite = 0;
            int totalEnv = 0;
            int totalFluidite = 0;
            for(int i=0;i<newBuildings.size();i++){
                    totalAttractivite += newBuildings.get(i).getEffetAttractivite();
                    totalEnv += newBuildings.get(i).getEffetEnvironnemental();
                    totalFluidite += newBuildings.get(i).getEffetFluidite();
            }
            if ((totalAttractivite > 0 && improve.equals("Attractivité")) &&
                    ((totalEnv >= 0 && hold.equals("Environnement")) || (totalFluidite >= 0 && hold.equals("Fluidité"))))
                return true;
            else if ((totalEnv > 0 && improve.equals("Environnement")) &&
                    ((totalAttractivite >= 0 && hold.equals("Attractivité")) ||(totalFluidite >= 0 && hold.equals("Fluidité"))))
                return true;
            else return (totalFluidite > 0 && improve.equals("Fluidité")) &&
                        (((totalAttractivite >= 0 && hold.equals("Attractivité"))) || (totalEnv >= 0 && hold.equals("Environnement")));
        } else{
            return false;
        }
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

    @NonNull
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


    public void addSocial(){
        tokenSocial++;
    }
    public  void lessSocial(){
        tokenSocial--;
    }
    public void addEco(){
        tokenEconomical++;
    }
    public  void lessEco(){
        tokenEconomical--;
    }
    public void addPolitical(){
        tokenPolitical++;
    }
    public  void lessPolitical(){
        tokenPolitical--;
    }
}
