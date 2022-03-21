package practica_1;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private String playerName;
    private ArrayList<String> teams;
    private ArrayList<String> positions;
    private int score;
    // private int count = 1;

    public Player(String playerName, String team, String position, int score) {
        this.playerName = playerName;
        this.teams = new ArrayList<String>();
        this.teams.add(team);
        this.positions = new ArrayList<String>();
        this.positions.add(position);
        this.score = score;
    }

    public void update(String team, String position, int score) {
        // this.count++;
        this.teams.add(team);
        this.positions.add(position);
        this.score = (this.score + score) / this.teams.size();
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<String> getTeams() {
        return this.teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public ArrayList<String> getPositions() {
        return this.positions;
    }

    public void setPositions(ArrayList<String> positions) {
        this.positions = positions;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        Player p = (Player) obj;
        return this.playerName.equals(p.playerName);
    }

    @Override
    public String toString() {
        // Beautiful one
        /*
         * StringBuilder sb = new StringBuilder(playerName + " played in ");
         * for (String team : this.teams) {
         * sb.append(team + ", ");
         * }
         * sb.replace(0, sb.length() - 2, " in these positions: ");
         * 
         * for (String position : this.positions) {
         * sb.append(position + ", ");
         * }
         * 
         * return sb.replace(0, sb.length() - 2,
         * ", obtaining in his carreer a total score of " + this.score + ".")
         * .toString();
         */
        // Useful one
        return this.playerName + ": " + this.score;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.score, this.score);
    }
}