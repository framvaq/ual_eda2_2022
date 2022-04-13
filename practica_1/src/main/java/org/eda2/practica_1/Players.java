package org.eda2.practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Players {
    private ArrayList<Player> allPlayers;

    private static ArrayList<Comparator<Player>> comparators = new ArrayList<>();
    public static final String route = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "practica_1" + System.getProperty("file.separator") + "docs" + System.getProperty("file.separator");
    private int numPlayers = 10;

    public Players() {
        this("NbaStats.csv");
    }

    public Players(String fileName) {
        addComparators();

        File f = new File(route + fileName);
        Scanner sc;
        try {
            sc = new Scanner(f);
            this.allPlayers = new ArrayList<>();
            String line = sc.nextLine();
            String lastName = "";
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] items = line.split(";");
                int score = calculateScore(items);

                if (items[2].equals(lastName)) {
                    this.allPlayers.get(this.allPlayers.size() - 1).update(items[6], items[4], score);
                } else {
                    Player p = new Player(items[2], items[6], items[4], score);
                    lastName = p.getPlayerName();
                    this.allPlayers.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error en la lectura: ");
            System.out.println("\t" + fileName);
            e.printStackTrace();
        }
    }

    public int getSize() {
        return this.allPlayers.size();
    }

    private static void addComparators() {
        comparators.add(0, new PlayerDefComparator());
        comparators.add(1, new PlayerNameComparator());
        comparators.add(2, new PlayerNumberTeamsComparator());
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

    public ArrayList<Player> getBestPlayers() {
        return getBestPlayers(this.numPlayers, null);
    }

    public ArrayList<Player> getBestPlayers(int num) {
        return getBestPlayers(num, null);
    }

    public ArrayList<Player> getBestPlayers(Comparator<Player> comp) {
        return getBestPlayers(this.numPlayers, comp);
    }

    public ArrayList<Player> getBestPlayers(int num, Comparator<Player> comp) {
        num = num <= 0 ? allPlayers.size() - 1 : num;
        comp = comp == null ? comparators.get(0) : comp;

        ArrayList<Player> result = getBestPlayers(0, this.allPlayers.size() - 1, num, comp);
        return new ArrayList<Player>(result.subList(0, num));
    }

    private ArrayList<Player> getBestPlayers(int begin, int end, int num, Comparator<Player> comp) {
        ArrayList<Player> solution = new ArrayList<>(num);
        // Caso base
        if (begin == end) {
            solution.add(allPlayers.get(begin));
            return solution;
        }

        // Dividir
        int mid = (begin + end) / 2;

        // Llamadas recursivas
        ArrayList<Player> left = getBestPlayers(begin, mid, num, comp);
        ArrayList<Player> right = getBestPlayers(mid + 1, end, num, comp);

        // Combinar es ordenar izquierda y derecha
        int i = 0;
        int j = 0;
        while (solution.size() < num && i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) < 0) {
                solution.add(left.get(i));
                i++;
            } else {
                solution.add(right.get(j));
                j++;
            }
        }

        // Completo si solution tiene menos elementos de los requeridos
        while (solution.size() < num && i < left.size()) {
            solution.add(left.get(i));
            i++;
        }
        while (solution.size() < num && j < right.size()) {
            solution.add(right.get(j));
            j++;
        }

        return solution;
    }

}
