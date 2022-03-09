package practica_1.version1;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getScore() - o2.getScore();
    }

}
