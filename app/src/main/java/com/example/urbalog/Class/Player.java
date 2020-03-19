package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Player implements Serializable {

    private String name;
    private int age;
    private String job;
    private int score;
    private Role role;
    private Integer[][] financementRessource;

    public Player(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.score = 0;
        this.role = null;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public Player(String name, int age, String job, Role role) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.score = 0;
        this.role = role;
        this.financementRessource = new Integer[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    }

    public Player(Role role) {
        this.name = "";
        this.age = 0;
        this.job = "";
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    @NonNull
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", score=" + score +
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
