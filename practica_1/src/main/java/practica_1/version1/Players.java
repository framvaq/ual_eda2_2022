package practica_1.version1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Players {

    private TreeSet<Player> players = new TreeSet<>();

    private ArrayList<Player> nba;
    String route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
            + System.getProperty("file.separator");

    public TreeSet<Player> getPlayers() {
        return this.players;
    }

    public static int number = 10;

    public Players() {
        this("NbaStats.csv");
    }

    public Players(String filename) {
        this.route += filename;
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

    public TreeSet<Player> getBestPlayers() {
        return getBestPlayers(number);
    }

    public TreeSet<Player> getBestPlayers(int n) {
        if (this.players.size() == 0) {
            return null;
        }
        nba = new ArrayList<>(this.players);
        TreeSet<Player> aux = new TreeSet<>(new PlayerComparator());
        TreeSet<Player> res = this.getBestPlayers(aux, 0, nba.size() - 1, n);
        return res;

    }

    private TreeSet<Player> getBestPlayers(TreeSet<Player> list, int begin, int end, int number) {
        if (begin == end) { // Caso base
            list.add(this.nba.get(begin));
            return list;
        }
        int middle = (begin + end) / 2;

        // Juntar
        list.addAll(this.getBestPlayers(list, begin, middle, number));
        list.addAll(this.getBestPlayers(list, middle + 1, end, number));

        while (list.size() > number) {
            list.remove(list.first());
        }

        return list;
    }

}
