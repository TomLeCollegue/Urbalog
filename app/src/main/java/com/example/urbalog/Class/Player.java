package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Player implements Serializable {

    private String name;
    private Integer age;
    private String job;
    private Integer score;
    private Role role;
    private Integer[][] financementRessource;


    public Player(String name, Integer age, String job, Integer score, Role role, Integer[][] financementRessource) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.score = score;
        this.role = role;
        this.financementRessource = financementRessource;
    }

    public Player(Role role) {
        this.score = 0;
        this.role = role;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void checkGoals(ArrayList<Building> newBuildings){
        if(role.goalsAchieve(newBuildings))
            score++;
    }

    public Integer[][] getFinancementRessource() {
        return financementRessource;
    }

    public void setFinancementRessource(Integer[][] financementRessource) {
        this.financementRessource = financementRessource;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", score=" + score +
                ", role=" + role.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(age, player.age) &&
                Objects.equals(job, player.job) &&
                Objects.equals(score, player.score) &&
                Objects.equals(role, player.role); }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, job, score, role);
    }
}
