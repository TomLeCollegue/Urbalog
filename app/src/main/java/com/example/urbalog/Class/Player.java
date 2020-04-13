package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Player implements Serializable {

    private String firstName;
    private String name;
    private String sexe;
    private String age;
    private String residence;
    private String statutActivite;
    private String profession;
    private String secteurActivite;
    private String entreprise;

    private int score;
    private Role role;
    private Integer[][] financementRessource;
    private long dbID;

    public Player(){
        this.firstName = null;
        this.name = null;
        this.sexe = null;
        this.age = null;
        this.residence = null;
        this.statutActivite = null;
        this.profession = null;
        this.secteurActivite = null;
        this.entreprise = null;

        this.score = 0;
        this.role = null;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public Player(String firstName, String name, String sexe, String age, String residence,
                  String statutActivite, String profession, String secteurActivite, String entreprise) {
        this.firstName = firstName;
        this.name = name;
        this.sexe = sexe;
        this.age = age;
        this.residence = residence;
        this.statutActivite = statutActivite;
        this.profession = profession;
        this.secteurActivite = secteurActivite;
        this.entreprise = entreprise;

        this.score = 0;
        this.role = null;
        this.dbID = 0;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public Player(String firstName, String name, String sexe, String age, String residence,
                  String statutActivite, String profession, String secteurActivite, String entreprise,
                  Role role) {
        this.firstName = firstName;
        this.name = name;
        this.sexe = sexe;
        this.age = age;
        this.residence = residence;
        this.statutActivite = statutActivite;
        this.profession = profession;
        this.secteurActivite = secteurActivite;
        this.entreprise = entreprise;

        this.score = 0;
        this.role = role;
        this.dbID = 0;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

<<<<<<< Updated upstream
=======
    public Player(Role role) {
        this.name = "";
        //this.age = 0;
        //this.job = "";
        this.score = 0;
        this.role = role;
        this.dbID = 0;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

>>>>>>> Stashed changes
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getStatutActivite() {
        return statutActivite;
    }

    public void setStatutActivite(String statutActivite) {
        this.statutActivite = statutActivite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean checkGoals(ArrayList<Building> newBuildings){
        if(role.goalsAchieve(newBuildings)) {
            score++;
            return true;
        }
        return false;
    }

    public Integer[][] getFinancementRessource() {
        return financementRessource;
    }

    public void setFinancementRessource(Integer[][] financementRessource) {
        this.financementRessource = financementRessource;
    }

    public void resetFinancementRessource() {
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public long getDbID() {
        return dbID;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(firstName, player.firstName) &&
                Objects.equals(name, player.name) &&
                Objects.equals(sexe, player.sexe) &&
                Objects.equals(age, player.age) &&
                Objects.equals(residence, player.residence) &&
                Objects.equals(statutActivite, player.statutActivite) &&
                Objects.equals(profession, player.profession) &&
                Objects.equals(secteurActivite, player.secteurActivite) &&
                Objects.equals(entreprise, player.entreprise) &&
                Objects.equals(score, player.score) &&
                Objects.equals(role, player.role) &&
                Arrays.equals(financementRessource, player.financementRessource);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, name, sexe, age, residence, statutActivite, profession, secteurActivite, entreprise, score, role);
        result = 31 * result + Arrays.hashCode(financementRessource);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", sexe='" + sexe + '\'' +
                ", age='" + age + '\'' +
                ", residence='" + residence + '\'' +
                ", statutActivite='" + statutActivite + '\'' +
                ", profession='" + profession + '\'' +
                ", secteurActivite='" + secteurActivite + '\'' +
                ", entreprise='" + entreprise + '\'' +
                ", score=" + score +
                ", role=" + role +
                ", financementRessource=" + Arrays.toString(financementRessource) +
                '}';
    }
}
