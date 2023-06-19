package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Position;

import java.util.HashMap;
import java.util.Map;

public class CountingMazeBuilder implements MazeBuilder {
    private int wallCounter, roomCounter, doorCounter;

    public CountingMazeBuilder() {
        resetCounters();
    }

    @Override
    public void reset() {
        resetCounters();
    }

    private void resetCounters() {
        this.doorCounter = 0;
        this.roomCounter = 0;
        this.wallCounter = 0;
    }

    @Override
    public void addRoom(Position newPosition) {
        this.roomCounter++;
    }

    @Override
    public void addRoomSurroundedByWalls(Position newPosition) {
        this.roomCounter++;
        this.wallCounter += 4;
    }

    @Override
    public void connectRooms(Position position, Direction direction) {

    }

    @Override
    public void addWall(Position position, Direction direction) {
        this.wallCounter++;
    }

    @Override
    public void addDoor(Position position, Direction direction) {
        this.doorCounter++;
    }

    public Map<String, Integer> getCounts() {
        Map<String, Integer> counters = new HashMap<>(3);
        counters.put("walls", this.wallCounter);
        counters.put("doors", this.doorCounter);
        counters.put("rooms", this.roomCounter);

        return counters;
    }
}
