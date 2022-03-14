package practica_1;

import java.util.HashSet;

public class Player implements Comparable<Player> {

    private String playerName;
    private HashSet<String> teams;
    private HashSet<String> positions;
    private int score;

    public Player(String playerName, String team, String position, int score) {
        this.playerName = playerName;
        this.teams = new HashSet<String>();
        this.teams.add(team);
        this.positions = new HashSet<String>();
        this.positions.add(position);
        this.score = score;
    }

    public Player(String name, HashSet<String> teams, HashSet<String> positions, int score) {
        this.playerName = name;
        this.teams = teams;
        this.positions = positions;
        this.score = score;
    }

    public void update(String team, String position, int score) {
        if (score <= 0) {
            return;
        }
        this.teams.add(team);
        this.positions.add(position);
        this.score = (this.score + score) / 2;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public HashSet<String> getTeams() {
        return this.teams;
    }

    public HashSet<String> getPositions() {
        return this.positions;
    }

    public void setPositions(HashSet<String> positions) {
        this.positions = positions;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return this.playerName + ": " + this.score;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.score, o.score);
    }
}