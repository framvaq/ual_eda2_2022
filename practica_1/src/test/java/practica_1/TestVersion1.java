package practica_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

import practica_1.version1.Player;
import practica_1.version1.PlayerComparator;
import practica_1.version1.Players;

public class TestVersion1 {
    /**
     * Datos de entrada
     */
    @Test
    public void testLeerFichero() {
        Players set = new Players();
        // TreeSet<Player> players = set.getPlayers();

        // assertTrue(players.contains(new Player("A.C. Green", "", "", 0)));
        // Player player = players.ceiling(new Player("A.C. Green", "", "", 0));

        TreeSet<String> team = new TreeSet<>();
        team.add("LAL");
        team.add("PHO");
        team.add("TOT");
        team.add("DAL");
        team.add("MIA");
        // assertTrue("Los equipos no son iguales", team.equals(player.getTeams()));
        TreeSet<String> pos = new TreeSet<>();
        pos.add("PF");
        pos.add("SF");
        // assertTrue("Las posiciones no son iguales",
        // pos.equals(player.getPositions()));

        int avg = 0;
        double percent[] = { 53.9, 53.8, 50.3, 52.9, 47.8, 47.6, 47.6, 53.7, 50.2, 50.4, 48.4, 48.3, 47.7, 48.6, 45.3,
                42.2, 44.7, 44.4 };
        int pts[] = { 521, 852, 937, 1088, 1061, 750, 1116, 1051, 1204, 916, 612, 597, 153, 444, 600, 246, 413, 367 };
        int score;
        for (int i = 0; i < pts.length; i++) {
            score = (int) ((pts[i] * percent[i]) / 100);
            avg = (avg + score) / 2;
        }
        // assertEquals("La puntuación no es la misma, debería ser " + avg + " y es " +
        // player.getScore(), avg,
        // player.getScore());
    }

    /**
     * Test puntuaciones
     */
    @Test
    public void testBestPlayers() {
        Players pl = new Players();
        // ArrayList<Player> aux = new ArrayList<>(pl.getPlayers());

        System.out.println(pl.getBestPlayers(3));
        // aux.sort(new PlayerComparator());
        // assertEquals(aux.get(aux.size() - 1), pl.getBestPlayers().last());

    }
}
