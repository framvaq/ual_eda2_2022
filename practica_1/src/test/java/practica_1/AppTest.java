package practica_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Test;

public class AppTest {
    /**
     * Datos de entrada
     */
    @Test
    public void testLeerFichero() {
        Players set = new Players();
        TreeSet<Player> players = set.getPlayers();

        assertTrue(players.contains(new Player("A.C. Green", "", "", 0)));
        Player player = players.ceiling(new Player("A.C. Green", "", "", 0));

        TreeSet<String> team = new TreeSet<>();
        team.add("LAL");
        team.add("PHO");
        team.add("TOT");
        team.add("DAL");
        team.add("MIA");
        assertTrue(team.equals(player.getTeams()));
        TreeSet<String> pos = new TreeSet<>();
        pos.add("PF");
        pos.add("SF");
        assertTrue(pos.equals(player.getPositions()));

        int avg = 0;
        int values[] = { 8035, 8420, 8807, 9242, 9688, 10166, 10617, 11060, 11529,
                11999, 12477, 13034, 13035, 13036, 13600, 14139, 14648, 15148 };
        for (int val : values) {
            avg += val;
            avg /= 2;
        }

        assertEquals(avg, player.getScore());

    }
}
