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
                int score = calculateScore(items);
                Player p = new Player(items[2], items[6], items[4], score);
                if (!(this.players.add(p))) { // If not added, there is a player with that name
                    players.ceiling(p).update(items[6], items[4], score);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int calculateScore(String[] items) {
        int score = 0;
        int pts = Integer.parseInt(items[8]);
        String pcntStr = items[7].replace(",", ".");
        double percent;
        try {
            percent = Double.parseDouble(pcntStr);
        } catch (NumberFormatException nfe) {
            percent = 0;
        }
        score = (int) ((pts * percent) / 100);
        return score;
    }

}
