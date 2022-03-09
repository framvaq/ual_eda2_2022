package practica_1.version1;

import java.util.TreeSet;

public class Player implements Comparable<Player> {
    private String playerName;
    private TreeSet<String> teams;
    private TreeSet<String> positions;
    private int score;

    public Player(String playerName, String team, String position, int score) {
        this.playerName = playerName;
        this.teams = new TreeSet<String>();
        this.teams.add(team);
        this.positions = new TreeSet<String>();
        this.positions.add(position);
        this.score = score;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public TreeSet<String> getTeams() {
        return this.teams;
    }

    public void setTeams(TreeSet<String> teams) {
        this.teams = teams;
    }

    public TreeSet<String> getPositions() {
        return this.positions;
    }

    public void setPositions(TreeSet<String> positions) {
        this.positions = positions;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean equals(Player p) {
        return this.playerName.equals(p.playerName);
    }

    public int compareTo(Player p) {
        return this.playerName.compareTo(p.playerName);
    }

    public void addTeam(String team) {
        this.teams.add(team);
    }

    public void addPosition(String position) {
        this.positions.add(position);
    }

    public void update(String team, String position, int score) {
        this.teams.add(team);
        this.positions.add(position);
        this.score = (this.score + score) / 2;
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
         * ", obtaining in his carreer a total of " + this.score + " points.")
         * .toString();
         */
        // Useful one
        return this.playerName + ": " + this.score;
    }
}
