package practica_1;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class TestVersion2 {

    @Test
    public void readFileTest() {
        App.route = System.getProperty("user.dir") + System.getProperty("file.separator") + "assets"
                + System.getProperty("file.separator") + "NbaStats.csv";
        HashSet<String> teams = new HashSet<>();
        teams.add("MIA");
        teams.add("LAL");
        teams.add("PHO");
        teams.add("TOT");
        teams.add("DAL");
        HashSet<String> positions = new HashSet<>();
        positions.add("SF");
        positions.add("PF");
        Player p = new Player("A.C. Green", teams, positions, 170);

        ArrayList<Player> players = new ArrayList<Player>(App.readFile());
        assertTrue(p.equals(players.get(0)));
        // ArrayList<Player> res = App.getBestPlayers();
        // Player p1 = res.get(0);
        // System.out.println(p1);
    }
}