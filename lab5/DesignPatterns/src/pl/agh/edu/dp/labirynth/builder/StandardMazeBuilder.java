package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.enterable.Room;
import pl.agh.edu.dp.labirynth.factory.MazeFactory;
import pl.agh.edu.dp.labirynth.enterable.Door;
import pl.agh.edu.dp.labirynth.enterable.Wall;

public class StandardMazeBuilder implements MazeBuilder {
    private Maze maze = new Maze();
    private final MazeFactory mazeFactory;

    public StandardMazeBuilder(MazeFactory mazeFactory) {
        this.mazeFactory = mazeFactory;
    }

    public Maze getResult() {
    return this.maze;
    }

    @Override
    public void reset() {
        this.maze = new Maze();
    }

    @Override
    public void addRoom(Position newPosition) {
        if (this.maze.positionOccupied(newPosition)) {
            throw new IllegalArgumentException("A room already exists on position " + newPosition.toString());
        }

        Room newRoom = mazeFactory.makeRoom(newPosition);
        this.maze.placeRoom(newRoom, newPosition);
    }

    @Override
    public void addRoomSurroundedByWalls(Position newPosition) {
        addRoom(newPosition);

        for (Direction direction: Direction.values()) {
            addWall(newPosition, direction);
        }
    }

    @Override
    public void connectRooms(Position position, Direction direction) {
        Room room1 = this.maze.getRoomAtPositionSafely(position);
        Room room2 = this.maze.getRoomAtPositionSafely(position.add(direction.getUnitVector()));

        room1.setSide(direction, room2);
        room2.setSide(direction.opposite(), room1);
    }

    @Override
    public void addWall(Position position, Direction direction) {
        Wall wall = mazeFactory.makeWall();

        Room room1 = this.maze.getRoomAtPositionSafely(position);
        room1.setSide(direction, wall);

        // only if there is a neighbouring room in this direction; if so, the wall must be shared
        Room room2 = this.maze.getRoomAtPosition(position.add(direction.getUnitVector()));
        if (room2 != null) {
            room2.setSide(direction.opposite(), wall);
        }
    }

    @Override
    public void addDoor(Position position, Direction direction) {
        Room room1 = this.maze.getRoomAtPositionSafely(position);
        Room room2 = this.maze.getRoomAtPositionSafely(position.add(direction.getUnitVector()));

        Door door = mazeFactory.makeDoor(room1, room2);

        room1.setSide(direction, door);
        room2.setSide(direction.opposite(), door);
    }
}
