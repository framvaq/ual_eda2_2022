package practica_1;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class TestVersion2 {

    @Test
    public void readFileTest() {
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

        ArrayList<Player> players = Players.getBestPlayers();
        System.out.println(players);
        // assertTrue(p.equals(players.get(0)));
        // ArrayList<Player> res = App.getBestPlayers();
        // Player p1 = res.get(0);
        // System.out.println(p1);
    }
}