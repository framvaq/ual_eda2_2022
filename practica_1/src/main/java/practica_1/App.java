package practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static String route = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "assets" + System.getProperty("file.separator") + "NbaStats.csv";
    private static ArrayList<Player> players;
    public static int numberOfPlayers = 10;

    public static void main(String[] args) {
        players = readFile();
        ArrayList<Player> bestPlayers = getBestPlayers();
        System.out.println(bestPlayers);
    }

    public static ArrayList<Player> getBestPlayers() {
        return getBestPlayers(numberOfPlayers);
    }

    public static ArrayList<Player> getBestPlayers(int number) {
        if (players.size() == 0) {
            return null;
        }
        return getBestPlayers(0, players.size() - 1, new ArrayList<Player>(), number);
    }

    private static ArrayList<Player> getBestPlayers(int begin, int end, ArrayList<Player> p, int num) {
        if (end == begin) {
            p.add(players.get(begin));
            return p;
        }
        int half = (end - begin) / 2;

        p.addAll(getBestPlayers(begin, half, p, num));
        p.addAll(getBestPlayers(half + 1, end, p, num));

        p.sort(null);
        if (p.size() > num) {
            p.subList(0, num + 1);
        }

        return p;
    }

    public static ArrayList<Player> readFile() {
        return readFile(route);
    }

    public static ArrayList<Player> readFile(String file) {
        players = new ArrayList<Player>();
        File f = new File(file);
        Scanner sc;
        try {
            sc = new Scanner(f);
            sc.nextLine();

            String lastName = "";
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().trim().split(";");
                if (tokens[2].equals(lastName)) {
                    players.get(players.size() - 1).update(tokens[6], tokens[4], calculateScore(tokens));
                } else {
                    Player p = new Player(tokens[2], tokens[6], tokens[4], calculateScore(tokens));
                    players.add(p);
                    lastName = p.getPlayerName();
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
        return players;
    }

    private static int calculateScore(String[] items) {
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
