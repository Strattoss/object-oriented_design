package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.enterable.Door;
import pl.agh.edu.dp.labirynth.Position;
import pl.agh.edu.dp.labirynth.enterable.Room;
import pl.agh.edu.dp.labirynth.enterable.Wall;

public class MazeFactory {
    private static MazeFactory instance;

    MazeFactory() {
    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            instance = new MazeFactory();
        }
        return instance;
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    public Room makeRoom(Position position) {
        return new Room(position);
    }
}
