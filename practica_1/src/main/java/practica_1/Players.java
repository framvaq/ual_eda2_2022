package practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Players {

    public static String route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
            + System.getProperty("file.separator");
    public static int numBestPlayers = 10;

    public static ArrayList<Player> allPlayers;
    public static ArrayList<Player> bestPlayers;

    public static void main(String[] args) {
        readFile();

        int reps = 100;
        long begin, end;
        double total = 0;
        int j;
        for (j = 0; j < reps; j++) {
            begin = System.nanoTime();
            getBestPlayers();
            end = System.nanoTime();
            total += (end - begin);
        }

        System.out.println("Ha tardado " + (total / j) / 1000000 + "ms");
    }

    public static void readFile() {
        readFile("NbaStats.csv");
    }

    public static void readFile(String fileName) {

        File f = new File(route + fileName);
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
        score = (int) (pts * percent / 100);
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
        if (begin >= end) { // Caso base
            bestPlayers.add(allPlayers.get(begin));
            return;
        }
        // Dividir
        int middle = (end + begin) / 2;

        getBestPlayers(begin, middle, number);
        getBestPlayers(middle + 1, end, number);

        // Ordenar y eliminar los que sobran
        bestPlayers.sort(null);
        while (bestPlayers.size() > number) {
            bestPlayers.remove(bestPlayers.size() - 1);
        }
    }

    public static ArrayList<Player> getPlayersByComparator(int begin, int end, Comparator<Player> cmp) {
        ArrayList<Player> players = new ArrayList<Player>(allPlayers.size());
        if (cmp == null) { // por defecto se ordena por puntuación
            cmp = new PlayerComparator();
        }
        if (begin >= end) { // Caso base
            players.add(allPlayers.get(begin));
        } else { // Dividir
            int half = (begin + end) / 2;
            ArrayList<Player> left = getPlayersByComparator(begin, half, cmp);
            ArrayList<Player> right = getPlayersByComparator(half + 1, end, cmp);

            int i = 0;
            int j = 0;
            // Recomponer
            while (i <= left.size() - 1 && j <= right.size() - 1) {
                if (cmp.compare(left.get(i), right.get(j)) <= 0) {
                    players.add(left.get(i));
                    i++;
                } else {
                    players.add(right.get(j));
                    j++;
                }
            }
            while (i <= left.size() - 1) {
                players.add(left.get(i));
                i++;
            }
            while (j <= right.size() - 1) {
                players.add(right.get(j));
                j++;
            }
        }
        return players;
    }
}
