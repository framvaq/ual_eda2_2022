package practica_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestVersion2 {

    @Test
    public void testReadFile() {
        Players.readFile();
        ArrayList<String> teams = new ArrayList<>();
        teams.add("MIA");
        teams.add("LAL");
        teams.add("PHO");
        teams.add("TOT");
        teams.add("DAL");
        ArrayList<String> positions = new ArrayList<>();
        positions.add("SF");
        positions.add("PF");
        Player p = new Player("A.C. Green", "MIA", "SF", 170);
        ArrayList<Player> players = Players.allPlayers;
        assertTrue(players.contains(p));
    }

    @Test
    public void testScore() {
        Players.readFile("test.csv");
        ArrayList<Player> players = Players.allPlayers;
        // 48,4*612/100
        // 48,3*597/100
        int score1 = (int) (48.4 * 612 / 100);
        int score2 = (int) (48.3 * 597 / 100);
        int score3 = (int) (47.7 * 153 / 100);
        int score4 = (int) (48.6 * 444 / 100);
        int avg = (score1 + score2 + score3 + score4) / 4;
        assertEquals(avg, players.get(0).getScore());
    }

    @Test
    public void testPlayersComparator() {
        Players.readFile();
        ArrayList<Player> bestPlayers = Players.getBestPlayers(Players.allPlayers.size() - 1);
        ArrayList<Player> playersNullcmp = Players.getPlayersByComparator(0, Players.allPlayers.size() - 1, null);

        for (int i = 0; i < Players.allPlayers.size() - 1; i++) {
            assertEquals(bestPlayers.get(i), playersNullcmp.get(i));
        }
    }
}