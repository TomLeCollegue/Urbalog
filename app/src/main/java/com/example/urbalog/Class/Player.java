package com.example.urbalog.Class;

import com.example.urbalog.NetworkHelper;

import java.util.Objects;

public class Player {

    private String name;
    private Integer age;
    private String job;
    private Integer score;
    private Role role;
    private NetworkHelper netHelp;

    public Player(String name, Integer age, String job, Integer score) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.score = score;
    }

    public Player(Role role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", score=" + score +
                ", role=" + role.toString() +
                ", netHelp=" + netHelp.toString() +
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
                Objects.equals(role, player.role) &&
                Objects.equals(netHelp, player.netHelp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, job, score, role, netHelp);
    }
}
