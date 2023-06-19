package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Position;

public interface MazeBuilder {

    public void reset();

    /**
     * Adds a new room
     * @param newPosition position of the new room
     */
    public void addRoom(Position newPosition);

    /**
     * Adds a new room and surrounds it with walls in all 4 directions
     * @param newPosition position of the new room
     */
    public void addRoomSurroundedByWalls(Position newPosition);

    /**
     * Directly connects two neighbouring rooms (no wall or door)
     *
     * @param position  first room's position
     * @param direction second room's position
     */
    public void connectRooms(Position position, Direction direction);

    /**
     * Place a wall on one side of the room.
     * If there is a neighbouring room in that direction, the new wall will also become neighbour's wall
     *
     * @param position  first room's position
     * @param direction second room's position
     */
    public void addWall(Position position, Direction direction);

    /**
     * Adds a door between two rooms.
     * If there is a neighbouring room in that direction, the new door will also become neighbour's wall
     *
     * @param position  first room's position
     * @param direction direction of new door
     */
    public void addDoor(Position position, Direction direction);

    /*
    Methods for removing various objects (removeDoor, removeWall etc.) would be convenient,
    but for sake of simplicity I will skip them, so I can focus on implementing design pattern.
    */
}
