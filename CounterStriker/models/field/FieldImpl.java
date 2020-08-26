package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {

        List<Player> counterTerrorists = new ArrayList<>();
        List<Player> terrorists = new ArrayList<>();

        for (Player player : players) {
            if (player.getClass().getSimpleName().equals("Terrorist")) {
                terrorists.add(player);
            } else {
                counterTerrorists.add(player);
            }
        }

        while (true) {
            for (Player terrorist : terrorists) {
                if (terrorist.isAlive()) {
                    for (Player counterTerrorist : counterTerrorists) {
                        if (counterTerrorist.isAlive()) {
                            int damage = terrorist.getGun().fire();
                            counterTerrorist.takeDamage(damage);
                        }
                    }
                }
            }

            if (isEveryoneDead(counterTerrorists)) {
                return OutputMessages.TERRORIST_WINS;
            }

            for (Player counterTerrorist : counterTerrorists) {
                if (counterTerrorist.isAlive()) {
                    for (Player terrorist : terrorists) {
                        if (terrorist.isAlive()) {
                            int damage = counterTerrorist.getGun().fire();
                            terrorist.takeDamage(damage);
                        }
                    }
                }
            }

            if (isEveryoneDead(terrorists)) {
                return OutputMessages.COUNTER_TERRORIST_WINS;
            }
        }
    }

    private boolean isEveryoneDead(List<Player> players) {
        boolean flag = true;
        for (Player player : players) {
            if (player.isAlive()) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
