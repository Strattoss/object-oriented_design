package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.Position;
import pl.agh.edu.dp.labirynth.enterable.*;

public class BombedMazeFactory extends MazeFactory{
    private static BombedMazeFactory instance;

    public static BombedMazeFactory getInstance() {
        if (instance == null) {
            instance = new BombedMazeFactory();
        }
        return instance;
    }

    @Override
    public Wall makeWall() {
        return new BombedWall();
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new BombedDoor(room1, room2);
    }

    @Override
    public Room makeRoom(Position position) {
        return new BombedRoom(position);
    }
}
