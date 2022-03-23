package practica_1.version1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {

    public static String route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
            + System.getProperty("file.separator");
    public static int numBestPlayers = 10;

    public static ArrayList<Player> allPlayers;
    public static ArrayList<Player> bestPlayers;

    public static void readFile(String fileName) {
        route += fileName;

        File f = new File(route);
        Scanner sc;
        try {
            sc = new Scanner(f);
            allPlayers = new ArrayList<>();
            String line = sc.nextLine();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] items = line.split(";");
                Player aux = allPlayers.get(allPlayers.size() - 1);
                int score = calculateScore(items);
                if (aux.getPlayerName().equals(items[2])) {
                    aux.update(items[6], items[4], score);
                } else {
                    Player p = new Player(items[2], items[6], items[4], score);
                    allPlayers.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la lectura: ");
            System.out.println("\t" + fileName);
            e.printStackTrace();
        }
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
        int middle = (begin + end) / 2;

        getBestPlayers(begin, middle, number);
        getBestPlayers(middle + 1, end, number);

        // Ordenar y eliminar los que sobran
        bestPlayers.sort(null);
        while (bestPlayers.size() > number) {
            bestPlayers.remove(bestPlayers.size() - 1);
        }
    }

}
