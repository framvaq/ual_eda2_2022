package org.eda2.practica_1;

import java.util.Comparator;

public class PlayerNameComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getPlayerName().compareTo(o2.getPlayerName());
    }

}
