package org.eda2.practica_1;

import java.util.Comparator;

public class PlayerDefComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.compareTo(o2);
    }

}
