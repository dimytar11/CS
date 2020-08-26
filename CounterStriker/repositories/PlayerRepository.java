package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerRepository implements Repository<Player> {
    private List<Player>players;

    public PlayerRepository(List<Player> players){
        this.players = new ArrayList<>();
    }



    @Override
    public Collection<Player> getModels() {
        return this.players;
    }

    @Override
    public void add(Player player) {
        if (player == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        players.add(player);
    }

    @Override
    public boolean remove(Player player) {
        return players.remove(player);
    }

    @Override
    public Player findByName(String name) {
        for (Player player: players) {
            if (player.getUsername().equals(name)) {
                return player;
            }
        }
        return null;
    }

}
