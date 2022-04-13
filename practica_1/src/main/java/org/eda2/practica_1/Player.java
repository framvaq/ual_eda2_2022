package org.eda2.practica_1;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private String playerName;
    private ArrayList<String> teams;
    private ArrayList<String> positions;
    private int avgScore;
    private int totalScore;

    public Player(String playerName, String team, String position, int newScore) {
        this.playerName = playerName;
        this.teams = new ArrayList<String>();
        this.teams.add(team);
        this.positions = new ArrayList<String>();
        this.positions.add(position);
        this.totalScore = newScore;
        this.avgScore = newScore;
    }

    public Player(Player p) {
        this.playerName = p.playerName;
        this.teams = p.teams;
        this.positions = p.positions;
        this.totalScore = p.totalScore;
        this.avgScore = p.avgScore;
    }

    public void update(String team, String position, int newScore) {
        // this.count++;
        this.teams.add(team);
        this.positions.add(position);
        this.totalScore += newScore;
        this.avgScore = this.totalScore / this.teams.size();
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public ArrayList<String> getTeams() {
        return this.teams;
    }

    public ArrayList<String> getPositions() {
        return this.positions;
    }

    public int getScore() {
        return this.avgScore;
    }

    @Override
    public int compareTo(Player o) {
        return -Integer.compare(this.avgScore, o.avgScore);
    }

    @Override
    public boolean equals(Object obj) {
        Player p = (Player) obj;
        return this.playerName.equals(p.playerName);
    }

    @Override
    public String toString() {
        return this.playerName + ": " + this.avgScore;
    }

}
