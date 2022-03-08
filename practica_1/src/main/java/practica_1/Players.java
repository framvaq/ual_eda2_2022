package practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Players {

    private TreeSet<Player> players = new TreeSet<>();

    public TreeSet<Player> getPlayers() {
        return this.players;
    }

    public Players() {
        String route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
                + System.getProperty("file.separator")
                + "NbaStats.csv";
        File f = new File(route);
        Scanner sc;
        try {
            sc = new Scanner(f);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] items = line.split(";");
                Player first = new Player(items[2], items[6], items[4], Integer.parseInt(items[0]));
                if (!(this.players.add(first))) { // If not added, there is a player with that name
                    notFirst(players.ceiling(first), items);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // playerName=2, team=6, position=4, score=0)
    private void notFirst(Player player, String[] data) {
        player.addTeam(data[6]);
        player.addPosition(data[4]);
        int newScore = (int) (player.getScore() + Integer.parseInt(data[0])) / 2;
        player.setScore(newScore);
    }
}
