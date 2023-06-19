package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.MazeDirector;
import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.factory.BombedMazeFactory;
import pl.agh.edu.dp.labirynth.factory.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.factory.MazeFactory;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        testIfMazeFactoryWorks();
//
//        runMazeFactories();
//
//        runCountingMazeBuilder();

        startGame(BombedMazeFactory.getInstance());
    }


    public static void runCountingMazeBuilder() {
        Map<String, Integer> counts = new MazeDirector().countMazeElements();

        for (Map.Entry<String, Integer> count: counts.entrySet()) {
            System.out.println(count.getKey() + " = " + count.getValue());
        }
    }

    public static void runMazeFactories() {
        Maze maze;
        maze = new MazeDirector().createStandardMaze(MazeFactory.getInstance());
        System.out.println(maze.getNumberOfRooms());

        maze = new MazeDirector().createStandardMaze(EnchantedMazeFactory.getInstance());
        System.out.println(maze.getNumberOfRooms());

        maze = new MazeDirector().createStandardMaze(BombedMazeFactory.getInstance());
        System.out.println(maze.getNumberOfRooms());
    }

    public static void testIfMazeFactoryWorks() {
        System.out.println("Are MazeFactories the same instance?: " + (MazeFactory.getInstance() == MazeFactory.getInstance()));
        System.out.println("Are EnchantedMazeFactories the same instance?: " + (EnchantedMazeFactory.getInstance() == EnchantedMazeFactory.getInstance()));
        System.out.println("Are BombedMazeFactories the same instance?: " + (BombedMazeFactory.getInstance() == BombedMazeFactory.getInstance()));
    }

    public static void startGame(MazeFactory mazeFactory) {
        Maze maze = new MazeDirector().createStandardMaze(mazeFactory);
        Player player = new Player(maze.getRoomAtPosition(new Position(0, 0)));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Use N/n/E/e/S/s/W/w");
        while (true) {
            System.out.println(player.drawSurrounding());

            String line = scanner.nextLine();
            if (line.length() == 0) continue;

            char playerDirection = line.toUpperCase().charAt(0);
            Direction direction = Direction.getDirections(playerDirection);

            if (direction == null) {
                System.out.println("Incorrect direction. Use N/n/E/e/S/s/W/w");
                continue;
            }

            try {
                player.move(direction);
            } catch (PlayerBombedException exc) {
                System.out.println("You have been bombed, because you've visited the same room too many times");
                break;
            }

        }
    }
}



