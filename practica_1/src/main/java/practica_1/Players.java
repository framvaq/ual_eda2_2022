package practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {

    public static String route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
            + System.getProperty("file.separator");
    public static int numBestPlayers = 10;

    public static ArrayList<Player> allPlayers;
    public static ArrayList<Player> bestPlayers;

    public static void main(String[] args) {
        readFile();
        bestPlayers = getBestPlayers();
        System.out.println(bestPlayers);
    }

    public static void readFile() {
        readFile("NbaStats.csv");
    }

    public static void readFile(String fileName) {
        route += fileName;

        File f = new File(route);
        Scanner sc;
        try {
            sc = new Scanner(f);
            allPlayers = new ArrayList<>();
            String line = sc.nextLine();
            String lastName = "";
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] items = line.split(";");
                int score = calculateScore(items);

                if (items[2].equals(lastName)) {
                    allPlayers.get(allPlayers.size() - 1).update(items[6], items[4], score);
                } else {
                    Player p = new Player(items[2], items[6], items[4], score);
                    lastName = p.getPlayerName();
                    allPlayers.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error en la lectura: ");
            System.out.println("\t" + fileName);
            e.printStackTrace();
        }
    }

    private static int calculateScore(String[] items) {
        int score;
        int pts = Integer.parseInt(items[8]);
        String pcntStr = items[7].replace(",", ".");
        double percent;
        try {
            percent = Double.parseDouble(pcntStr);
        } catch (NumberFormatException nfe) {
            percent = 0;
        }
        score = (int) (pts * percent);
        return score;
    }

    public static ArrayList<Player> getBestPlayers() {
        return getBestPlayers(numBestPlayers);
    }

    public static ArrayList<Player> getBestPlayers(int n) {

        if (allPlayers.size() == 0) {
            return null;
        }
        bestPlayers = new ArrayList<>();
        getBestPlayers(0, allPlayers.size() - 1, n);
        return bestPlayers;

    }

    private static void getBestPlayers(int begin, int end, int number) {
        if (begin == end) { // Caso base
            bestPlayers.add(allPlayers.get(begin));
            return;
        }
        // Dividir
        int middle = (end + begin) / 2;

        getBestPlayers(begin, middle, number);
        getBestPlayers(middle + 1, end, number);

        // Ordenar y eliminar los que sobran
        if (bestPlayers.size() > number) {
            bestPlayers.sort(null);
        }
        while (bestPlayers.size() > number) {
            bestPlayers.remove(bestPlayers.size() - 1);
        }
    }
}
