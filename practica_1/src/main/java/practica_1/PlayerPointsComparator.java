package practica_1;

import java.util.Comparator;

public class PlayerPointsComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        // TODO
        return o1.getPoints() - o2.getScore();
    }

}