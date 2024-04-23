package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.AlienBug;
import game.actors.HuntsmanSpider;
import game.actors.Player;
import game.actors.SuspiciousAstronaut;
import game.behaviours.FollowBehaviour;
import game.grounds.*;
import game.items.LargeBolt;
import game.items.MetalSheet;
import game.weapons.MetalPipe;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new SaplingTree(), new Crater(), new MatureTree());

        List<String> map = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~....uu..................",
                "............u.................",
                ".............#####T...........",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".....u.......##_##.........~~~",
                ".....u..........t~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, map);
        LargeBolt largeBolt = new LargeBolt();
        MetalSheet metalSheet = new MetalSheet();
        MetalPipe metalPipe = new MetalPipe();

        world.addGameMap(gameMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        gameMap.at(7, 9).addActor(new SuspiciousAstronaut());
        gameMap.at(24, 8).addItem(largeBolt);
        gameMap.at(24, 9).addItem(metalSheet);
        gameMap.at(15, 8).addItem(metalPipe);
        gameMap.at(15, 10).addActor(new AlienBug());
        gameMap.at(14, 10).addActor(new AlienBug());

        Player player = new Player("Intern", '@', 4);

        world.addPlayer(player, gameMap.at(15, 6));

        world.run();

        for (String line : FancyMessage.YOU_ARE_FIRED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
