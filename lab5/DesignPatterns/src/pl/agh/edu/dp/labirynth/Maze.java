package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.enterable.Room;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    private Map<Position, Room> roomMap;

    public Maze() {
        this.roomMap = new HashMap<>();
    }

    public void placeRoom(Room room, Position position){
        roomMap.put(position, room);
    }

    public Room getRoomAtPosition(Position position) {
        return this.roomMap.get(position);
    }

    public boolean positionOccupied(Position position) {
        return this.roomMap.get(position) != null;
    }

    /**
     * Returns room at given position
     * @param position position to check
     * @throws IllegalArgumentException if no room exists on given position
     * @return room at given position
     */
    public Room getRoomAtPositionSafely(Position position) {
        Room room =  this.roomMap.get(position);
        if (room == null) {
            throw new IllegalArgumentException("There is no room on position " + position.toString());
        }
        return room;
    }

    public void setRoomMap(Map<Position, Room> roomMap) {
        this.roomMap = roomMap;
    }

    public int getNumberOfRooms()
    {
        return roomMap.size();
    }
}
