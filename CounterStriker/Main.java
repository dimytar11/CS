package CounterStriker;

import CounterStriker.core.EngineImpl;
import CounterStriker.core.Engine;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.GunImpl;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();


    }
}
