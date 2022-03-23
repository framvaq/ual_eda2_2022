package practica_1;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Players.readFile();

        // ArrayList<Player> p1 = Players.allPlayers;

        long begin, end, total;

        System.out.println("Número de jugadores\tTiempo(ms)");
        for (int i = 21; i <= 3921; i += 100) {
            begin = System.nanoTime();
            Players.getBestPlayers(i);
            end = System.nanoTime();
            total = end - begin;
            System.out.println(i + "\t\t\t" + total / 1000000);
        }

        System.out.println("Con comparator");
        System.out.println("Número de jugadores\tTiempo(ms)");
        for (int i = 21; i <= 3921; i += 100) {
            begin = System.nanoTime();
            Players.getPlayersByComparator(0, Players.allPlayers.size() - 1, i, null);
            end = System.nanoTime();
            total = end - begin;
            System.out.println(i + "\t\t\t" + total / 1000000);
        }
    }
}
