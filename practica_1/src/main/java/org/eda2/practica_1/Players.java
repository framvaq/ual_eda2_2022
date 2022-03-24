package org.eda2.practica_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Players {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Player> allPlayers;
    private ArrayList<Player> bestPlayers;
    private ArrayList<Comparator<Player>> comparators;
    public static final String route = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "practica_1" + System.getProperty("file.separator") + "docs" + System.getProperty("file.separator");
    private int numPlayers = 10;

    public Players() {
        this("NbaStats.csv");
    }

    public Players(String fileName) {
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

    public void getBestPlayers() {
        // getBestPlayers(this.numPlayers, null);
        System.out.println("getBestPlayers()");
    }

    public void getBestPlayers(int num) {
        // getBestPlayers(num, null);
        System.out.println("getBestPlayers(num)");
    }

    public void getBestPlayers(Comparator<Player> comp) {
        // getBestPlayers(this.numPlayers, comp);
        System.out.println("getBestPlayers(comp)");
    }

    public void getBestPlayers(int num, Comparator<Player> comp) {
        System.out.println("getBestPlayers(num, comp)");
    }
}
