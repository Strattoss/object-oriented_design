package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.Position;
import pl.agh.edu.dp.labirynth.enterable.*;

public class EnchantedMazeFactory extends MazeFactory{
    private static EnchantedMazeFactory instance;

    public static EnchantedMazeFactory getInstance() {
        if (instance == null) {
            instance = new EnchantedMazeFactory();
        }
        return instance;
    }
    @Override
    public Wall makeWall() {
        return new EnchantedWall();
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new EnchantedDoor(room1, room2);
    }

    @Override
    public Room makeRoom(Position position) {
        return new EnchantedRoom(position);
    }
}
