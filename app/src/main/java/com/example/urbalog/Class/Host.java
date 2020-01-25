package com.example.urbalog.Class;

import java.util.ArrayList;

public class Host {

    private ArrayList<Player> players;
    private ArrayList<Game> games;

    public Host() {
        this.players = new ArrayList<Player>();
        this.games = new ArrayList<Game>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
}
