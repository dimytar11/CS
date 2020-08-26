package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.GunImpl;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.ComparatorClass;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository(new ArrayList<>());
        this.players = new PlayerRepository(new ArrayList<>());
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        switch (type) {
            case "Pistol":
                Gun pistol = new Pistol(name, bulletsCount);
                guns.add(pistol);
                break;
            case "Rifle":
                Gun rifle = new Rifle(name, bulletsCount);
                guns.add(rifle);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);

        }

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        switch (type) {
            case "Terrorist":
                if (guns.findByName(gunName) == null) {
                    throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
                }

                Player terrorist = new Terrorist(username, health, armor, guns.findByName(gunName));
                players.add(terrorist);
                break;

            case "CounterTerrorist":
                if (guns.findByName(gunName) == null) {
                    throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
                }

                Player counterTerrorist = new CounterTerrorist(username, health, armor, guns.findByName(gunName));
                players.add(counterTerrorist);
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return new FieldImpl().start(players.getModels());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        for (Player player : players.getModels()) {
            sb
                    .append(player.toString())
                    .append(System.lineSeparator());
        }
        

        return sb.toString().trim();
    }


}
