package CounterStriker.models.players;

import java.util.Comparator;

public class ComparatorClass implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        int result = p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName()) ;

        if (result == 0) {
            result = Integer.compare(p1.getHealth(), p2.getHealth());
        }
        if (result == 0) {
            result = p1.getUsername().compareTo(p2.getUsername());
        }

        return result;

    }
}
